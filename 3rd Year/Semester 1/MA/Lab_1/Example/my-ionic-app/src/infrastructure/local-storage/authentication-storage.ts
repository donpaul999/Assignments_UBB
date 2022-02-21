import { LocalStorage } from "..";

interface StorageType {
    token: string;
    userId: string;
}

const AUTHENTICATION_STORAGE_KEY = "authentication_storage";

export const set = (token: string, userId: string) =>
    LocalStorage.set<StorageType>(AUTHENTICATION_STORAGE_KEY, { token, userId });

export const get = async () => LocalStorage.get<StorageType>(AUTHENTICATION_STORAGE_KEY);

export const clear = () => LocalStorage.remove(AUTHENTICATION_STORAGE_KEY);
