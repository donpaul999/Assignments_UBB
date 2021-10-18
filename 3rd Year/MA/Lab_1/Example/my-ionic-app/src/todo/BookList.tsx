import React, { useContext } from 'react';
import { RouteComponentProps } from 'react-router';
import {
    IonContent,
    IonFab,
    IonFabButton,
    IonHeader,
    IonIcon, IonItem, IonLabel,
    IonList, IonLoading,
    IonPage,
    IonTitle,
    IonToolbar
} from '@ionic/react';
import { add } from 'ionicons/icons';
import Book from './Book';
import { getLogger } from '../core';
import { ItemContext } from './BookProvider';

const log = getLogger('ItemList');

const BookList: React.FC<RouteComponentProps> = ({ history }) => {
    const { books, fetching, fetchingError } = useContext(ItemContext);
    console.log(books);
    log('render');
    return (
        <IonPage>
            <IonHeader>
                <IonToolbar>
                    <IonTitle>Book a book</IonTitle>
                </IonToolbar>
            </IonHeader>
            <IonContent>
                <IonLoading isOpen={fetching} message="Fetching items" />
                <IonList>
                    <IonItem>
                        <IonLabel>Name</IonLabel>
                        <IonLabel>Author</IonLabel>
                        <IonLabel>Publish Date</IonLabel>
                        <IonLabel>Availability</IonLabel>
                    </IonItem>
                </IonList>
                {books && (
                    <IonList>
                        {books.map(({ id, name, author, publishDate, isBooked}) =>
                            <Book key={id} id={id} name={name} author={author} publishDate={publishDate} isBooked={isBooked} onEdit={id => history.push(`/book/${id}`)} />)}
                    </IonList>
                )}
                {fetchingError && (
                    <div>{fetchingError.message || 'Failed to fetch items'}</div>
                )}
                <IonFab vertical="bottom" horizontal="end" slot="fixed">
                    <IonFabButton onClick={() => history.push('/book')}>
                        <IonIcon icon={add} />
                    </IonFabButton>
                </IonFab>
            </IonContent>
        </IonPage>
    );
};

export default BookList;
