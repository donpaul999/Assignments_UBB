import { makeAutoObservable } from "mobx";
import { createContext } from "react";
import { hasToken } from "../../accessors/helper-functions";

export class AuthorizedStore {
    public isAuthorized = false;

    constructor() {
        makeAutoObservable(this);
    }

    public checkAuthorization = () => this.isAuthorized = hasToken();
}

export const authorizedStore = new AuthorizedStore();
export const AuthorizedContext = createContext(authorizedStore); 
