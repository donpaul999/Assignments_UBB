import React, { useContext, useEffect, useState } from 'react';
import {
  IonButton,
  IonButtons,
  IonContent,
  IonDatetime,
  IonHeader,
  IonInput, IonItem, IonLabel,
  IonLoading,
  IonPage, IonSelectOption, IonSelect,
  IonTitle,
  IonToolbar,
  useIonAlert
} from '@ionic/react';
import { getLogger } from '../core';
import { ItemContext } from './BookProvider';
import { RouteComponentProps } from 'react-router';
import { BookProps } from './BookProps';
import {removeBook} from "./bookApi";

const log = getLogger('ItemEdit');

interface BookEditProps extends RouteComponentProps<{
  id?: string;
}> {}

const BookEdit: React.FC<BookEditProps> = ({ history, match }) => {
  const { books, saving, savingError, saveBook } = useContext(ItemContext);
  const [name, setName] = useState('');
  const [author, setAuthor] = useState('');
  const [publishDate, setPublishDate] = useState('');
  const [isBooked, setIsBooked] = useState('');
  const [book, setItem] = useState<BookProps>();
  const [present] = useIonAlert();
  useEffect(() => {
    log('useEffect');
    const routeId = match.params.id || '';
    log(books);
    log(routeId);
    // @ts-ignore
    const book = books?.find(it => it.id.toString() === routeId);
    log(book);
    setItem(book);
    if (book) {
      setName(book.name);
      setAuthor(book.author);
      setPublishDate(book.publishDate.substr(0, 10));
      setIsBooked(book.isBooked);
    }
  }, [match.params.id, books]);
  const handleSave = () => {
    const editedItem = book ? { ...book, name, author, publishDate, isBooked } : { name, author, publishDate, isBooked};
    saveBook && saveBook(editedItem).then(() => { history.push("/books"); });
  };
  const handleRemove = () => {
    const removeItem = book ? { ...book, name, author, publishDate, isBooked } : { name, author, publishDate, isBooked};
    log(removeItem);
    removeBook && removeBook(removeItem).then(() => { history.push("/books") });
  };
  log('render');
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Edit</IonTitle>
          <IonButtons slot="end">
            <IonButton onClick={handleSave}>
              Save
            </IonButton>
          </IonButtons>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonLabel>Name</IonLabel>
        <IonInput class="ion-margin-bottom" value={name} onIonChange={e => setName(e.detail.value || '')} />
        <IonLabel>Author</IonLabel>
        <IonInput class="ion-margin-bottom" value={author} onIonChange={e => setAuthor(e.detail.value || '')} />
        <IonLabel>Publish Date</IonLabel>
        <IonDatetime class="custom-button ion-margin-bottom" value={publishDate} onIonChange={e => {
          e.detail.value = e.detail.value + " ";
          setPublishDate(e.detail.value.substr(0, 10) || '');
        }} />
        <IonItem>
          <IonLabel>Is already booked?</IonLabel>
          <IonSelect value={isBooked} placeholder="Select One" onIonChange={e => setIsBooked(e.detail.value)}>
            <IonSelectOption value="true">Yes</IonSelectOption>
            <IonSelectOption value="false">No</IonSelectOption>
          </IonSelect>
        </IonItem>
        <IonLoading isOpen={saving} />
        {savingError && (
          <div>{savingError.message || 'Failed to save book'}</div>
        )}
        <IonButton color="danger"
                   onClick={() =>
                       present({
                         cssClass: 'my-css',
                         header: 'Alert',
                         message: 'Are you sure you want to delete this book?',
                         buttons: [
                           'Cancel',
                           { text: 'Yes', handler: () => {handleRemove()} },
                         ],
                       })
                   }>Delete</IonButton>

      </IonContent>
    </IonPage>
  );
};

export default BookEdit;
