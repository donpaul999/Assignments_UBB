import controller.Controller;
import model.ADT.*;
import model.PrgState;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.expression.*;
import model.statement.*;
import model.type.*;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.IRepo;
import repository.Repository;
import view.*;

import java.io.BufferedReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, MyException, StmtException, ExprException {

        IMyStack<IStmt> stack1 = new MyStack<>();
        IMyStack<IStmt> stack2 = new MyStack<>();
        IMyStack<IStmt> stack3 = new MyStack<>();
        IMyStack<IStmt> stack4 = new MyStack<>();
        IMyStack<IStmt> stack5 = new MyStack<>();
        IMyStack<IStmt> stack6 = new MyStack<>();
        IMyStack<IStmt> stack7 = new MyStack<>();

        IStmt example_1 = new CompStmt(
                new VarDeclStmt("x", new IntType()),
                new CompStmt(
                        new AssignStmt("x", new ValueExp(new IntValue(17))),
                        new PrintStmt(new VarExp("x"))
                )
        );
        example_1.typecheck(new MyDictionary<String, Type>());
        PrgState prg1 = new PrgState(stack1, new MyDictionary<String, Value>(),  new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(),  example_1);
        IRepo repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        IStmt example_2 = new CompStmt(
                new VarDeclStmt("x" , new IntType()),
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
        example_2.typecheck(new MyDictionary<String, Type>());

        PrgState prg2= new PrgState(stack2, new MyDictionary<String, Value>(),  new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), example_2);
        IRepo repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);

        IStmt example_3 = new CompStmt(
                new VarDeclStmt("s" , new BoolType()),
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
        example_3.typecheck(new MyDictionary<String, Type>());

        PrgState prg3 = new PrgState(stack3, new MyDictionary<String, Value>(),  new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), example_3);
        IRepo repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

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
        example_4.typecheck(new MyDictionary<String, Type>());

        PrgState prg4 = new PrgState(stack4, new MyDictionary<String, Value>(),  new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), example_4);
        IRepo repo4 = new Repository(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);

        IStmt example_5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                        new ValueExp(new IntValue(5)),
                                                        '+')))))));
        example_5.typecheck(new MyDictionary<String, Type>());


        PrgState prg5 = new PrgState(stack5, new MyDictionary<String, Value>(),  new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), example_5);
        IRepo repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

        IStmt example_6 = new CompStmt(new VarDeclStmt("x", new IntType()),
                new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(10))),
                        new WhileStmt(new RelationalExp(new VarExp("x"), new ValueExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("x")), new AssignStmt("x", new ArithExp(new VarExp("x"), new ValueExp(new IntValue(1)), '-')))
                        )));


        example_6.typecheck(new MyDictionary<String, Type>());

        PrgState prg6 = new PrgState(stack6, new MyDictionary<String, Value>(),  new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), example_6);
        IRepo repo6 = new Repository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

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



        example_7.typecheck(new MyDictionary<String, Type>());

        PrgState prg7 = new PrgState(stack7, new MyDictionary<String, Value>(),  new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), example_7);
        IRepo repo7 = new Repository(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);

        TextMenu menu = new TextMenu();

        repo1.addState(prg1);
        repo2.addState(prg2);
        repo3.addState(prg3);
        repo4.addState(prg4);
        repo5.addState(prg5);
        repo6.addState(prg6);
        repo7.addState(prg7);


        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",example_1.toString(),ctr1));
        menu.addCommand(new RunExample("2",example_2.toString(),ctr2));
        menu.addCommand(new RunExample("3",example_3.toString(),ctr3));
        menu.addCommand(new RunExample("4",example_4.toString(),ctr4));
        menu.addCommand(new RunExample("5",example_5.toString(),ctr5));
        menu.addCommand(new RunExample("6",example_6.toString(),ctr6));
        menu.addCommand(new RunExample("7",example_7.toString(),ctr7));
        menu.show();
    }
}