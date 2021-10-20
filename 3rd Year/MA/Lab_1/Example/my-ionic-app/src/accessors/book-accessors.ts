import { API_PATH_BOOKS, BASE_HTTP_URL } from "./constants";
import { httpDelete, httpGet, httpPost, httpPut } from "./helper-functions";
import { Book } from "./types";

const BASE_BOOK_URL = BASE_HTTP_URL + API_PATH_BOOKS;

export const getAllBooks = () => httpGet<Book[]>(BASE_BOOK_URL);

export const addBook = (book: Book) => httpPut(BASE_BOOK_URL, book);

export const updateBook = (book: Book) => httpPost(BASE_BOOK_URL, book);

export const deleteBook = (bookId: number) => httpDelete(`${BASE_BOOK_URL}/${bookId}`);

export const getAvailableBooks = () => httpGet<Book[]>(`${BASE_BOOK_URL}/available`);

export const getRelatedBooks = () => httpGet<Book[]>(`${BASE_BOOK_URL}/related`);
