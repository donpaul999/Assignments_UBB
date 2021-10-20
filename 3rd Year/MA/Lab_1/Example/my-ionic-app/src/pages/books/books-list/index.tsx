import { IonCard } from "@ionic/react";
import { Book } from "../../../accessors/types";
import styles from "./books-list.module.scss";

interface Props {
    books: Book[]
    onClick?: (book: Book) => void;
}

const NewBookList = ({ books, onClick }: Props) => {
    console.log();
    return (<>
        {books ? books.map(book => (
            <IonCard
                key={book.id}
                className={styles.itemContainer}
                onClick={() => onClick && onClick(book)}>
                <div className={styles.itemDetails}>
                    <div className={styles.row}>
                        <div>
                            <strong>Name:</strong> {book.name}
                        </div>
                        <div>
                            <strong>Author:</strong> {book.name}
                        </div>
                    </div>
                    <div className={styles.row}>
                        <div>
                            <strong>Id:</strong> {book.id}
                        </div>
                        <div>
                            <strong>Publish date:</strong> {book.publishDate}
                        </div>
                    </div>
                    <div className={styles.row}>
                        <div>
                            <strong>Is the book available?</strong> {book.isBooked === "false" ? "Yes" : "No"}
                        </div>
                    </div>
                </div>
            </IonCard>
        )) : (
            <div className={styles.notFound}>No books found</div>
        )}
    </>);
}

export default NewBookList;
