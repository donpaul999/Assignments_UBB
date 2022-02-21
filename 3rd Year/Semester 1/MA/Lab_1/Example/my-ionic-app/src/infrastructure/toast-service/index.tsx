import { SnackbarProvider, useSnackbar } from "notistack";
import { useContext, useEffect } from "react";
import { ToastServiceContext } from "./toast-service-store";

const ToastService = () => {
    const { enqueueSnackbar } = useSnackbar();
    const { setEnqueue } = useContext(ToastServiceContext);

    useEffect(() => {
        setEnqueue(enqueueSnackbar);
    }, [enqueueSnackbar, setEnqueue]);

    return null;
}

export default () => (
    <SnackbarProvider
        maxSnack={3}
        anchorOrigin={{
            vertical: "bottom",
            horizontal: "center"
        }}>
        <ToastService />
    </SnackbarProvider>
);
