import { LocalStorage } from "../infrastructure";
import { API_PATH_AUTHENTICATE, BASE_HTTP_URL } from "./constants";
import { httpPost } from "./helper-functions";
import { LoginResponse, LoginUser, RegisterUser } from "./types";

const BASE_ACCOUNT_URL = BASE_HTTP_URL + API_PATH_AUTHENTICATE;

export const login = async (user: LoginUser) => {
    const { token } = await httpPost<LoginResponse>(`${BASE_ACCOUNT_URL}/login`, user);

    LocalStorage.setAuthenticationToken(token);
}

export const register = async (user: RegisterUser) => {
    const response = await httpPost(`${BASE_ACCOUNT_URL}/register`, user);

    if (response) throw response;
}
