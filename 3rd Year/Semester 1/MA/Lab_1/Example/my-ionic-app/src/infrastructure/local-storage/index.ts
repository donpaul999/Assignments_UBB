import { Storage } from "@capacitor/storage";

export const set = <T>(key: string, value: T) => Storage.set({
    key,
    value: JSON.stringify(value)
});

export const get = async <T>(key: string): Promise<T | null> => {
    const result = await Storage.get({ key });

    return result.value === null ? null : JSON.parse(result.value);
}

export const remove = (key: string) => Storage.remove({ key });
