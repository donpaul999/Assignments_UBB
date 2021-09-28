import logging
import time
import base64
import json
import pandas as pd
import requests
import textwrap

from typing import List, Dict, Optional, Tuple, Any

from gql import Client, gql  # type: ignore
from gql.transport.requests import RequestsHTTPTransport  # type: ignore

from transform_tools.validate import upload_configs, ERROR_RESPONSE_PREFIX
from .exceptions import QueryRuntimeException
from .utils import get_git_info, is_git_repo, local_commit_info, DEFAULT_QUERY_TIMEOUT, POLL_INTERVAL
from .models import (
    MqlQueryStatus,
    MqlQueryStatusResp,
    QueryResult,
    CacheMode,
    Organization,
    User,
    Materialization,
    ModelKey,
    Metric,
    MQLServer,
    HealthReport,
    UserState,
    TimeGranularity,
)

TRANSFORM_PROD_API = "https://api.transformdata.io"

logger = logging.getLogger(__name__)


class BaseInterface:
    """Base implementation for our various GraphQL APIs we connect to.

    Assumes that authorization and GraphQL query pattern are the same across Backend, and MQL APIs
    """

    def __init__(self, auth_header: Dict[str, str], rest_api_url: str, verify: bool = True) -> None:  # noqa: D
        self.rest_api_url = rest_api_url
        self.auth_header = auth_header
        self.verify = verify

        self.gql_client = Client(
            transport=RequestsHTTPTransport(
                url=self.graphql_api_url, verify=self.verify, retries=2, headers=self.auth_header
            ),
            fetch_schema_from_transport=False,
        )

    def execute(  # type: ignore [misc]
        self, query: gql, verbose: bool = False, variable_values: Dict[str, Any] = None
    ) -> Dict[str, Any]:
        """Error handling for GQL Clients"""
        start_time = time.time()
        try:
            response = self.gql_client.execute(query, variable_values)
        except requests.exceptions.ConnectionError:
            raise Exception(
                textwrap.dedent(
                    """\
                    Transform could not connect to the MQL Server.

                    Check that you are currently connected to the internet or that the MQL Server is up using:

                            mql health-report
                    """
                )
            )
        except Exception as e:
            exception_response = str(e.args[0])
            if "Authentication hook unauthorized" in exception_response:
                raise Exception(
                    textwrap.dedent(
                        """\
                        Transform could not authenticate the set API Key.

                        A new API Key can be created at:

                            https://app.transformdata.io/api_keys

                        and then set using the following command:

                            mql setup -k <api-key>
                            OR instantiate with MQLClient(api_key=<api-key>)
                        """
                    )
                )
            else:
                raise
        finally:
            logger.debug(f"Query {query} took {(time.time() - start_time):.2f}s")
        return response

    @property
    def graphql_api_url(self) -> str:  # noqa: D
        return f"{self.rest_api_url}/graphql"


class BackendInterface(BaseInterface):  # noqa: D
    def __init__(  # noqa: D
        self, auth_header: Dict[str, str], rest_api_url: str = TRANSFORM_PROD_API, verify: bool = True
    ) -> None:
        super(BackendInterface, self).__init__(auth_header, rest_api_url, verify)

    def _get_and_upload_configs(
        self, mode: str, config_dir: str, repo: str, branch: str, commit: str
    ) -> requests.Response:  # noqa: D
        try:
            return upload_configs(mode, self.auth_header, repo, branch, commit, config_dir, self.rest_api_url)
        except Exception as e:
            error_content = str(e).split(ERROR_RESPONSE_PREFIX)
            error_msg = str(e)
            if len(error_content) == 2:
                error_response = json.loads(error_content[1])["error"]
                error_msg = f"🙈 {error_response['error_type']}: {error_response['message']}"
            raise Exception(error_msg)

    def validate_configs(self, config_dir: str) -> Tuple[str, str, str]:  # noqa: D
        """Finds and validates yaml configs in the provided directory"""
        if is_git_repo(config_dir):
            repo, branch, commit = get_git_info(config_dir)
        else:
            repo, branch, commit = local_commit_info()

        commit += "-dirty"
        self._get_and_upload_configs("validate", config_dir, repo, branch, commit)

        return repo, branch, commit

    def commit_configs(self, config_dir: str) -> ModelKey:
        """Finds and validates yaml configs in the provided directory"""
        repo, branch, commit = get_git_info()
        commit += "-dirty"
        r = self._get_and_upload_configs("commit", config_dir, repo, branch, commit)

        return ModelKey.from_snake_dict(r.json()["model"])

    def get_my_org(self) -> Organization:  # noqa: D
        """Queries for "my org" (the current user's org)"""
        query = gql(
            """
            query MyOrgQuery {
                myOrganization {
                    id
                    name
                    createdAt
                    primaryConfigRepo
                    primaryConfigBranch
                }
            }
            """
        )

        return Organization.from_gql_dict(self.execute(query)["myOrganization"])

    def get_me(self) -> User:  # noqa: D
        """Queries for "me" (the current user)"""
        query = gql(
            """
            query MeQuery {
                myUser {
                    id
                    userName
                    email
                    mqlServerUrl
                }
            }
            """
        )

        return User.from_gql_dict(self.execute(query)["myUser"])

    def get_latest_model_key(self) -> "ModelKey":
        """Queries for latest model key in Web API GQL"""
        query = gql(
            """
            query LatestModelKeyQuery {
                myOrganization {
                    currentModel {
                        id
                        organizationId
                        gitBranch
                        gitCommit
                        gitRepo
                        createdAt
                        isCurrent
                    }
                }
            }
            """
        )
        return ModelKey.from_gql_dict(self.execute(query)["myOrganization"]["currentModel"][0])

    def get_model_key(self, model_id: int) -> "ModelKey":
        """Queries for a given model id"""
        query = gql(
            """
            query ModelKeyQuery($modelId: ID) {
                myOrganization {
                    models(id: $modelId){
                        id
                        organizationId
                        gitBranch
                        gitCommit
                        gitRepo
                        createdAt
                        isCurrent
                    }
                }
            }
            """
        )
        return ModelKey.from_gql_dict(
            self.execute(query, variable_values={"modelId": model_id})["myOrganization"]["models"][0]
        )

    def get_org_mql_servers(self) -> List[MQLServer]:  # noqa: D
        query = gql(
            """
            query MqlServersQuery {
                myUser {
                    organization {
                        mqlServers {
                            id
                            name
                            url
                            isOrgDefault
                        }
                    }
                }
            }
            """
        )

        return [MQLServer.from_gql_dict(s) for s in self.execute(query)["myUser"]["organization"]["mqlServers"]]

    def get_user_state(self) -> UserState:
        """Fetch all the core models for the User/Org/current model in one simple query"""
        query = gql(
            """
            query MeQuery {
                myUser {
                    id
                    userName
                    email
                    mqlServerUrl
                    organization {
                        id
                        name
                        createdAt
                        primaryConfigRepo
                        primaryConfigBranch
                        currentModel {
                            id
                            organizationId
                            gitBranch
                            gitCommit
                            gitRepo
                            createdAt
                            isCurrent
                        }
                    }
                }
            }
            """
        )

        resp = self.execute(query)
        user = User.from_gql_dict(resp["myUser"])
        org = Organization.from_gql_dict(resp["myUser"]["organization"])

        model: Optional[ModelKey] = None
        if len(resp["myUser"]["organization"]["currentModel"]):
            model = ModelKey.from_gql_dict(resp["myUser"]["organization"]["currentModel"][0])

        return UserState(user=user, organization=org, current_model=model)


class MQLInterface(BaseInterface):  # noqa: D
    def __init__(self, auth_header: Dict[str, str], query_server_url: str) -> None:  # noqa: D
        super(MQLInterface, self).__init__(auth_header, query_server_url)

    def __common_query_input_params(  # type: ignore [misc]
        self,
        metrics: List[str],
        dimensions: List[str],
        model_key: Optional[ModelKey] = None,
        where_constraint: Optional[str] = None,
        time_constraint: Optional[str] = None,
        time_granularity: Optional[TimeGranularity] = None,
        order: Optional[List[str]] = None,
        limit: Optional[str] = None,
        cache_mode: Optional[CacheMode] = None,
        as_table: Optional[str] = None,
        time_series: bool = True,
        result_format: Optional[str] = None,
    ) -> Dict[str, Any]:
        return {
            "modelKey": {
                "organization": model_key.organization_id,
                "repo": model_key.repository,
                "branch": model_key.branch,
                "commit": model_key.commit,
            }
            if model_key
            else None,
            "metrics": metrics,
            "groupBy": dimensions,
            "whereConstraint": where_constraint,
            "timeConstraint": time_constraint,
            "timeGranularity": time_granularity.name if time_granularity else None,
            "order": order,
            "limit": limit,
            "cacheMode": (CacheMode.default() if cache_mode is None else cache_mode).name,
            "asTable": as_table,
            "timeSeries": time_series,
            "resultFormat": result_format,
        }

    def drop_materialization(  # noqa: D
        self,
        materialization_name: str,
        start_time: Optional[str],
        end_time: Optional[str],
        model_key: Optional[ModelKey] = None,
    ) -> str:
        """Implements drop materialization mutation in MQL/GQL"""
        query = gql(
            """
            mutation CreateMqlDropMaterializationMutation(
                $materializationName: String!
                $startTime: String
                $endTime: String
                $modelKey: ModelKeyInput
            ) {
                createMqlDropMaterialization(
                    input: {
                        modelKey: $modelKey,
                        materializationName: $materializationName,
                        startTime: $startTime,
                        endTime: $endTime,
                    }
                ) {
                    id
                }
            }
            """
        )

        return self.execute(
            query,
            variable_values={
                "modelKey": {
                    "organization": model_key.organization_id,
                    "repo": model_key.repository,
                    "branch": model_key.branch,
                    "commit": model_key.commit,
                }
                if model_key
                else None,
                "materializationName": materialization_name,
                "startTime": start_time,
                "endTime": end_time,
            },
        )["createMqlDropMaterialization"]["id"]

    def create_materialization(  # noqa: D
        self,
        materialization_name: str,
        start_time: Optional[str],
        end_time: Optional[str],
        model_key: Optional[ModelKey] = None,
        output_table: Optional[str] = None,
    ) -> str:
        """Implements Materialize (new) mutation in MQL/GQL"""
        # Note: we omit the outputTable var if arg is not passed to maintain backwards compatibility with servers
        # that don't recognize it (ie if they're not on 0e7448e8b6a75484f806c32c7f74e3aafbf11b7d or later)
        if output_table is not None:
            query = gql(
                """
                mutation CreateMqlMaterializationNewMutation(
                    $materializationName: String!
                    $startTime: String
                    $endTime: String
                    $modelKey: ModelKeyInput
                    $outputTable: String
                ) {
                    createMqlMaterializationNew(
                        input: {
                            modelKey: $modelKey,
                            materializationName: $materializationName,
                            startTime: $startTime,
                            endTime: $endTime,
                            outputTable: $outputTable
                        }
                    ) {
                        id
                    }
                }
                """
            )

            return self.execute(
                query,
                variable_values={
                    "modelKey": {
                        "organization": model_key.organization_id,
                        "repo": model_key.repository,
                        "branch": model_key.branch,
                        "commit": model_key.commit,
                    }
                    if model_key
                    else None,
                    "materializationName": materialization_name,
                    "startTime": start_time,
                    "endTime": end_time,
                    "outputTable": output_table,
                },
            )["createMqlMaterializationNew"]["id"]
        else:
            query = gql(
                """
                mutation CreateMqlMaterializationNewMutation(
                    $materializationName: String!
                    $startTime: String
                    $endTime: String
                    $modelKey: ModelKeyInput
                ) {
                    createMqlMaterializationNew(
                        input: {
                            modelKey: $modelKey,
                            materializationName: $materializationName,
                            startTime: $startTime,
                            endTime: $endTime,
                        }
                    ) {
                        id
                    }
                }
                """
            )

            return self.execute(
                query,
                variable_values={
                    "modelKey": {
                        "organization": model_key.organization_id,
                        "repo": model_key.repository,
                        "branch": model_key.branch,
                        "commit": model_key.commit,
                    }
                    if model_key
                    else None,
                    "materializationName": materialization_name,
                    "startTime": start_time,
                    "endTime": end_time,
                },
            )["createMqlMaterializationNew"]["id"]

    def get_materializations(self, model_key: Optional[ModelKey] = None) -> List[Materialization]:  # noqa: D
        query = gql(
            """
            query MaterializationsList($modelKey: ModelKeyInput) {
                materializations(modelKey: $modelKey) {
                    name
                    metrics
                    dimensions
                    destinationTable
                }
            }
            """
        )

        return [
            Materialization.from_gql_dict(m)
            for m in self.execute(
                query,
                variable_values={
                    "modelKey": {
                        "organization": model_key.organization_id,
                        "repo": model_key.repository,
                        "branch": model_key.branch,
                        "commit": model_key.commit,
                    }
                    if model_key
                    else None
                },
            )["materializations"]
        ]

    def get_metrics(self, model_key: Optional[ModelKey] = None) -> List[Metric]:  # noqa: D
        query = gql(
            """
            query MetricList($modelKey: ModelKeyInput) {
                metrics(modelKey: $modelKey) {
                    name
                    measures
                    dimensions
                }
            }
            """
        )

        return [
            Metric.from_gql_dict(m)
            for m in self.execute(
                query,
                variable_values={
                    "modelKey": {
                        "organization": model_key.organization_id,
                        "repo": model_key.repository,
                        "branch": model_key.branch,
                        "commit": model_key.commit,
                    }
                    if model_key
                    else None
                },
            )["metrics"]
        ]

    def create_query(  # noqa: D
        self,
        metrics: List[str],
        dimensions: List[str],
        model_key: Optional[ModelKey] = None,
        where_constraint_str: Optional[str] = None,
        time_constraint: Optional[str] = None,
        time_granularity: Optional[TimeGranularity] = None,
        order: Optional[List[str]] = None,
        limit: Optional[str] = None,
        cache_mode: Optional[CacheMode] = None,
        as_table: Optional[str] = None,
    ) -> str:
        """Implements CreateMqlQuery mutation in MQL/GQL"""

        # Unless we are requesting multiple metrics, include the post-processor required by the UI
        # so that users are able to visualize the results in the query logs
        if len(metrics) > 1 or len(dimensions) > 1:
            time_series = False
            result_format = None
        else:
            time_series = True
            result_format = "TFD"
        query = gql(
            """
            mutation CreateMqlQueryMutation(
                $modelKey: ModelKeyInput,
                $metrics: [String!],
                $groupBy: [String!],
                $whereConstraint: String,
                $timeConstraint: String,
                $timeGranularity: TimeGranularity,
                $order: [String!],
                $limit: LimitInput,
                $cacheMode: CacheMode,
                $asTable: String,
                $timeSeries: Boolean,
                $resultFormat: ResultFormat,
            ) {
                createMqlQuery(
                    input: {
                        modelKey: $modelKey,
                        metrics: $metrics,
                        groupBy: $groupBy,
                        whereConstraint: $whereConstraint,
                        timeConstraint: $timeConstraint,
                        timeGranularity: $timeGranularity,
                        order: $order,
                        limit: $limit,
                        cacheMode: $cacheMode,
                        asTable: $asTable,
                        addTimeSeries: $timeSeries,
                        resultFormat: $resultFormat
                    }
                ) {
                    id
                }
            }
            """
        )

        params = self.__common_query_input_params(
            model_key=model_key,
            metrics=metrics,
            dimensions=dimensions,
            where_constraint=where_constraint_str,
            time_constraint=time_constraint,
            time_granularity=time_granularity,
            order=order,
            limit=limit,
            cache_mode=cache_mode,
            as_table=as_table,
            time_series=time_series,
            result_format=result_format,
        )

        return self.execute(query, variable_values=params)["createMqlQuery"]["id"]

    def drop_cache(self) -> bool:  # noqa: D
        """Drops the entire MQL cache. Only do this is the cache is somehow corrupt."""
        query = gql(
            """
            mutation {
                dropCache(confirm: "yes") {
                    success
                }
            }
            """
        )

        return bool(self.execute(query)["dropCache"]["success"])

    def ping(self) -> requests.Response:  # noqa: D
        """Calls the MQL REST API for Health"""
        return requests.get(f"{self.rest_api_url}/health", headers=self.auth_header, timeout=10)

    def get_version(self) -> str:  # noqa: D
        query = gql(
            """
            query GetVersion {
                version
            }
            """
        )
        return self.execute(query)["version"]

    def get_health_report(self) -> Tuple[str, List[HealthReport]]:  # noqa: D
        """Calls the MQL Server GQL API for Health"""
        query = gql(
            """
            query GetHealthReport {
                version
                healthReport {
                    name
                    status
                    errorMessage
                }
            }
            """
        )

        query_result = self.execute(query)

        return query_result["version"], [HealthReport.from_gql_dict(h) for h in query_result["healthReport"]]

    def _get_materialization_result(self, query_id: str) -> Tuple[str, str]:
        """Retrieves query status given a query_id"""
        query = gql(
            """
            query GetMqlQueryResultsStatus($queryId: ID!) {
                mqlQuery(id: $queryId) {
                    resultTableSchema,
                    resultTableName
                }
            }
            """
        )

        query_result = self.execute(query, variable_values={"queryId": query_id})["mqlQuery"]
        return query_result["resultTableSchema"], query_result["resultTableName"]

    def explain_query_sql(self, query_id: str) -> str:
        """Retrieves the SQL generated by the MQL server for this query"""
        query = gql(
            """
            query SourceQuery($queryId: ID!) {
                sourceQuery(id: $queryId)
            }
            """
        )

        return self.execute(query, variable_values={"queryId": query_id})["sourceQuery"]

    def get_query_status(self, query_id: str) -> MqlQueryStatusResp:
        """Retrieves query status given a query_id"""
        query = gql(
            """
            query GetMqlQueryResultsStatus($queryId: ID!) {
                mqlQuery(id: $queryId) {
                    status
                    error
                    sql
                }
            }
            """
        )

        q = self.execute(query, variable_values={"queryId": query_id})["mqlQuery"]
        return MqlQueryStatusResp(
            query_id=query_id,
            status=MqlQueryStatus[q["status"]],
            error=q["error"] if q["error"] else None,
        )

    def get_queries(self, active_only: bool, limit: Optional[int]) -> Dict[str, Any]:  # type: ignore [misc]
        """Retrieves query status given a query_id"""
        query = gql(
            """
            query GetMqlQueries($activeOnly: Boolean, $limit: Int) {
                queries(activeOnly: $activeOnly, limit:$limit) {
                    id,
                    modelKey {
                        branch,
                        commit,
                    },
                    metrics,
                    dimensions,
                    status,
                }
            }
            """
        )
        # TODO: This should return MQLQueryStatus type
        return self.execute(query, variable_values={"activeOnly": active_only, "limit": limit})

    def get_logs_by_line(self, query_id: str, from_line: int, max_lines: int = 0) -> Tuple[str, int]:
        """Retrieves logs by log-file line number"""
        query = gql(
            """
            query GetLogLines($queryId: ID!, $fromLine: Int, $maxLines: Int) {
                mqlQuery(id: $queryId) {
                    logsByLine(fromLine: $fromLine, maxLines: $maxLines),
                }
            }
            """
        )
        variable_values = {"queryId": query_id, "fromLine": from_line, "maxLines": max_lines}
        logs = self.execute(query, variable_values=variable_values)["mqlQuery"]["logsByLine"]
        return logs, len(logs.split("\n"))

    def get_query_page_as_df(self, query_id: str, cursor: Optional[int] = None) -> QueryResult:
        """Retrieves a single page of a query's results and converts to a DataFrame"""
        cursor = cursor or 0
        query = gql(
            """
            query GetMqlQueryResultsTabular($queryId: ID!, $cursor: Int) {
                mqlQuery(id: $queryId) {
                    resultTabular(orient: TABLE, cursor: $cursor) {
                        nextCursor
                        data
                    }
                }
            }
            """
        )

        query_result = self.execute(query, variable_values={"queryId": query_id, "cursor": cursor})["mqlQuery"]
        tabular = query_result["resultTabular"]
        df = pd.read_json(
            base64.b64decode(tabular["data"].encode()),
            orient="table",
        )
        return QueryResult(
            df=df,
            cursor=tabular["nextCursor"],
        )

    def poll_for_query_completion(self, query_id: str, timeout: Optional[int] = None) -> MqlQueryStatusResp:
        """Poll for query completion, displaying some progress indication."""
        timeout = timeout if timeout is not None else DEFAULT_QUERY_TIMEOUT
        start = time.time()

        # Poll until the query is complete or we hit the timeout (timeout of 0 indicate no timeout)
        while timeout == 0 or time.time() < start + timeout:
            time.sleep(POLL_INTERVAL)
            query_result = self.get_query_status(query_id)
            if query_result.is_complete:
                return query_result

        raise QueryRuntimeException(
            query_id,
            f"Timeout reached waiting for query {query_id} to complete after {timeout} seconds. Please see --timeout option to override.",
        )

    def get_drop_materialization_result(self, query_id: str, timeout: Optional[int] = None) -> None:
        """Retrieves status of drop materialization query"""
        resp = self.poll_for_query_completion(query_id, timeout=timeout)
        if not resp.is_successful:
            raise QueryRuntimeException.from_query_response(resp)

    def get_materialization_result(self, query_id: str, timeout: Optional[int] = None) -> Tuple[str, str]:
        """Retrieves materialized table resulting from successful materialization query"""
        resp = self.poll_for_query_completion(query_id, timeout=timeout)
        if not resp.is_successful:
            raise QueryRuntimeException.from_query_response(resp)

        return self._get_materialization_result(query_id)

    def get_query_dataframe(self, query_id: str, timeout: Optional[int] = None) -> pd.DataFrame:
        """Retrieves query results in Pandas DataFrame format

        Includes logic for:
        1. Polling for completion
        2. Paging through results
        3. Converting JSON to a Pandas DataFrame
        """
        resp = self.poll_for_query_completion(query_id, timeout=timeout)
        if not resp.is_successful:
            raise QueryRuntimeException.from_query_response(resp)

        cursor: Optional[int] = 0
        df = pd.DataFrame()

        while cursor is not None:
            query_result = self.get_query_page_as_df(query_id, cursor)
            cursor = query_result.cursor
            df = df.append(query_result.df, ignore_index=True)

        return df
