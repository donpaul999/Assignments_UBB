import React from 'react';
import { IonItem, IonLabel } from '@ionic/react';
import { BookProps } from './BookProps';

interface BookPropsExt extends BookProps {
  onEdit: (id?: string) => void;
}

const Book: React.FC<BookPropsExt> = ({ id, name, author, publishDate, isBooked, onEdit }) => {
  return (
    <IonItem onClick={() => onEdit(id)}>
        <IonLabel>{name}</IonLabel>
        <IonLabel>{author}</IonLabel>
        <IonLabel>{publishDate}</IonLabel>
        <IonLabel>{isBooked === "true" ? "This book is not available" : "This book is available"}</IonLabel>
    </IonItem>
  );
};

export default Book;
