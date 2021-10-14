import { createContext } from "react";
import { makeAutoObservable, runInAction } from "mobx";
import { EMPTY_LOGIN_USER, LoginUser } from "../../../accessors/types";
import { login } from "../../../accessors/account-accessor";
import { isString } from "../../../shared/type-helpers";
import { AuthorizedStore } from "../../../infrastructure/authorized/authorized-store";

export class LoginStore {
    public user: LoginUser = EMPTY_LOGIN_USER;
    public isLoading = false;
    public errorMessage = "";

    constructor() {
        makeAutoObservable(this);
    }

    public setEmail = (email: string) => this.user.email = email;

    public setPassword = (password: string) => this.user.password = password;

    public login = async (authorizedStore: AuthorizedStore) => {
        this.isLoading = true;
        let error = "";

        try {
            await login(this.user);
        } catch (exception) {
            error = "Email or password is incorrect, try again!";
        } finally {
            runInAction(() => {
                authorizedStore.checkAuthorization();
                this.errorMessage = error;
                this.isLoading = false;
            });
        }
    }
    public reset = () => {
        this.user = EMPTY_LOGIN_USER;
        this.isLoading = false;
        this.errorMessage = "";
    }
}

export const loginStore = new LoginStore();
export const LoginContext = createContext(loginStore);
