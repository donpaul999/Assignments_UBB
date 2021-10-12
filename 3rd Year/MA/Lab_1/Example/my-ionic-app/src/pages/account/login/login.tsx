import { IonContent, IonImg, IonPage, IonTitle } from "@ionic/react";
import styles from "./login.module.scss";
import classNames from "classnames";
import { Alert, AlertTitle, Button, TextField } from "@mui/material";
import { useContext } from "react";
import { LoginContext } from "./login-store";
import { LoadingOverlay } from "../../../components";
import { observer } from "mobx-react";
import { AuthorizedContext } from "../../../infrastructure";
import React from "react";

const Login = () => {
    const authorizedStore = useContext(AuthorizedContext);
    const {
        user,
        setEmail,
        setPassword,
        isLoading,
        errorMessage,
        login
    } = useContext(LoginContext);

    return (
        <IonPage>
            <IonContent fullscreen>
                <div className={styles.centeredContainer}>
                    <div className={classNames(styles.row, styles.largeText)}>
                        <IonTitle className={styles.applicationTitle}>Book a Book</IonTitle>
                    </div>
                    <IonTitle className={classNames(styles.logInText, styles.largeText)}>
                        Log in
                    </IonTitle>
                    <TextField
                        label="Email"
                        type="email"
                        className={styles.inputEmail}
                        value={user.email}
                        onChange={e => setEmail(e.target.value)} />
                    <TextField
                        label="Password"
                        type="password"
                        className={styles.inputPassword}
                        value={user.password}
                        onChange={e => setPassword(e.target.value)} />
                    {errorMessage && (
                        <Alert
                            severity="error"
                            className={styles.alertContainer}>
                            <AlertTitle><strong>Error</strong></AlertTitle>
                            {errorMessage}
                        </Alert>
                    )}
                    <div className={classNames(styles.row)}>
                        <Button className={styles.registerButton}>Register</Button>
                        <Button
                            variant="contained"
                            color="secondary"
                            disabled={!user.email || !user.password}
                            onClick={() => login(authorizedStore)}>
                            Log in
                        </Button>
                    </div>
                </div>
                <LoadingOverlay show={isLoading} />
            </IonContent>
        </IonPage>
    );
}

export default observer(Login);
