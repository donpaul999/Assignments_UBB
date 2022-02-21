import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle
} from "@mui/material";

interface Props {
    isOpen: boolean;
    onCancel: () => void;
    onConfirm: () => void;
    title: string;
    message?: string;
    cancelButtonText?: string;
    confirmButtonText?: string;
}

const ConfirmationDialog = ({
                                isOpen,
                                onCancel,
                                onConfirm,
                                title,
                                message,
                                cancelButtonText = "Cancel",
                                confirmButtonText = "Confirm"
                            }: Props) => (
    <Dialog
        open={isOpen}
        onClose={onCancel}>
        <DialogTitle>{title}</DialogTitle>
        {!!message && (
            <DialogContent>
                <DialogContentText>{message}</DialogContentText>
            </DialogContent>
        )}
        <DialogActions>
            <Button onClick={onCancel}>{cancelButtonText}</Button>
            <Button onClick={onConfirm}>{confirmButtonText}</Button>
        </DialogActions>
    </Dialog>
);

export default ConfirmationDialog;
