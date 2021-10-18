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

export const EMPTY_REGISTER_USER: RegisterUser = {
    ...EMPTY_LOGIN_USER,
    confirmPassword: ""
}

export interface Book {
    id: number;
    name: string;
    author: string;
    publishDate: string;
    isBooked: boolean;
}

export const EMPTY_CAR: Book = {
    id: 0,
    name: "",
    author: "",
    publishDate: "0000-00-00",
    isBooked: false
}
