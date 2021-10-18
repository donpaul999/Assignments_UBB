import { makeAutoObservable, runInAction } from "mobx";
import { createContext } from "react";
import { LocalStorage } from "..";

export class AuthorizedStore {
    public isAuthorized: boolean | null = null;

    constructor() {
        makeAutoObservable(this);
    }

    public initialize = () => {
        if (this.isAuthorized === null) {
            this.checkAuthorization();
        }
    }

    public checkAuthorization = async () => {
        const isAuthorized = await LocalStorage.hasAuthenticationToken();

        runInAction(() => this.isAuthorized = isAuthorized);
    }
}

export const authorizedStore = new AuthorizedStore();
export const AuthorizedContext = createContext(authorizedStore);
