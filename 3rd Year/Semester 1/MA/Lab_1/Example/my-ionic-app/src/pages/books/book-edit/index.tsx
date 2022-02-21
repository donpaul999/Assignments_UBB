import { forwardRef, useContext, useEffect } from "react";
import { withDataProvider, WithDataProvider } from "../../../infrastructure";
import CloseIcon from "@mui/icons-material/CloseSharp";
import CheckIcon from "@mui/icons-material/CheckSharp";
import DeleteIcon from "@mui/icons-material/DeleteOutlineSharp";
import { Book } from "../../../accessors/types";
import styles from "./book-edit.module.scss";
import { BookEditContext } from "./book-edit-store";
import { observer } from "mobx-react";
import ConfirmationDialog from "../../../components/confirmation-dialog";
import {
    AppBar,
    Button,
    Checkbox,
    Dialog,
    Fab,
    FormControl,
    FormControlLabel,
    IconButton,
    InputLabel,
    MenuItem,
    Select,
    Slide,
    TextField,
    Toolbar,
    Typography
} from "@mui/material";

interface Props extends WithDataProvider {
    initialBook: Book | null;
    onClose: () => void;
}

const Transition = forwardRef(function Transition(props: any, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});

const NewBookEdit = ({ initialBook, onClose }: Props) => {
    const {
        book,
        isAdd,
        showCloseConfirmation,
        showDeleteConfirmation,
        initializeBook,
        setAuthor,
        setName,
        setPublishDate,
        setBooked,
        canSave,
        saveBook,
        deleteBook,
        setCloseConfirmation,
        setDeleteConfirmation
    } = useContext(BookEditContext);

    useEffect(() => initializeBook(initialBook),
        [initializeBook, initialBook]);

    const handleSave = async () => {
        if (await saveBook()) {
            onClose();
        }
    }

    const handleDelete = async () => {
        if (await deleteBook()) {
            onClose();
        }
    }

    return (
        <Dialog
            fullScreen
            open={initialBook !== null}
            onClose={() => setCloseConfirmation(true)}
            TransitionComponent={Transition}>
            <AppBar
                sx={{ position: "relative" }}
                color="inherit">
                <Toolbar>
                    <IconButton
                        onClick={() => setCloseConfirmation(true)}
                        color="primary">
                        <CloseIcon />
                    </IconButton>
                    <Typography
                        sx={{
                            ml: 2,
                            flex: 1
                        }}
                        variant="h6"
                        color="black"
                        component="div">
                        {isAdd ? "Add new book" : `Edit ${initialBook?.name} by ${initialBook?.author}`}
                    </Typography>
                    <IconButton
                        onClick={handleSave}
                        color="secondary"
                        disabled={!canSave()}>
                        <CheckIcon />
                    </IconButton>
                </Toolbar>
            </AppBar>
            <div className={styles.container}>
                <TextField
                    label="Name"
                    required
                    className={styles.input}
                    value={book.name}
                    onChange={e => setName(e.target.value)} />
                <TextField
                    label="Author"
                    required
                    className={styles.input}
                    value={book.author}
                    onChange={e => setAuthor(e.target.value)} />
                <TextField
                    label="Publish date"
                    InputLabelProps={{ shrink: true }}
                    required
                    type="date"
                    className={styles.input}
                    onChange={e => setPublishDate(e.target.value)} />
                <FormControlLabel
                    label="Is it booked?"
                    control={
                        <Checkbox
                            color="secondary"
                            checked={book.isBooked == "true"}
                            onChange={e => setBooked(e.target.checked)} />
                    } />
            </div>
            {!isAdd && (
                <Fab
                    className={styles.deleteButton}
                    onClick={() => setDeleteConfirmation(true)}>
                    <DeleteIcon />
                </Fab>
            )}
            <ConfirmationDialog
                isOpen={showCloseConfirmation}
                onCancel={() => setCloseConfirmation(false)}
                onConfirm={() => {
                    onClose();
                    setCloseConfirmation(false);
                }}
                title="Closing the edit form"
                message="Are you sure you want to close the form? All the introduced data will be lost." />
            <ConfirmationDialog
                isOpen={showDeleteConfirmation}
                onCancel={() => setDeleteConfirmation(false)}
                onConfirm={() => {
                    handleDelete();
                    setDeleteConfirmation(false);
                }}
                title={`Deleting book ${initialBook?.name} by ${initialBook?.author}`}
                message="Are you sure you want to delete this book? The action cannot be undone and the data will be lost." />
        </Dialog>
    );
}

export default withDataProvider(observer(NewBookEdit));
