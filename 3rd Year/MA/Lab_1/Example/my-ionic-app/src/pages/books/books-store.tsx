import { makeAutoObservable } from "mobx";
import { createContext } from "react";
import { Book, EMPTY_BOOK } from "../../accessors/types";
import { authorizedStore, AuthenticationStorage } from "../../infrastructure";

export class BooksStore {
    public selectedTab: number = 0;
    public bookToEdit: Book | null = null;

    constructor() {
        makeAutoObservable(this);
    }

    public setSelectedTab = (tabIndex: number) => this.selectedTab = tabIndex;

    public showAddDialog = () => this.bookToEdit = EMPTY_BOOK;

    public closeDialog = () => this.bookToEdit = null;

    public showEditDialog = (book: Book) => this.bookToEdit = book;

    public signOut = async () => {
        await AuthenticationStorage.clear();
        await authorizedStore.checkAuthorization();
    }
}

export const mainPageStore = new BooksStore();
export const MainPageContext = createContext(mainPageStore);
