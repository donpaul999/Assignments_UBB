package view;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;

import controller.Controller;
import exception.ExeFinished;
import exception.MyException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.PrgState;
import model.adt.FileTable;
import model.statement.IStmt;
import model.value.IValue;
import model.value.StringValue;

public class PrgRunController implements Initializable {

    Controller myController;
    @FXML
    Label nrPrgStates;
    @FXML
    Button runButton;
    @FXML
    TableView<HashMap.Entry<Integer, IValue>> heapTable;
    @FXML
    TableColumn<HashMap.Entry<Integer,IValue>, String> heapTableAddress;
    @FXML
    TableColumn<HashMap.Entry<Integer,IValue>, String> heapTableValue;
    @FXML
    ListView<String> outList;
    @FXML
    TableView<HashMap.Entry<StringValue, BufferedReader>> fileTable;
    @FXML
    TableColumn<HashMap.Entry<StringValue, BufferedReader>, String> fileTableIdentifier;
    @FXML
    TableColumn<HashMap.Entry<StringValue, BufferedReader>, String> fileTableFileName;
    @FXML
    ListView<String> prgStateList;
    @FXML
    TableView<HashMap.Entry<String, IValue>> symTable;
    @FXML
    TableColumn<HashMap.Entry<String, IValue>, String> symTableVariable;
    @FXML
    TableColumn<HashMap.Entry<String, IValue>, String> symTableValue;
    @FXML
    ListView<String> exeStackList;

    public PrgRunController(Controller myController) {
        this.myController = myController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialRun();
        prgStateList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setSymTableAndExeStack();
            }
        });
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                try {
                    myController.allStepGUI();
                } catch (ExeFinished e1) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Toy Language - Current program finished");
                    alert.setHeaderText(null);
                    alert.setContentText("Program successfully finished!");
                    Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    confirm.setDefaultButton(false);
                    confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                    alert.showAndWait();
                    Stage stage = (Stage) heapTable.getScene().getWindow();
                    stage.close();
                }
                catch (MyException e3) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Toy Language - Warning" + e3.getClass().toString());
                    alert.setHeaderText(null);
                    alert.setContentText(e3.getMessage());
                    Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    confirm.setDefaultButton(false);
                    confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                    alert.showAndWait();
                    Stage stage = (Stage) heapTable.getScene().getWindow();
                    stage.close();
                }
                catch (RuntimeException e2) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Toy Language - Warning" + e2.getClass().toString());
                    alert.setHeaderText(null);
                    alert.setContentText(e2.getMessage());
                    Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    confirm.setDefaultButton(false);
                    confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                    alert.showAndWait();
                    Stage stage = (Stage) heapTable.getScene().getWindow();
                    stage.close();
                }
                updateUIComponents();
            }
        });
    }

    public void initialRun() {
        setNumberLabel();
        setHeapTable();
        setOutList();
        setFileTable();
        setPrgStateList();
        prgStateList.getSelectionModel().selectFirst();
        setSymTableAndExeStack();
    }

    public void updateUIComponents() {
        setNumberLabel();
        setHeapTable();
        setOutList();
        setFileTable();
        setPrgStateList();
        if(prgStateList.getSelectionModel().getSelectedItem() == null) {
            prgStateList.getSelectionModel().selectFirst();
        }
        setSymTableAndExeStack();
    }

    public void setNumberLabel() {
        nrPrgStates.setText("The number of PrgStates: " + myController.getRepository().getPrgList().size());
    }

    public void setHeapTable() {
        ObservableList<HashMap.Entry<Integer, IValue>> heapTableList = FXCollections.observableArrayList();
        heapTableAddress.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getKey())));
        heapTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        heapTableList.addAll(myController.getRepository().getPrgList().get(0).getHeap().getContent().entrySet());
        heapTable.setItems(heapTableList);
        heapTable.refresh();
    }

    public void setOutList() {
        ObservableList<String> outObservableList = FXCollections.observableArrayList();
        for(IValue e : myController.getRepository().getPrgList().get(0).getOut().getList()) {
            outObservableList.add(e.toString());
        }
        outList.setItems(outObservableList);
    }

    public void setFileTable() {
        ObservableList<HashMap.Entry<StringValue, BufferedReader>> fileTableList = FXCollections.observableArrayList();
        //fileTableIdentifier.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getKey())));
        fileTableFileName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKey().toString()));
        fileTableList.addAll(myController.getRepository().getPrgList().get(0).getFileTable().getDictionary().entrySet());
        fileTable.setItems(fileTableList);
        fileTable.refresh();
    }

    public void setPrgStateList() {
        ObservableList<String> prgStateIdList = FXCollections.observableArrayList();
        for(PrgState e : myController.getRepository().getPrgList()) {
            prgStateIdList.add(Integer.toString(e.getId()));
        }
        prgStateList.setItems(prgStateIdList);
    }

    public void setSymTableAndExeStack() {
        ObservableList<HashMap.Entry<String, IValue>> symTableList = FXCollections.observableArrayList();
        ObservableList<String> exeStackItemsList = FXCollections.observableArrayList();
        symTableVariable.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKey()));
        symTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));

        List<PrgState> allPrgs = myController.getRepository().getPrgList();
        PrgState prgResult = null;
        for(PrgState e : allPrgs) {
            if(e.getId() == Integer.parseInt(prgStateList.getSelectionModel().getSelectedItem())) {
                prgResult = e;
                break;
            }
        }
        if(prgResult != null) {
            symTableList.addAll(prgResult.getSymTable().getDictionary().entrySet());
            for(IStmt e : prgResult.getExeStack().getStack()) {
                exeStackItemsList.add(e.toString());
            }
            symTable.setItems(symTableList);
            symTable.refresh();
            exeStackList.setItems(exeStackItemsList);
        }
    }

}
