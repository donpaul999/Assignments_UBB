import { createContext } from "react";
import { SnackbarMessage, OptionsObject } from "notistack";

type EnqueueSnackbar =
    (message: SnackbarMessage, options?: OptionsObject | undefined) => void;

export class ToastServiceStore {
    private enqueue: EnqueueSnackbar = () => {};

    setEnqueue = (enqueue: EnqueueSnackbar) => this.enqueue = enqueue;

    public showError = (message: JSX.Element | string) => this.enqueue(message, {
        variant: "error"
    });

    public showWarning = (message: JSX.Element | string) => this.enqueue(message, {
        variant: "warning"
    });

    public showInfo = (message: JSX.Element | string) => this.enqueue(message, {
        variant: "info"
    });

    public showSuccess = (message: JSX.Element | string) => this.enqueue(message, {
        variant: "success"
    });
}

export const toastServiceStore = new ToastServiceStore();
export const ToastServiceContext = createContext(toastServiceStore);
