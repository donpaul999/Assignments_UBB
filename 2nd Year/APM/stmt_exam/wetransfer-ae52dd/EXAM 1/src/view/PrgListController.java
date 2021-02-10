package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.Controller;
import exception.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.PrgState;
import model.adt.*;
import model.exp.*;
import model.statement.*;
import model.statement.files.CloseRFileStmt;
import model.statement.files.OpenRFileStmt;
import model.statement.files.ReadFileStmt;
import model.statement.heap.HeapAllocStmt;
import model.statement.heap.HeapWriteStmt;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import repository.IRepository;
import repository.Repository;

public class PrgListController implements Initializable {

    static Controller ctr1,ctr2,ctr3,ctr4,ctr5,ctr6,ctr7,ctr8,ctr9,ctr10,ctr11,ctr12,ctr13,ctr14;
    //static IStmt firstProgram, secondProgram, thirdProgram, fourthProgram, lastProgram;
    @FXML
    ListView<Controller> myPrgList;
    @FXML
    Button runButton;

    public static IStmt assemble(IStmt... statements){
        return assemble2(List.of(statements));
    }

    public static IStmt assemble2(List<IStmt> stmts){
        if(stmts.size() == 0)
            return new NopStmt();
        return new CompStmt(stmts.get(0), assemble2(stmts.subList(1, stmts.size())));
    }

    public void setUp() {
        IStmt ex1= new CompStmt(
                new VarDeclStmt("v", new IntType()), new CompStmt(
                new AssignStmt("v", new ValueExp(new IntValue(2))),
                new PrintStmt(new VarExp("v"))));
        //ex1.typeCheck(new MyDictionary<>());

        PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1, new FileTable<>(), new Heap<>());
        IRepository repo1 = new Repository("log1.txt");
        repo1.addPrg(prg1);
        ctr1 = new Controller(repo1);


        IStmt ex2= new CompStmt(
                new VarDeclStmt("a", new IntType()), new CompStmt(
                new VarDeclStmt("b", new IntType()), new CompStmt(
                new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)),
                        new ArithExp(3, new ValueExp(new IntValue(3)),
                                new ValueExp(new IntValue(5))))),
                new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        //ex2.typeCheck(new MyDictionary<>());

        PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex2, new FileTable<>(), new Heap<>());
        IRepository repo2 = new Repository("log2.txt");
        repo2.addPrg(prg2);
        ctr2 = new Controller(repo2);


        IStmt ex3= new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(false))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));;
        //ex3.typeCheck(new MyDictionary<>());

        PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex3, new FileTable<>(), new Heap<>());
        IRepository repo3 = new Repository("log3.txt");
        repo3.addPrg(prg3);
        ctr3 = new Controller(repo3);


        IStmt ex4 = new CompStmt(
                new VarDeclStmt("varf", new StringType()),
                new CompStmt(
                        new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(
                                new OpenRFileStmt(new VarExp("varf")),
                                new CompStmt(
                                        new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(
                                                new ReadFileStmt(new VarExp("varf"), "varc"),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(
                                                                new ReadFileStmt(new VarExp("varf"), "varc"),
                                                                new CompStmt(
                                                                        new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFileStmt(new VarExp("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        // ex4.typeCheck(new MyDictionary<>());

        PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex4, new FileTable<>(), new Heap<>());
        IRepository repo4 = new Repository("log4.txt");
        repo4.addPrg(prg4);
        ctr4 = new Controller(repo4);


        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));

        //ex5.typeCheck(new MyDictionary<>());

        PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex5, new FileTable<>(), new Heap<>());;
        IRepository repo5 = new Repository("log5.txt");
        repo5.addPrg(prg5);
        ctr5 = new Controller(repo5);


        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(1, new HeapReadingExp(new HeapReadingExp(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));

        //ex6.typeCheck(new MyDictionary<>());

        PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex6, new FileTable<>(), new Heap<>());;
        IRepository repo6 = new Repository("log6.txt");
        repo6.addPrg(prg6);
        ctr6 = new Controller(repo6);


        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(1, new HeapReadingExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

        //ex7.typeCheck(new MyDictionary<>());

        PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex7, new FileTable<>(), new Heap<>());;
        IRepository repo7 = new Repository("log7.txt");
        repo7.addPrg(prg7);
        ctr7 = new Controller(repo7);


        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                        new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))))))));

        //ex8.typeCheck(new MyDictionary<>());

        PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex8, new FileTable<>(), new Heap<>());;
        IRepository repo8 = new Repository("log8.txt");
        repo8.addPrg(prg8);
        ctr8 = new Controller(repo8);


        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(5))),
                        new CompStmt( new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        //ex9.typeCheck(new MyDictionary<>());

        PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex9, new FileTable<>(), new Heap<>());
        IRepository repo9 = new Repository("log9.txt");
        repo9.addPrg(prg9);
        ctr9 = new Controller(repo9);


        //thread
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("a",new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));
        //ex10.typeCheck(new MyDictionary<>());

        PrgState prg10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex10, new FileTable<>(), new Heap<>());
        IRepository repo10 = new Repository("log10.txt");
        repo10.addPrg(prg10);
        ctr10 = new Controller(repo10);

        IStmt ex11 = new CompStmt(
                new VarDeclStmt("v",new IntType()),
                new CompStmt(
                        new VarDeclStmt("x",new IntType()),
                        new CompStmt(
                                new VarDeclStmt("y",new IntType()),
                                new CompStmt(
                                        new AssignStmt("v",new ValueExp(new IntValue(0))),
                                        new CompStmt(
                                                new RepeatStmt(
                                                        new CompStmt(
                                                                new ForkStmt(
                                                                        new CompStmt(
                                                                                new PrintStmt(new VarExp("v")),
                                                                                new AssignStmt("v",new ArithExp(2,new VarExp("v"),new ValueExp(new IntValue(1))))
                                                                        )
                                                                ),
                                                                new AssignStmt("v",new ArithExp(1,new VarExp("v"),new ValueExp(new IntValue(1))))
                                                        ),
                                                        new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(3)),3)
                                                ),
                                                new CompStmt(
                                                        new AssignStmt("x",new ValueExp(new IntValue(1))),
                                                        new CompStmt(
                                                                new NopStmt(),
                                                                new CompStmt(
                                                                        new AssignStmt("y", new ValueExp(new IntValue(3))),
                                                                        new CompStmt(
                                                                                new NopStmt(),
                                                                                new PrintStmt(new ArithExp(3,new VarExp("v"),new ValueExp(new IntValue(10))))
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        PrgState prg11 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex11, new FileTable<>(), new Heap<>());
        IRepository repo11 = new Repository("log11.txt");
        repo11.addPrg(prg11);
        ctr11 = new Controller(repo11);

        /*IStmt ex12 = new CompStmt(
                new VarDeclStmt("b",new BoolType()),new CompStmt(
                        new VarDeclStmt("c",new IntType()), new CompStmt(
                                new AssignStmt("b",new ValueExp(new BoolValue(true)), new CompStmt(
                                        new CondStmt("c",new VarExp("b"),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))), new CompStmt(
                                                new PrintStmt(new VarExp("c")), new CompStmt(
                                                        new CondStmt("c",new ValueExp(new BoolValue(false)),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),
                                new PrintStmt(new VarExp("c"))
                                )
                                )
        );

         */
        IStmt ex12 = assemble(
                new VarDeclStmt("b",new BoolType()),
                new VarDeclStmt("c",new IntType()),
                new AssignStmt("b",new ValueExp(new BoolValue(true))),
                new CondStmt("c",new VarExp("b"),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),
                new PrintStmt(new VarExp("c")),
                new CondStmt("c", new ValueExp(new BoolValue(false)),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),
                new PrintStmt(new VarExp("c"))
        );

        PrgState prg12 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex12, new FileTable<>(), new Heap<>());
        IRepository repo12 = new Repository("log12.txt");
        repo12.addPrg(prg12);
        ctr12 = new Controller(repo12);



        /*
        IStmt ex13 = assemble(
                new VarDeclStmt("a", new RefType(new IntType())),
                new HeapAllocStmt("a",new ValueExp(new IntValue(20))),
                new ForStmt("v",new ValueExp(new IntValue(0)),new ValueExp(new IntValue(3)),new ValueExp(new IntValue(1)),
                        new ForkStmt(assemble(
                        new PrintStmt(new VarExp("v")),
                        new AssignStmt("v", new ArithExp(3,new VarExp("v"),new HeapReadingExp(new VarExp("a"))))
                        ))
                ),
                        new PrintStmt(new HeapReadingExp(new VarExp("a")))
                );


         */



        IStmt ex13 = new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new ForStmt("v",
                                new ValueExp(new IntValue(0)),
                                new ValueExp(new IntValue(3)),
                                new ValueExp(new IntValue(1)),
                                new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp(3, new VarExp("v"), new HeapReadingExp(new VarExp("a"))))))),
                                new PrintStmt(new HeapReadingExp(new VarExp("a"))))));


        PrgState prg13 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex13, new FileTable<>(), new Heap<>());
        IRepository repo13 = new Repository("log13.txt");
        repo13.addPrg(prg13);
        ctr13 = new Controller(repo13);

        IStmt ex14 = assemble(
                new VarDeclStmt("a",new IntType()),
                new AssignStmt("a",new ValueExp(new IntValue(1))),
                new VarDeclStmt("b",new IntType()),
                new AssignStmt("b",new ValueExp(new IntValue(2))),
                new VarDeclStmt("c",new IntType()),
                new AssignStmt("c",new ValueExp(new IntValue(5))),
                new SwitchStmt(new ArithExp(3,new VarExp("a"),new ValueExp(new IntValue(10))),
                        new ArithExp(3, new VarExp("b"), new VarExp("c")),
                        new ValueExp(new IntValue(10)),
                        assemble(
                                new PrintStmt(new VarExp("a")),
                                new PrintStmt(new VarExp("b"))
                        ),
                        assemble(
                                new PrintStmt(new ValueExp(new IntValue(100))),
                                new PrintStmt(new ValueExp(new IntValue(200)))
                        ),
                        new PrintStmt(new ValueExp(new IntValue(300)))),
                new PrintStmt(new ValueExp(new IntValue(300))));

        PrgState prg14 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex14, new FileTable<>(), new Heap<>());
        IRepository repo14 = new Repository("log14.txt");
        repo14.addPrg(prg14);
        ctr14 = new Controller(repo14);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUp();
        ObservableList<Controller> myData = FXCollections.observableArrayList();
        myData.add(ctr1);
        myData.add(ctr2);
        myData.add(ctr3);
        myData.add(ctr4);
        myData.add(ctr5);
        myData.add(ctr6);
        myData.add(ctr7);
        myData.add(ctr8);
        myData.add(ctr9);
        myData.add(ctr10);
        myData.add(ctr11);
        myData.add(ctr12);
        myData.add(ctr13);
        myData.add(ctr14);

        myPrgList.setItems(myData);
        myPrgList.getSelectionModel().selectFirst();
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                Controller myCtrl = myPrgList.getSelectionModel().getSelectedItem();
                try {
                    myCtrl.getRepository().getPrgList().get(0).getOriginalProgram().typeCheck(new MyDictionary<>());
                }
                catch (MyException exception) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Toy Language - Warning - TYPECHECK");
                    alert.setHeaderText(null);
                    alert.setContentText(exception.getMessage());
                    Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    confirm.setDefaultButton(false);
                    confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                    alert.showAndWait();
                    return;
                }
                Stage programStage = new Stage();
                Parent programRoot;
                Callback<Class<?>, Object> controllerFactory = type -> {
                    if (type == PrgRunController.class) {
                        return new PrgRunController(myCtrl);
                    } else {
                        try {
                            return type.newInstance() ;
                        } catch (Exception exc) {
                            System.err.println("Could not create controller for "+type.getName());
                            throw new RuntimeException(exc);
                        }
                    }
                };
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ProgramLayout.fxml"));
                    fxmlLoader.setControllerFactory(controllerFactory);
                    programRoot = fxmlLoader.load();
                    Scene programScene = new Scene(programRoot);
                    programStage.setTitle("Toy Language - Program running");
                    programStage.setScene(programScene);
                    programStage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}
