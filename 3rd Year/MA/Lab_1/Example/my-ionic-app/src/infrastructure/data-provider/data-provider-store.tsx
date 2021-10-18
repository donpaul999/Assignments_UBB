import { makeAutoObservable, runInAction } from "mobx";
import { createContext } from "react";
import { BuildWebSocket, toastServiceStore } from "..";
import { addBook, updateBook, deleteBook, getAvailableBooks } from "../../accessors/book-accessors";
import { Book } from "../../accessors/types";
import { addToList, removeFromList, updateInList } from "../../shared/template-list-helpers";

export class DataProviderStore {
    public availableBooks: Book[] = []
    public relatedBooks: Book[] = []
    private isInitialized = false
    private unsubscribe = () => {};

    constructor() {
        makeAutoObservable(this);
    }

    public addBook = async (book: Book) => addBook(book);

    public updateBook = async (book: Book) => updateBook(book);

    public deleteBook = (book: Book) => deleteBook(book.id);

    initialize = () => {
        if (this.isInitialized) {
            return () => {};
        }
        // this.isInitialized = true;
        return this.getBooks();
    }

    private getBooks = () => {
        this.getAvailableBooks();
        this.getRelatedBooks();
        return this.subscribeToChanges();
    }

    private subscribeToChanges = async () => {
        const unsubscribe = await BuildWebSocket()
            .onCreate(this.handleCreateChange)
            .onUpdate(this.handleUpdateChange)
            .onDelete(this.handleDeleteChange)
            .connect();

        runInAction(() => this.unsubscribe = unsubscribe);
    }

    public unsubscribeToChanges = () => {
        try {
            this.unsubscribe();
        } catch {}
    }

    private getAvailableBooks = async () => {
        const availableBooks = await getAvailableBooks();
        runInAction(() => {
            this.availableBooks = availableBooks;
        });
    }

    private getRelatedBooks = () => {

    }

    private handleCreateChange = (book: Book) => {
        this.availableBooks = addToList(this.availableBooks, book);

        toastServiceStore.showInfo(<>
            New book (<strong> {book.name} </strong>) added
        </>);
    }

    private handleUpdateChange = (book: Book) => {
        const [updatedList, bookToUpdate] =
            updateInList(this.availableBooks, book, ({ id }) => book.id === id);
        if (!bookToUpdate) {
            return;
        }

        this.availableBooks = updatedList;

        let newName = <></>;
        if (bookToUpdate.name !== book.name) {
            newName = <> to <strong>{book.name}</strong></>;
        }

        toastServiceStore.showInfo(<>
            The book <strong>{bookToUpdate.name}</strong> was updated{newName}
        </>);
    }

    private handleDeleteChange = (book: Book) => {
        this.availableBooks = removeFromList(this.availableBooks, ({ id }) => book.id === id);;

        toastServiceStore.showInfo(<>
            The book <strong>{book.name}</strong> was removed from the list
        </>);
    }
}

export const dataProviderStore = new DataProviderStore();
export const DataProviderContext = createContext(dataProviderStore);
