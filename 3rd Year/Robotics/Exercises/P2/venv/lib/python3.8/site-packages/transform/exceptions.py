import time

from typing import Optional

from .models import MqlQueryStatusResp


class QueryRuntimeException(Exception):
    """Internal Exception type to surface query execution errors to the user"""

    query_id: str
    msg: Optional[str]
    start_time: Optional[float]

    def __init__(self, query_id: str, msg: Optional[str], start_time: Optional[float] = None) -> None:  # noqa: D
        self.msg = msg
        self.query_id = query_id
        self.start_time = start_time
        super().__init__(self.msg or "")

    def __str__(self) -> str:  # noqa: D
        if self.msg:
            msg = self.msg
        else:
            msg = "Failure 🧐 - query failed for unknown reason"
            if self.start_time:
                msg += f" after {time.time() - self.start_time:.2f} seconds..."
        return (
            msg
            + "\n💡 See MQL server logs for more details:\n"
            + f"\tmql stream-query-logs --query-id {self.query_id}\n"
        )

    @classmethod
    def from_query_response(cls, resp: MqlQueryStatusResp) -> "QueryRuntimeException":  # noqa: D
        if not resp.is_complete or resp.is_successful:
            raise Exception("Unable to build exception from unfailed query")
        return cls(query_id=resp.query_id, msg=resp.error)


class AuthException(Exception):
    """Exception to handle unauthorized access"""

    def __init__(self, msg: Optional[str] = None) -> None:  # noqa: D
        self.msg = msg
        super().__init__(self.msg or "")

    def __str__(self) -> str:  # noqa: D
        if self.msg:
            msg = self.msg
        else:
            msg = "Unable to find authentication"
        return (
            msg
            + "\n\tPlease retrieve an API key at https://app.transformdata.io/api_keys or run `mql setup` for multi-factor authentication"
        )


def URLException(Exception):
    """Invalid URL"""

    def __init__(self, url: str) -> None:  # noqa: D
        self.url = url

    def __str__(self) -> str:  # noqa: D
        return f"Detected invalid URL entry: {self.url}. Please try again."
