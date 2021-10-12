import { IonSpinner } from "@ionic/react";
import { Backdrop } from "@mui/material";
import styles from "./loading-overlay.module.scss";
import React from "react";

interface Props {
    show: boolean;
}

const LoadingOverlay = ({ show }: Props) => (
    <Backdrop className={styles.elevatedLoadingOverlay} open={show} >
        <IonSpinner />
    </Backdrop>
);

export default LoadingOverlay;
