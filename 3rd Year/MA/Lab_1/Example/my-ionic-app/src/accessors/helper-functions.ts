let token = "";

export const setToken = (tokenValue: string) => token = tokenValue;

export const hasToken = () => !!token;

export const post = <T>(url: string, body?: any) => new Promise<T>(async (resolve, reject) => {
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(body)
        });
        if (response.status / 100 === 2) {
            resolve(await response.json());
        } else {
            reject(response.status)
        }
    } catch (exception) {
        reject("Could not connect to the server!");
    }
});
