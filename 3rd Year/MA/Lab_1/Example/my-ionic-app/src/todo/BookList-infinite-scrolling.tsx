import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonCard,
  IonToolbar, useIonViewWillEnter,
  IonInfiniteScroll, IonInfiniteScrollContent, IonItem, IonLabel, IonList, IonIcon, IonFabButton, IonFab, IonLoading
} from '@ionic/react';
import React, {useContext, useState} from 'react';
import Book from "./Book";
import {getBooksCount, newWebSocket} from "./bookApi";
import {BookProps} from "./BookProps";
import {RouteComponentProps} from "react-router";
import {getLogger} from "../core";
import {add} from "ionicons/icons";
import {ItemContext} from "./BookProvider";

const log = getLogger('BookListInf');

var startID = 0;
const counter = 10;
const BookListInf: React.FC<RouteComponentProps> = ({ history }) => {
  const [newBooks, setItems] = useState<BookProps[]>([]);
  const { books, fetching, fetchingError } = useContext(ItemContext);

  const [disableInfiniteScroll, setDisableInfiniteScroll] = useState<boolean>(false);

  async function fetchData() {
    log(disableInfiniteScroll)
    const result = await getBooksCount(startID, counter);
    log(startID);
    if (result.length > 0) {
      setItems([...newBooks, ...result]);
      setDisableInfiniteScroll(result.length < counter);
    } else {
      setDisableInfiniteScroll(true);
    }
  }

  useIonViewWillEnter(async () => {
    await fetchData();
  });

  async function searchNext($event: CustomEvent<void>) {
    startID += counter;
    await fetchData();

    ($event.target as HTMLIonInfiniteScrollElement).complete();
  }

  return (
      <IonPage>
        <IonHeader>
          <IonToolbar>
            <IonTitle>Tab One</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonContent>
          <IonList>
            <IonItem>
              <IonLabel>Name</IonLabel>
              <IonLabel>Author</IonLabel>
              <IonLabel>Publish Date</IonLabel>
              <IonLabel>Availability</IonLabel>
            </IonItem>
          </IonList>
          <IonList>
            {newBooks.map(({ id, name, author, publishDate, isBooked}) =>
                <Book key={id} id={id} name={name} author={author} publishDate={publishDate} isBooked={isBooked} onEdit={id => history.push(`/book/${id}`)} />)}
          </IonList>

          <IonInfiniteScroll threshold="100px" disabled={disableInfiniteScroll}
                             onIonInfinite={(e: CustomEvent<void>) => searchNext(e)}>
            <IonInfiniteScrollContent
                loadingText="Loading more good doggos...">
            </IonInfiniteScrollContent>
          </IonInfiniteScroll>
          <IonFab vertical="bottom" horizontal="end" slot="fixed">
            <IonFabButton onClick={() => history.push('/book')}>
              <IonIcon icon={add} />
            </IonFabButton>
          </IonFab>
        </IonContent>
      </IonPage>
  );
};

export default BookListInf;
