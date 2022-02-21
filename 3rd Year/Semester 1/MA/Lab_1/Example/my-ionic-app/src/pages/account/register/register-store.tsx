import { createContext } from "react";
import { makeAutoObservable, runInAction } from "mobx";
import { EMPTY_REGISTER_USER, RegisterUser } from "../../../accessors/types";
import { register } from "../../../accessors/account-accessor";
import { isString } from "../../../shared/type-helpers";

export class RegisterStore {
    public user: RegisterUser = EMPTY_REGISTER_USER;
    public isLoading = false;
    public errorMessage = "";

    constructor() {
        makeAutoObservable(this);
    }

    public setEmail = (email: string) => this.user.email = email;

    public setPassword = (password: string) => this.user.password = password;

    public setConfirmPassword = (confirmPassword: string) =>
        this.user.confirmPassword = confirmPassword;

    public register = async () => {
        this.isLoading = true;
        let error = "";

        try {
            await register(this.user);
        } catch (exception: any) {
            if (isString(exception)) {
                error = exception;
            } else {
                error = exception === 409 ?
                    "The email is already used by another user!" :
                    "The email is not valid or the password does not meet the requirements, try again!";
            }
        } finally {
            runInAction(() => {
                this.errorMessage = error;
                this.isLoading = false;
            });
        }
    }

    public reset = () => {
        this.user = EMPTY_REGISTER_USER;
        this.isLoading = false;
        this.errorMessage = "";
    }
}

export const registerStore = new RegisterStore();
export const RegisterContext = createContext(registerStore);
