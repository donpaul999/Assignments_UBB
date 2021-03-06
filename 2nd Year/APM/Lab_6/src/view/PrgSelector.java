package view;

import com.sun.jdi.ByteType;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ADT.*;
import model.PrgState;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.Repository;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

public class PrgSelector {
    @FXML
    private ListView<Controller> programsListView;

    @FXML
    private Button startProgramButton;

    @FXML
    public void initialize() {
        programsListView.setItems(getControllerList());
        programsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void startProgram(ActionEvent event) {
        Controller controller = programsListView.getSelectionModel().getSelectedItem();
        try {
            controller.typecheckOriginalProgram();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PrgExecutor.fxml"));
            PrgExecutor execController = new PrgExecutor(controller);
            loader.setController(execController);
            StackPane executorRoot = loader.load();
            Scene executorScene = new Scene(executorRoot, 600, 400);
            Stage executorStage = new Stage();
            executorStage.setScene(executorScene);
            executorStage.setTitle("Program Execution");
            executorStage.show();
        } catch (StmtException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("StmtException Error");
            alert.setHeaderText("A StmtException error has occured!");
            alert.setContentText(e.toString());

            alert.showAndWait();
        } catch (ExprException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ExprException Error");
            alert.setHeaderText("A ExprException error has occured!");
            alert.setContentText(e.toString());

            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("IOException Error");
            alert.setHeaderText("A IOException error has occured!");
            alert.setContentText(e.toString());

            alert.showAndWait();
        }
    }

    public MyList<IStmt> getStmtList() {
        IStmt example_1 = new CompStmt(
                new VarDeclStmt("x", new IntType()),
                new CompStmt(
                        new AssignStmt("x", new ValueExp(new IntValue(17))),
                        new PrintStmt(new VarExp("x"))
                )
        );

        IStmt example_2 = new CompStmt(
                new VarDeclStmt("x", new IntType()),
                new CompStmt(new AssignStmt("x", new ArithExp(
                        new ValueExp(new IntValue(3)),
                        new ArithExp(
                                new ValueExp(new IntValue(5)), new ValueExp(new IntValue(7)), '*'
                        ),
                        '+'
                )
                ),
                        new PrintStmt(new VarExp("x"))
                )
        );


        IStmt example_3 = new CompStmt(
                new VarDeclStmt("s", new BoolType()),
                new CompStmt(new VarDeclStmt("x", new IntType()),
                        new CompStmt(
                                new AssignStmt("s", new ValueExp(new BoolValue(true))),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("s"),
                                                new AssignStmt("x", new ValueExp(new IntValue(20))),
                                                new AssignStmt("x", new ValueExp(new IntValue(2)))
                                        ),
                                        new PrintStmt(new VarExp("x"))
                                )
                        )
                )
        );

        IStmt example_4 = new CompStmt(
                new VarDeclStmt("fileName", new StringType()),
                new CompStmt(new AssignStmt("fileName", new ValueExp(new StringValue("test.txt"))),
                        new CompStmt(new OpenRFileStmt(new VarExp("fileName")),
                                new CompStmt(new VarDeclStmt("x", new IntType()),
                                        new CompStmt(new ReadFileStmt(new VarExp("fileName"), "x"),
                                                new CompStmt(new PrintStmt(new VarExp("x")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("fileName"), "x"),
                                                                new CompStmt(new PrintStmt(new VarExp("x")),
                                                                        new CloseRFileStmt(new VarExp("fileName"))))))))));


        IStmt example_5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                        new ValueExp(new IntValue(5)),
                                                        '+')))))));


        IStmt example_6 = new CompStmt(new VarDeclStmt("x", new IntType()),
                new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(10))),
                        new WhileStmt(new RelationalExp(new VarExp("x"), new ValueExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("x")), new AssignStmt("x", new ArithExp(new VarExp("x"), new ValueExp(new IntValue(1)), '-')))
                        )));


        IStmt example_7 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));


        MyList<IStmt> statementList = new MyList<IStmt>();
        statementList.add(example_1);
        statementList.add(example_2);
        statementList.add(example_3);
        statementList.add(example_4);
        statementList.add(example_5);
        statementList.add(example_6);
        statementList.add(example_7);
        return statementList;
    }

    private ObservableList<Controller> getControllerList() {
        MyList<IStmt> statements = getStmtList();
        LinkedList<Controller> list = new LinkedList<Controller>();
        for (int i = 0; i < statements.size(); ++i) {
            try {
                IMyStack<IStmt> stack = new MyStack<>();
                PrgState prg = new PrgState(stack, new MyDictionary<String, Value>(),  new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(),  statements.get(i));
                Repository repo = new Repository(prg, "log" + String.valueOf(i + 1) + ".txt");
                Controller controller = new Controller(repo);
                list.add(controller);
            }
            catch (MyException | ADTException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return FXCollections.observableArrayList(list);
    }


}
