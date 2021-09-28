import os
import ast
import logging
from datetime import datetime
from typing import Callable, List, Optional

import click

from ..models import CacheMode, INF, NEGATIVE_ONE, DEFAULT_LIMIT, TimeGranularity

DEFAULT_QUERY_TIMEOUT = 180
POLL_INTERVAL = 1.0


class PythonLiteralOption(click.Option):
    """Click class to decode a string literal into a Python object."""

    def type_cast_value(self, ctx, value):  # type: ignore
        """Cast to Python obj, throws exception if we are unable to cast"""

        # For non-required inputs we receive a None here, and should return it
        if value is None:
            return None
        try:
            return ast.literal_eval(value)
        except Exception:
            raise click.BadParameter(value)


def query_observability_options(function: Callable) -> Callable:
    """Options for display mql server log"""
    function = click.option(
        "--debug",
        required=False,
        is_flag=True,
        default=False,
        help="Enable showing query log in the terminal for debugging purpose",
    )(function)

    function = click.option(
        "--web",
        required=False,
        is_flag=True,
        default=False,
        help="Open the MQL server logs on the web",
    )(function)

    return function


def async_options(function: Callable) -> Callable:
    """Options for async commands"""
    function = click.option(
        "--detach",
        default=False,
        type=bool,
        help="Returns the created query ID to allow for asynchronous querying.",
    )(function)

    function = click.option(
        "-t",
        "--timeout",
        type=int,
        default=DEFAULT_QUERY_TIMEOUT,
        help="Sets the timeout to wait for query completion. Pass 0 to remove any timeout.",
    )(function)

    return function


def config_validation_options(function: Callable) -> Callable:
    """Common options when validating or committing configs"""
    function = click.option(
        "--config-dir",
        type=str,
        required=False,
        default=os.getcwd(),
        help="Path to directory containing Transform yaml models",
    )(function)
    return function


def query_options(function: Callable) -> Callable:
    """Common options for a query, adding support for limit, order, and cache-mode"""
    function = click.option(
        "--config-dir",
        type=str,
        required=False,
        help="Path to directory containing Transform yaml models to execute query against",
    )(function)

    function = click.option(
        "--cache-mode",
        type=click.Choice(
            list(map(lambda x: x.value, CacheMode)),
            case_sensitive=False,
        ),
        # Cast as CacheMode type
        callback=lambda ctx, param, value: CacheMode(value) if value is not None else None,
    )(function)

    function = click.option(
        "--order",
        required=False,
        multiple=True,
        help='Metrics or dimensions to order by ("-" in front of a column means descending). For example: --order -ds',
    )(function)

    function = click.option(
        "--limit",
        required=False,
        type=str,
        help="Limit the number of rows out(Default: 100) using an int or 'inf' for no limit. For example: --limit 100 or --limit inf",
    )(function)

    function = click.option(
        "--where",
        required=False,
        type=str,
        default=None,
        help="SQL-like where statement provided as a string. For example: --where \"ds = '2020-04-15'\"",
    )(function)

    function = click.option(
        "--time-constraint",
        required=False,
        type=str,
        default=None,
        help="Optional TimeConstraint on query (written as a WHERE clause, ie: `ds < '2020-01-01')",
    )(function)

    function = click.option(
        "--time-granularity",
        type=click.Choice(
            list(map(lambda x: x.value, TimeGranularity)),
            case_sensitive=False,
        ),
        # Cast as TimeGranularity type
        callback=lambda ctx, param, value: TimeGranularity(value) if value is not None else None,
    )(function)

    return function


def validate_query_args(limit: Optional[str]) -> None:
    """Logic to perform query validation before sending to the MQL Server"""
    if limit and not (limit.isnumeric() or limit in [INF, NEGATIVE_ONE]):
        click.echo(
            f"❌ limit must be an int (--limit {DEFAULT_LIMIT}) or {NEGATIVE_ONE} or {INF} (--limit {INF}) to specify no limit"
        )
        exit()


def metrics_and_dimensions_options(function: Callable) -> Callable:
    """TODO: could we validate that these exist using a callback?"""

    function = click.option(
        "--metrics",
        required=True,
        multiple=True,
        help="Metrics to query for: syntax is --metrics bookings. Submit a list of metrics by providing multiple --metrics flags.",
        callback=lambda ctx, param, value: __validate_and_normalize_query_inputs("--metrics ", value),
    )(function)
    function = click.option(
        "--dimensions",
        required=True,
        multiple=True,
        help="Dimensions to group by: syntax is --dimensions ds. Submit a list of metrics by providing multiple --dimensions flags.",
        callback=lambda ctx, param, value: __validate_and_normalize_query_inputs("--dimensions ", value),
    )(function)
    return function


def __validate_and_normalize_query_inputs(option_name: str, value: List[str]) -> List[str]:
    """A callback executed to normalize and validate query inputs for metrics and dimensions.

    TODO: Eventually we should validate that the metrics and dimensions actually exist on the org,
        avoiding a round trip and failed query. This would be a good place for that.
    """

    for v in value:
        # We have a comma-separated list of metrics or dimensions, explode this into a list and return
        if "," in v:
            return [i.strip() for i in v.split(",")]

    return value


def no_active_mql_servers(org_name: str) -> None:
    """Read out if there are no mql servers found"""
    click.echo(f"‼️  The {org_name} organization doesn't currently have an active MQL Server.")
    click.echo(
        "An admin can create a new MQL Server at:\n\n    https://app.transformdata.io/settings/org/mql",
    )


def valid_datetime(dt_str: str) -> bool:
    """Returns true if string is valid iso8601 timestamp, false otherwise"""
    try:
        datetime.fromisoformat(dt_str)
    except Exception:
        try:
            datetime.fromisoformat(dt_str.replace("Z", "+00:00"))
        except Exception:
            return False
        return True
    return True


def enable_debug_log_file() -> None:  # noqa: D
    # Writing to the console would be ideal, but the newlines get messed up so it's hard to read.
    # e.g. log line looks like: Waiting for query2021-07-27 10:19:40,051 - http.client ...
    logging.basicConfig(
        level=logging.DEBUG,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
        filemode="a",
        filename="debug.log",
    )


def get_query_logs_url_for_id(query_id: str) -> str:
    """Returns the query log URL to the web UI given the query_id"""
    return f"https://app.transformdata.io/mql/query/{query_id}?tab=server_logs"
