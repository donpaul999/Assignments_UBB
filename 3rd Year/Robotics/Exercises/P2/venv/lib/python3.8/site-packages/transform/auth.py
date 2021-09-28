import os
import pathlib
import validators
import yaml

from dateutil import parser
from datetime import datetime
from typing import Dict, Optional

from .exceptions import AuthException, URLException
from .interfaces import MQLInterface, BackendInterface
from .models import User, Organization, ModelKey, UserState


class TransformAuth:  # noqa: D

    API_KEY_CONFIG_KEY = "api_key"
    MQL_OVERRIDE_CONFIG_KEY = "mql_server_url_override"
    BEARER_TOKEN_CONFIG_KEY = "bearer_token"
    BEARER_TOKEN_EXPIRES_AT_CONFIG_KEY = "bearer_token_expires_at"
    MQL_OVERRIDE_CONFIG_KEY = "mql_server_url_override"

    def __init__(  # noqa: D
        self, api_key: Optional[str] = None, mql_server_url: Optional[str] = None, override_config: bool = True
    ) -> None:
        self.__user_state: Optional[UserState] = None
        self.__mql_server_url: Optional[str] = None
        self.__mql_client: Optional[MQLInterface] = None
        self.__auth_header: Optional[Dict[str, str]] = None
        self.config = ConfigHandler(override_config)

        # Overrides
        if api_key is not None:
            self.api_key = api_key
        if mql_server_url is not None:
            self.mql_server_url_config_override = mql_server_url

        # Trigger auth
        self.auth_header

    @property
    def api_key(self) -> Optional[str]:
        """Pull API key from config file by default, also supporting a fallback environment variable TRANSFORM_API_KEY."""
        config_api_key = self.config._get_config_value(self.API_KEY_CONFIG_KEY)
        return config_api_key if config_api_key else os.getenv("TRANSFORM_API_KEY")

    @api_key.setter
    def api_key(self, api_key: str) -> None:
        """Set the users' API key and record to the config file."""

        # Validate API Key we expect a format like tfdk-prefix-secret
        # Do some very basic validation that this fits before setting key
        if not api_key.count("-") == 2:
            raise AuthException

        self.config._set_config_value(self.API_KEY_CONFIG_KEY, api_key)

    @property
    def bearer_token(self) -> Optional[str]:
        """Get bearer token from config, if exists."""
        token = self.config._get_config_value(self.BEARER_TOKEN_CONFIG_KEY)
        if (
            token is not None
            and self.bearer_token_expires_at is not None
            and parser.parse(self.bearer_token_expires_at) <= datetime.now()
        ):
            raise AuthException("Bearer token has expired")

        return token

    @bearer_token.setter
    def bearer_token(self, bearer_token: str) -> None:  # noqa: D
        self.config._set_config_value(self.BEARER_TOKEN_CONFIG_KEY, bearer_token)

    @property
    def bearer_token_expires_at(self) -> Optional[str]:
        """Time that current bearer token will expire, if exists."""
        return self.config._get_config_value(self.BEARER_TOKEN_EXPIRES_AT_CONFIG_KEY)

    @bearer_token_expires_at.setter
    def bearer_token_expires_at(self, bearer_token_expires_at: str) -> None:  # noqa: D
        self.config._set_config_value(self.BEARER_TOKEN_EXPIRES_AT_CONFIG_KEY, bearer_token_expires_at)

    @property
    def auth_header(self) -> Dict[str, str]:
        """Get auth header based on creds in config file. If none, raise exception."""
        if not self.__auth_header:
            if self.api_key is not None:
                self.__auth_header = {"Authorization": f"X-Api-Key {self.api_key}"}
            elif self.bearer_token is not None:
                self.__auth_header = {"Authorization": f"Bearer {self.bearer_token}"}
            else:
                raise AuthException

        return self.__auth_header

    @property
    def backend_client(self) -> BackendInterface:
        """Returns backend client"""
        return BackendInterface(auth_header=self.auth_header)

    @property
    def user_state(self) -> UserState:  # noqa: D
        if self.__user_state is None:
            self.__user_state = self.backend_client.get_user_state()
        return self.__user_state

    @property
    def org(self) -> Organization:  # noqa: D
        return self.user_state.organization

    @property
    def user(self) -> User:  # noqa: D
        return self.user_state.user

    @property
    def current_model(self) -> Optional[ModelKey]:  # noqa: D
        return self.user_state.current_model

    @property
    def is_authenticated(self) -> bool:  # noqa: D
        if self.api_key is not None or self.bearer_token is not None:
            try:
                self.user_state  # Will error if not authed with correct method
                return True
            except Exception:
                return False
        return False

    @property
    def mql_server_url_status(self) -> str:  # noqa: D
        return f"Your MQL server is {self.mql_server_url} " + (
            f"which is overriden via {self.config.config_file_path} from {self.user.mql_server_url}"
            if self.mql_server_url_config_override
            else ""
        )

    @property
    def mql_server_url(self) -> str:
        """The URL for the org's MQL server.

        Overrideable via passing MQL server url at instantiation process for users who need to redirect using a local proxy.
        """
        if self.mql_server_url_config_override:
            return self.mql_server_url_config_override
        if not self.__mql_server_url:
            self.__mql_server_url = self.user.mql_server_url
        return self.__mql_server_url

    @property
    def mql_server_url_config_override(self) -> Optional[str]:
        """Config override for the users' MQL server URL

        Overrideable via passing MQL server url at instantiation process for users who need to redirect using a local proxy.
        """
        return self.config._get_config_value(self.MQL_OVERRIDE_CONFIG_KEY)

    @mql_server_url_config_override.setter
    def mql_server_url_config_override(self, mql_server_url: str) -> None:  # noqa: D
        if not mql_server_url:
            self.config._remove_config_value(self.MQL_OVERRIDE_CONFIG_KEY)
        elif validators.url(mql_server_url, public=False):
            self.config._set_config_value(self.MQL_OVERRIDE_CONFIG_KEY, mql_server_url)
        else:
            raise URLException(mql_server_url)

    @property
    def mql_client(self) -> MQLInterface:  # noqa: D
        if not self.__mql_client:
            self.__mql_client = MQLInterface(query_server_url=self.mql_server_url, auth_header=self.auth_header)
        return self.__mql_client


class ConfigHandler:
    """Class for handling interactions with config.yml"""

    def __init__(self, override_config: bool = True) -> None:  # noqa: D
        # Check if the proper directory exists, if not create it
        if not os.path.exists(self.config_dir):
            cofig_dir = pathlib.Path(self.config_dir)
            cofig_dir.mkdir(parents=True)
        self.override_config = override_config

    @property
    def config_dir(self) -> str:
        """Retrieve Transform config_dir from $TFD_CONFIG_DIR, default config dir is ~/.transform"""
        config_dir_env = os.getenv("TFD_CONFIG_DIR")
        return config_dir_env if config_dir_env else f"{os.getenv('HOME')}/.transform"

    @property
    def config_file_path(self) -> str:
        """Config file can be found at <config_dir>/config.yml"""
        return os.path.join(self.config_dir, "config.yml")

    def _get_config_value(self, key: str) -> Optional[str]:
        try:
            config_file = open(self.config_file_path, "r")
        except FileNotFoundError:
            return None

        config = yaml.load(config_file, Loader=yaml.Loader)
        if config and key in config and config[key]:
            return config[key]

        return None

    def _set_config_value(self, key: str, value: str) -> None:
        # If the caller passed a falsey value, assume they'd like to remove the key
        if not self.override_config:
            return  # Don't override config if flagged
        if not value:
            return self._remove_config_value(key)

        configs: Dict[str, str] = {}
        if os.path.exists(self.config_file_path):
            with open(self.config_file_path) as f:
                configs = yaml.load(f, Loader=yaml.SafeLoader) or {}

        configs[key] = value

        with open(self.config_file_path, "w") as f:
            yaml.dump(configs, f)

    def _remove_config_value(self, key: str) -> None:
        if not self.override_config:
            return  # Don't override config if flagged
        configs: Dict[str, str] = {}
        if os.path.exists(self.config_file_path):
            with open(self.config_file_path) as f:
                configs = yaml.load(f, Loader=yaml.SafeLoader) or {}

        del configs[key]

        with open(self.config_file_path, "w") as f:
            yaml.dump(configs, f)
