import { Storage } from "@capacitor/storage";

const AUTHENTICATION_TOKEN_KEY = "authentication_token";

export const setAuthenticationToken = (token: string) => Storage.set({
    key: AUTHENTICATION_TOKEN_KEY,
    value: token
});

export const getAuthenticationToken = async () => {
    const tokenResult = await Storage.get({ key: AUTHENTICATION_TOKEN_KEY });

    return tokenResult.value || "";
}

export const hasAuthenticationToken = async () => {
    const token = await getAuthenticationToken();

    return !!token;
}

export const clearToken = () => Storage.remove({ key: AUTHENTICATION_TOKEN_KEY });
