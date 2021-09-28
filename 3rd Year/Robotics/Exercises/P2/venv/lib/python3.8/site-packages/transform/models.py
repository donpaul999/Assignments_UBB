import pandas as pd
import arrow

from typing import List, Optional, Any, Dict
from enum import Enum
from dataclasses import dataclass, field

INF = "inf"
NEGATIVE_ONE = "-1"
DEFAULT_LIMIT = "100"


@dataclass(frozen=True)
class ModelKey:
    """A model key. Represents a given point in time for the Transform configs."""

    id: int
    organization_id: int
    repository: str
    branch: str
    commit: str
    created_at_ts: str
    is_current: bool

    @property
    def created_at(self) -> str:
        """Converts a system timestamp to a human readable string, ex: 'an hour ago'"""
        return arrow.get(self.created_at_ts).humanize()

    @classmethod
    def from_gql_dict(cls, _input: Dict[str, str]) -> "ModelKey":  # noqa: D
        return cls(
            id=int(_input["id"]),
            organization_id=int(_input["organizationId"]),
            repository=_input["gitRepo"],
            branch=_input["gitBranch"],
            commit=_input["gitCommit"],
            created_at_ts=_input["createdAt"],
            is_current=bool(_input["isCurrent"]),
        )

    @classmethod
    def from_snake_dict(cls, _input: Dict[str, str]) -> "ModelKey":  # noqa: D
        return cls(
            id=int(_input["id"]),
            organization_id=int(_input["organization_id"]),
            repository=_input["repository"],
            branch=_input["branch"],
            commit=_input["commit"],
            created_at_ts=_input["created_at"],
            is_current=bool(_input["is_current"]),
        )


@dataclass(frozen=True)
class Organization:
    """Lightweight object to represent a Transform Organization."""

    id: int
    name: str
    primary_config_repo: str
    primary_config_branch: str

    @classmethod
    def from_gql_dict(cls, _input: Dict[str, str]) -> "Organization":  # noqa: D
        return cls(
            id=int(_input["id"]),
            name=_input["name"],
            primary_config_repo=_input["primaryConfigRepo"],
            primary_config_branch=_input["primaryConfigBranch"],
        )


@dataclass(frozen=True)
class User:
    """Lightweight object to represent a Transform User."""

    id: int
    user_name: str
    email: str
    mql_server_url: str

    @classmethod
    def from_gql_dict(cls, _input: Dict[str, str]) -> "User":  # noqa: D
        return cls(
            id=int(_input["id"]),
            user_name=_input["userName"],
            email=_input["email"],
            mql_server_url=_input["mqlServerUrl"],
        )


@dataclass(frozen=True)
class UserState:
    """Aggregation of the core models necessary for most operations"""

    user: User
    organization: Organization
    current_model: Optional[ModelKey]


@dataclass(frozen=True)
class Materialization:
    """Object to represent a Metric."""

    name: str
    metrics: List[str]
    dimensions: List[str]
    destination_table: Optional[str]

    @classmethod
    def from_gql_dict(cls, _input: Dict[str, Any]) -> "Materialization":  # type: ignore [misc] # noqa: D
        destination_table = None
        if "destination_table" in _input:
            destination_table = _input["destination_table"]
        return cls(
            name=_input["name"],
            metrics=_input["metrics"],
            dimensions=_input["dimensions"],
            destination_table=destination_table,
        )


@dataclass(frozen=True)
class Metric:
    """Object to represent a Metric."""

    name: str
    measures: List[str]
    dimensions: List[str]

    @classmethod
    def from_gql_dict(cls, _input: Dict[str, Any]) -> "Metric":  # type: ignore [misc] # noqa: D
        return cls(
            name=_input["name"],
            measures=_input["measures"],
            dimensions=_input["dimensions"],
        )


class MqlQueryStatus(Enum):
    """The status of queries submitted for execution in the query manager."""

    # Created and waiting to run.
    PENDING = "PENDING"
    RUNNING = "RUNNING"
    SUCCESSFUL = "SUCCESSFUL"
    # Handled exception prevented a query from being successful.
    FAILED = "FAILED"
    # Unhandled exception prevented a query from being successful.
    UNHANDLED_EXCEPTION = "UNHANDLED_EXCEPTION"
    # The given query ID is not known to exist - possibly expired.
    UNKNOWN = "UNKNOWN"
    TIMEOUT = "TIMEOUT"


@dataclass(frozen=True)
class MqlQueryStatusResp:  # noqa: D
    query_id: str
    status: MqlQueryStatus
    error: Optional[str]

    @property
    def is_complete(self) -> bool:
        """These statuses indicate the Query has completed execution"""
        return self.status in [
            None,
            MqlQueryStatus.SUCCESSFUL,
            MqlQueryStatus.FAILED,
            MqlQueryStatus.UNHANDLED_EXCEPTION,
        ]

    @property
    def is_successful(self) -> bool:
        """Indicate whether the Query has completed successfully"""
        return self.status == MqlQueryStatus.SUCCESSFUL

    @property
    def is_failed(self) -> bool:
        """Indicate whether the Query has completed successfully"""
        return self.status in [MqlQueryStatus.FAILED, MqlQueryStatus.UNHANDLED_EXCEPTION]


@dataclass(frozen=True)
class QueryResult:
    """Object for query results with a cursor"""

    df: pd.DataFrame
    cursor: Optional[int] = None


class CacheMode(Enum):
    """Different CacheMode options."""

    READ = "r"  # use cached datasets but do not write new datasets to cache
    READWRITE = "rw"  # use cached datasets and write new datasets to cache
    WRITE = "w"  # ignore cached datatsets and write new datasets to cache
    IGNORE = ""  # ignore cache completely, only use configuration files

    @classmethod
    def from_string(cls, string_value: str) -> "CacheMode":  # noqa: D
        if string_value == "wr":  # convenience rewire
            string_value = "rw"
        return cls(string_value)

    @classmethod
    def default(cls) -> "CacheMode":  # noqa: D
        return CacheMode.READWRITE


class TimeGranularity(Enum):
    """For time dimensions, the smallest possible difference between to time values.

    Needed for calculating adjacency when merging 2 different time ranges.
    """

    # Names are used in parameters to DATE_TRUC, so don't change them.
    # Values are used to convert user supplied string to enums.
    DAY = "day"
    WEEK = "week"
    MONTH = "month"
    QUARTER = "quarter"
    YEAR = "year"


@dataclass(frozen=True)
class MQLServer:
    """Object to represent a Org MQL Server."""

    id: int
    name: str
    url: str
    is_org_default: bool

    @classmethod
    def from_gql_dict(cls, _input: Dict[str, str]) -> "MQLServer":  # noqa: D
        return cls(
            id=int(_input["id"]),
            name=_input["name"],
            url=_input["url"],
            is_org_default=bool(_input["isOrgDefault"]),
        )


class HealthReportStatus(Enum):
    """Statuses for Health Reports."""

    SUCCESS = "SUCCESS"  # MQL Server responded as healthy
    FAIL = "FAIL"  # MQL Server responded as unhealthy


@dataclass(frozen=True)
class HealthReport:
    """Object to represent a MQL Server Health Report."""

    name: str
    status: str
    error_message: str

    @classmethod
    def from_gql_dict(cls, _input: Dict[str, str]) -> "HealthReport":  # noqa: D
        return cls(
            name=_input["name"],
            status=_input["status"],
            error_message=_input["errorMessage"],
        )


@dataclass(frozen=True)
class ServerHealthReport:
    """Object to represent the health of a data warehouse."""

    name: str
    status: str
    url: str
    version: str = ""
    error_message: str = ""
    servers: List[HealthReport] = field(default_factory=list)


@dataclass(frozen=True)
class MqlMaterializeResp:
    """Response object received from MQL from a successful Materialize request."""

    schema: Optional[str]
    table: Optional[str]

    @property
    def fully_qualified_name(self) -> str:  # noqa: D
        return f"{self.schema}.{self.table}"
