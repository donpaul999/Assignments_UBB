import { IonContent, IonImg, IonPage, IonTitle } from "@ionic/react";
import { Fab, IconButton, Tab, Tabs } from "@mui/material";
import { Box } from "@mui/system";
import React, { useContext } from "react";
import SwipeableViews from "react-swipeable-views";
import AddIcon from '@mui/icons-material/AddSharp';
import styles from "./index.module.scss";
import { ToastService, WithDataProvider, withDataProvider } from "../../infrastructure";
import NewBookList from "./books-list";
import { MainPageContext } from "./books-store";
import NewBookEdit from "./book-edit";
import { observer } from "mobx-react";
import SignOutIcon from '@mui/icons-material/NoAccountsSharp';
import InfoIcon from '@mui/icons-material/InfoSharp';

const IndexPage = ({ availableBooks }: WithDataProvider) => {
    console.log(availableBooks);
    const {
        selectedTab,
        bookToEdit,
        setSelectedTab,
        showAddDialog,
        showEditDialog,
        closeDialog,
        signOut
    } = useContext(MainPageContext);

    return (
        <IonPage>
            <IonContent fullscreen>
                <div className={styles.pageContainer}>
                    <NewBookList
                        books={availableBooks}
                        onClick={showEditDialog}
                    />
                    <Fab
                        className={styles.addButton}
                        onClick={() => showAddDialog()}>
                        <AddIcon color="primary" />
                    </Fab>
                    <ToastService />
                    <NewBookEdit
                        initialBook={bookToEdit}
                        onClose={closeDialog} />
                </div>
            </IonContent>
        </IonPage>
    );
}

export default withDataProvider(observer(IndexPage));
