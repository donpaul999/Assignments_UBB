import os
import time
import git
import hashlib

from typing import Tuple

LOCAL = "local"
DEFAULT_QUERY_TIMEOUT = 180
POLL_INTERVAL = 1.0


def is_git_repo(path: str = None) -> bool:
    """Check is a path is a git repo"""
    try:
        git.Repo(path, search_parent_directories=True)
        return True
    except git.exc.InvalidGitRepositoryError:
        return False
    return False


def get_git_info(path: str = None) -> Tuple[str, str, str]:
    """Attempts to retrieve git repo info"""
    repo = git.Repo(path, search_parent_directories=True)  # will throw an error if this is not a git repo

    # NOTE: we are taking the name of the directory as the name of the repo
    # TODO: better handling of detached state
    repo_name = os.path.split(os.path.realpath(os.path.dirname(repo.common_dir)))[-1]
    branch = repo.active_branch.name if not repo.head.is_detached else f"detached_{repo.head.object.hexsha}"
    commit = repo.head.object.hexsha

    return repo_name, branch, commit


def local_commit_info() -> Tuple[str, str, str]:
    """Create a pseudo-git setup for validation from a non-git repo"""
    hasher = hashlib.sha1()
    hasher.update(str(time.time()).encode("utf-8"))
    commit = str(hasher.hexdigest())
    return LOCAL, LOCAL, commit
