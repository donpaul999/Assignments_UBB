export interface LoginUser {
    email: string;
    password: string;
}

export interface RegisterUser extends LoginUser {
    confirmPassword: string;
}

export interface LoginResponse {
    token: string;
    expiration: string;
}

export const EMPTY_LOGIN_USER: LoginUser = {
    email: "",
    password: ""
}
