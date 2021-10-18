import { Book } from "../../accessors/types";

export type WebSocketListener = (book: Book) => void;

export enum ChangeType {
    Create = 0,
    Update = 1,
    Delete = 2
}

export interface Change {
    type: ChangeType;
    payload: Book;
}
