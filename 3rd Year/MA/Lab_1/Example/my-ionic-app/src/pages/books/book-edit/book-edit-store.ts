import { makeAutoObservable, runInAction, toJS } from "mobx";
import { createContext } from "react";
import { Book, EMPTY_BOOK } from "../../../accessors/types";
import { addBook, deleteBook, updateBook } from "../../../accessors/book-accessors";
import { toastServiceStore } from "../../../infrastructure";

export class BookEditStore {
    public book: Book = EMPTY_BOOK;
    public isAdd: boolean = false;
    public showCloseConfirmation: boolean = false;
    public showDeleteConfirmation: boolean = false;

    constructor() {
        makeAutoObservable(this);
    }

    public initializeBook = (initialBook: Book | null) => {
        if (!initialBook) {
            return;
        }

        if (initialBook.id === 0) {
            this.isAdd = true;
            this.book = EMPTY_BOOK;
            return;
        }

        this.isAdd = false;
        this.book = toJS(initialBook);
    }

    public setName = (name: string) => this.book.name = name;

    public setAuthor = (author: string) => this.book.author = author;

    public setPublishDate = (publishDate: string) =>
        this.book.publishDate = publishDate;

    public setBooked = (isBooked: boolean) => this.book.isBooked = isBooked ? "true" : "false";

    public canSave = () => this.book.name && this.book.author &&
        this.book.publishDate;

    public saveBook = async () => {
        const api = this.isAdd ? addBook : updateBook;

        try {
            await api(this.book);
        } catch {
            toastServiceStore.showError("Something went wrong, the server could not save the book, try again!");
            return false;
        }

        toastServiceStore.showSuccess("Operation successful!");
        return true;
    }

    public deleteBook = async () => {
        try {
            await deleteBook(this.book.id);
        } catch {
            toastServiceStore.showError("Something went wrong, the server could not delete the book, try again!");
            return false;
        }

        toastServiceStore.showSuccess("Operation successful!");
        return true;
    }

    public setCloseConfirmation = (show: boolean) => this.showCloseConfirmation = show;

    public setDeleteConfirmation = (show: boolean) => this.showDeleteConfirmation = show;
}

export const bookEditStore = new BookEditStore();
export const BookEditContext = createContext(bookEditStore);
