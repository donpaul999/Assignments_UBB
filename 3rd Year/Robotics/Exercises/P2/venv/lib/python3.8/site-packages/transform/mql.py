import pandas as pd
import requests

from typing import Any, Dict, List, Optional, Tuple

from .auth import TransformAuth
from .models import (
    CacheMode,
    HealthReportStatus,
    Materialization,
    Metric,
    MqlMaterializeResp,
    MqlQueryStatusResp,
    MQLServer,
    ModelKey,
    ServerHealthReport,
    TimeGranularity,
    UserState,
)
from .interfaces import MQLInterface


class MQLClient:
    """Wrapper that exposes MQLInterface and BackendInterface"""

    def __init__(self, api_key: Optional[str] = None, mql_server_url: Optional[str] = None) -> None:  # noqa: D
        self.context = TransformAuth(api_key, mql_server_url)

    def create_query(  # noqa: D
        self,
        metrics: List[str],
        dimensions: List[str],
        model_key_id: Optional[int] = None,
        where: Optional[str] = None,
        time_constraint: Optional[str] = None,
        time_granularity: Optional[TimeGranularity] = None,
        order: Optional[List[str]] = None,
        limit: Optional[str] = None,
        cache_mode: Optional[CacheMode] = None,
        as_table: Optional[str] = None,
    ) -> MqlQueryStatusResp:
        """Builds a query in MQL and returns a MqlQueryStatusResp detailing the results."""
        model_key = self.context.backend_client.get_model_key(model_key_id) if model_key_id else None
        query_id = self.context.mql_client.create_query(
            metrics,
            dimensions,
            model_key,
            where,
            time_constraint,
            time_granularity,
            order,
            limit,
            cache_mode,
            as_table,
        )
        return self.context.mql_client.get_query_status(query_id)

    def query(  # noqa: D
        self,
        metrics: List[str],
        dimensions: List[str],
        model_key_id: Optional[int] = None,
        where: Optional[str] = None,
        time_constraint: Optional[str] = None,
        time_granularity: Optional[TimeGranularity] = None,
        order: Optional[List[str]] = None,
        limit: Optional[str] = None,
        cache_mode: Optional[CacheMode] = None,
        as_table: Optional[str] = None,
    ) -> pd.DataFrame:
        """Builds a query in MQL and returns pandas dataframe of queried result."""
        model_key = self.context.backend_client.get_model_key(model_key_id) if model_key_id else None
        query_id = self.context.mql_client.create_query(
            metrics,
            dimensions,
            model_key,
            where,
            time_constraint,
            time_granularity,
            order,
            limit,
            cache_mode,
            as_table,
        )
        return self.context.mql_client.get_query_dataframe(query_id)

    def list_queries(self, active_only: bool, limit: Optional[int] = None) -> Dict[str, Any]:
        """Retrieve a list of queries from MQL server."""
        return self.context.mql_client.get_queries(active_only=active_only, limit=limit)

    def list_metrics(self, model_key_id: Optional[int] = None) -> List[Metric]:
        """Returns a list of Metricc."""
        model_key = self.context.backend_client.get_model_key(model_key_id) if model_key_id else None
        return sorted(self.context.mql_client.get_metrics(model_key), key=lambda m: m.name)

    def create_materialization(  # noqa: D
        self,
        materialization_name: str,
        start_time: Optional[str],
        end_time: Optional[str],
        model_key_id: Optional[int] = None,
        output_table: Optional[str] = None,
        timeout: Optional[int] = None,
    ) -> MqlQueryStatusResp:
        """Implements Materialize (new) mutation in MQL/GQL and returns MqlQueryStatusResp object. Can be very expensive if a large time range is provided.

        Note: Currently it just wraps mql_api.MQLCLient.create_materialization
        """
        model_key = self.context.backend_client.get_model_key(model_key_id) if model_key_id else None
        query_id = self.context.mql_client.create_materialization(
            materialization_name, start_time, end_time, model_key, output_table
        )
        if timeout:
            self.context.mql_client.poll_for_query_completion(query_id, timeout)
        return self.context.mql_client.get_query_status(query_id)

    def materialize(  # noqa: D
        self,
        materialization_name: str,
        start_time: Optional[str],
        end_time: Optional[str],
        model_key_id: Optional[int] = None,
        output_table: Optional[str] = None,
    ) -> MqlMaterializeResp:
        """Builds materilization and returns MqlMaterializeResp object. Can be very expensive if a large time range is provided."""
        model_key = self.context.backend_client.get_model_key(model_key_id) if model_key_id else None
        query_id = self.context.mql_client.create_materialization(
            materialization_name, start_time, end_time, model_key, output_table
        )
        schema, table = self.context.mql_client.get_materialization_result(query_id)
        return MqlMaterializeResp(table=table, schema=schema)

    def drop_materialization(  # noqa: D
        self,
        materialization_name: str,
        start_time: Optional[str],
        end_time: Optional[str],
        model_key_id: Optional[int] = None,
        timeout: Optional[int] = None,
    ) -> MqlQueryStatusResp:
        """Implements drop materialization mutation in MQL/GQL. Can be very expensive if a large time range is provided.

        Note: Current it just wraps mql_api.MQLClient.drop_materialization
        """
        model_key = self.context.backend_client.get_model_key(model_key_id) if model_key_id else None
        query_id = self.context.mql_client.drop_materialization(materialization_name, start_time, end_time, model_key)
        if timeout:
            self.context.mql_client.poll_for_query_completion(query_id, timeout)
        return self.context.mql_client.get_query_status(query_id)

    def list_materializations(self, model_key_id: Optional[int] = None) -> List[Materialization]:
        """List the materializations for the Organization with their available metrics and dimensions."""
        model_key = self.context.backend_client.get_model_key(model_key_id) if model_key_id else None
        return sorted(self.context.mql_client.get_materializations(model_key), key=lambda m: m.name)

    def commit_configs(self, config_dir: str) -> ModelKey:
        """Validates and commits yaml transform configs in the provided directory. Returns a ModelKey object."""
        return self.context.backend_client.commit_configs(config_dir)

    def validate_configs(self, config_dir: str) -> Tuple[str, str, str]:
        """Finds and validates yaml transform configs in the provided directory. Returns a tuple (repo, branch, commit)"""
        return self.context.backend_client.validate_configs(config_dir)

    def health_report(self) -> List[ServerHealthReport]:
        """Performs a health check on all mql servers within the organization and returns List[ServerHealthReport]."""
        servers = self.context.backend_client.get_org_mql_servers()
        result = []
        for s in servers:
            test_server = MQLInterface(self.context.auth_header, s.url)
            try:
                version, health = test_server.get_health_report()
                result.append(
                    ServerHealthReport(
                        name=s.name, status=HealthReportStatus.SUCCESS, url=s.url, version=version, servers=health
                    )
                )
            except Exception as e:  # noqa: D
                result.append(
                    ServerHealthReport(
                        name=s.name,
                        status=HealthReportStatus.FAIL,
                        url=s.url,
                        error_message="Unable to connect to MQL server",
                    )
                )
        return result

    def drop_cache(self) -> bool:
        """Drops the entire MQL cache. Only do this is the cache is somehow corrupt. Returns boolean about operation result."""
        return self.context.mql_client.drop_cache()

    def identify(self) -> UserState:
        """Returns a UserState object describing current user."""
        return self.context.user_state

    def ping(self) -> requests.Response:
        """Returns ping response from currently selected MQL server."""
        return self.context.mql_client.ping()

    def list_servers(self) -> List[MQLServer]:
        """Returns a list of MQL servers within the organization."""
        return self.context.backend_client.get_org_mql_servers()
