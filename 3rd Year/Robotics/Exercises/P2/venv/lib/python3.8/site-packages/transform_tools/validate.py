import logging
import json
import os
import requests
import sys
import yaml
from typing import Optional, Dict

EXPECTED_TF_CONFIG_FILE_NAMES = ["tdfconfig.yml", "tdfconfig.yaml", "validate_configs.yaml", "commit_configs.yaml"]
TRANSFORM_API_URL = "https://web-api.prod.transformdata.io"
UPLOAD_MODE_VALIDATE = "validate"
UPLOAD_MODE_COMMIT = "commit"
# TODO: Return error response so this constant doesn't have to be passed to the CLI
ERROR_RESPONSE_PREFIX = "Error response: "

logger = logging.getLogger(__name__)


def read_config_files(config_dir: str):
    """Read yaml files from config_dir. Returns (file name, file contents) per file in dir"""
    assert os.path.exists(config_dir), f"User-specified config dir ({config_dir}) does not exist"

    results = {}
    for path, _folders, filenames in os.walk(config_dir):
        for fname in filenames:
            if not (fname.endswith(".yml") or fname.endswith(".yaml")):
                continue

            # ignore transform config
            if fname in EXPECTED_TF_CONFIG_FILE_NAMES:
                continue

            with open(os.path.join(path, fname), "r") as f:
                filepath = os.path.abspath(os.path.join(path, fname))
                results[filepath] = f.read()
                try:
                    yaml.safe_load_all(results[filepath])
                except yaml.parser.ParserError as e:
                    raise yaml.parser.ParserError(
                        f"Invalid yaml in config file at path: {filepath}. {ERROR_RESPONSE_PREFIX}'{e}'"
                    )
                except Exception as e:
                    raise Exception(f"Failed loading yaml config file. {ERROR_RESPONSE_PREFIX}{e}")

                results[fname] = f.read()

    return results


def upload_configs(  # noqa: D
    mode: str,
    auth_header: Dict[str, str],
    repo: Optional[str] = None,
    branch: Optional[str] = None,
    commit: Optional[str] = None,
    config_dir: Optional[str] = None,
    api_url: Optional[str] = None,
) -> requests.Response:

    config_dir_to_use = config_dir or "."

    yaml_files = read_config_files(config_dir_to_use)
    results = {"yaml_files": yaml_files}
    logger.info(f"Files to upload: {yaml_files.keys()}")
    headers = {**{"Content-Type": "application/json"}, **auth_header}
    url = api_url or TRANSFORM_API_URL
    verify = url.startswith("https")

    # because people like to put slashes in their branch names
    if "/" in branch:
        branch = branch.replace("/", "__")  # dunder, for readability... to the extent it matters

    add_files_url = f"{url}/api/v1/model/{repo}/{branch}/{commit}/add_model_files"
    logger.info(f"add_files_url: {add_files_url}")
    logger.info("Uploading config files")
    r = requests.post(add_files_url, data=json.dumps(results).encode("utf-8"), headers=headers, verify=verify)
    if r.status_code != 200:
        raise Exception(f"Failed uploading config yaml files. {ERROR_RESPONSE_PREFIX}{r.text}")

    if mode == UPLOAD_MODE_VALIDATE:
        validate_url = f"{url}/api/v1/model/{repo}/{branch}/{commit}/validate_model"
        logger.info(f"validate_url: {validate_url}")
        logger.info("Checking that uploaded configs are valid and form a valid model")
        r = requests.post(validate_url, headers=headers, verify=verify)
        if r.status_code != 200:
            raise Exception(f"{ERROR_RESPONSE_PREFIX}{r.text}")
        logger.info("Successfully validated configs")
    elif mode == UPLOAD_MODE_COMMIT:
        commit_url = f"{url}/api/v1/model/{repo}/{branch}/{commit}/commit_model"
        logger.info(f"commit_url: {commit_url}")
        logger.info("Committing model")
        r = requests.post(commit_url, headers=headers, verify=verify)
        if r.status_code != 200:
            raise Exception(f"{ERROR_RESPONSE_PREFIX}{r.text}")
        logger.info("Successfully committed configs")
    else:
        raise ValueError(f"Invalid upload mode ({mode}). Expected '{UPLOAD_MODE_VALIDATE}' or '{UPLOAD_MODE_COMMIT}'")

    return r


if __name__ == "__main__":
    mode = sys.argv[1]
    if mode != UPLOAD_MODE_VALIDATE and mode != UPLOAD_MODE_COMMIT:
        raise ValueError(f"Invalid upload mode ({mode}) passed via args.")

    # Retrieve git info and API key from env
    REPO = os.getenv("REPO")
    # remove github org from repo
    if REPO:
        REPO = "/".join(REPO.split("/")[1:])

    if os.getenv("GITHUB_HEAD_REF") == "":
        BRANCH = os.getenv("GITHUB_REF").lstrip("/refs/heads/")
    else:
        BRANCH = os.getenv("GITHUB_HEAD_REF")

    API_URL = os.getenv("TRANSFORM_API_URL", TRANSFORM_API_URL)
    COMMIT = os.getenv("GITHUB_SHA")
    TRANSFORM_CONFIG_DIR = os.getenv("TRANSFORM_CONFIG_DIR")
    if TRANSFORM_CONFIG_DIR:
        TRANFSFORM_CONFIG_DIR = TRANSFORM_CONFIG_DIR.lstrip().rstrip()
    TRANSFORM_API_KEY = os.environ["TRANSFORM_API_KEY"].lstrip().rstrip()  # fail if TRANSFORM_API_KEY not present
    auth_header = {"Authorization": f"X-Api-Key {TRANSFORM_API_KEY}"}

    upload_configs(mode, auth_header, REPO, BRANCH, COMMIT, TRANSFORM_CONFIG_DIR, API_URL)
    print("success")
