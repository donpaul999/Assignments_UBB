import { IonContent, IonPage, IonTitle } from "@ionic/react";
import { Alert, AlertTitle, Button, IconButton, TextField } from "@mui/material";
import classNames from "classnames";
import { observer } from "mobx-react";
import ArrowBack from "@mui/icons-material/ArrowBackSharp";
import styles from "./register.module.scss";
import { useContext, useEffect } from "react";
import { RegisterContext } from "./register-store";
import { useHistory } from "react-router";
import React from "react";

const Register = () => {
    const { goBack } = useHistory();

    const {
        user,
        errorMessage,
        setEmail,
        setPassword,
        setConfirmPassword,
        register,
        reset
    } = useContext(RegisterContext);

    useEffect(() => {
        return reset;
    }, []);

    const arePasswordsDifferent = user.password !== user.confirmPassword;

    return (
        <IonPage>
            <IonContent fullscreen>
                <div className={styles.mainContainer}>
                    <IconButton className={styles.backButton} onClick={goBack}>
                        <ArrowBack color="primary" />
                    </IconButton>
                    <div className={styles.centeredContainer}>
                        <IonTitle className={classNames(styles.logInText, styles.largeText)}>
                            Register
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
                            helperText="Should contain a small letter, capital letter, digit and special symbol"
                            className={styles.inputPassword}
                            value={user.password}
                            onChange={e => setPassword(e.target.value)} />
                        <TextField
                            label="Confirm password"
                            type="password"
                            className={styles.inputPassword}
                            value={user.confirmPassword}
                            helperText={arePasswordsDifferent && "The passwords are different!"}
                            error={arePasswordsDifferent}
                            onChange={e => setConfirmPassword(e.target.value)} />
                        {errorMessage && (
                            <Alert
                                severity="error"
                                className={styles.alertContainer}>
                                <AlertTitle><strong>Error</strong></AlertTitle>
                                {errorMessage}
                            </Alert>
                        )}
                        <Button
                            variant="contained"
                            color="secondary"
                            className={styles.registerButton}
                            disabled={!user.email || !user.password || !user.confirmPassword}
                            onClick={register}>
                            Create account
                        </Button>
                    </div>
                </div>
            </IonContent>
        </IonPage>
    );
}

export default observer(Register);
