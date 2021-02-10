/*package view;

import controller.Controller;
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

import java.util.ArrayList;

class Interpreter {

    public static void main(String[] args) {

        IStmt ex1= new CompStmt(
                new VarDeclStmt("v", new IntType()), new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                new PrintStmt(new VarExp("v"))));;
        ex1.typeCheck(new MyDictionary<>());

        PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1, new FileTable<>(), new Heap<>());
        IRepository repo1 = new Repository("log1.txt");
        repo1.addPrg(prg1);
        Controller ctr1 = new Controller(repo1);


        IStmt ex2= new CompStmt(
                new VarDeclStmt("a", new IntType()), new CompStmt(
                        new VarDeclStmt("b", new IntType()), new CompStmt(
                                new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)),
                                        new ArithExp(3, new ValueExp(new IntValue(3)),
                                                new ValueExp(new IntValue(5))))),
                new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        ex2.typeCheck(new MyDictionary<>());

        PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex2, new FileTable<>(), new Heap<>());
        IRepository repo2 = new Repository("log2.txt");
        repo2.addPrg(prg2);
        Controller ctr2 = new Controller(repo2);


        IStmt ex3= new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(false))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));;
        ex3.typeCheck(new MyDictionary<>());

        PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex3, new FileTable<>(), new Heap<>());
        IRepository repo3 = new Repository("log3.txt");
        repo3.addPrg(prg3);
        Controller ctr3 = new Controller(repo3);


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
        ex4.typeCheck(new MyDictionary<>());

        PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex4, new FileTable<>(), new Heap<>());
        IRepository repo4 = new Repository("log4.txt");
        repo4.addPrg(prg4);
        Controller ctr4 = new Controller(repo4);


        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));

        ex5.typeCheck(new MyDictionary<>());

        PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex5, new FileTable<>(), new Heap<>());;
        IRepository repo5 = new Repository("log5.txt");
        repo5.addPrg(prg5);
        Controller ctr5 = new Controller(repo5);


        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(1, new HeapReadingExp(new HeapReadingExp(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));

        ex6.typeCheck(new MyDictionary<>());

        PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex6, new FileTable<>(), new Heap<>());;
        IRepository repo6 = new Repository("log6.txt");
        repo6.addPrg(prg6);
        Controller ctr6 = new Controller(repo6);


        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(1, new HeapReadingExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

        ex7.typeCheck(new MyDictionary<>());

        PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex7, new FileTable<>(), new Heap<>());;
        IRepository repo7 = new Repository("log7.txt");
        repo7.addPrg(prg7);
        Controller ctr7 = new Controller(repo7);


        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new VarDeclStmt("a",new RefType(new RefType( new IntType()))),
                                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                        new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))))))));

        ex8.typeCheck(new MyDictionary<>());

        PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex8, new FileTable<>(), new Heap<>());;
        IRepository repo8 = new Repository("log8.txt");
        repo8.addPrg(prg8);
        Controller ctr8 = new Controller(repo8);


        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(5))),
                        new CompStmt( new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        ex9.typeCheck(new MyDictionary<>());

        PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex9, new FileTable<>(), new Heap<>());
        IRepository repo9 = new Repository("log9.txt");
        repo9.addPrg(prg9);
        Controller ctr9 = new Controller(repo9);


        //thread
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("a",new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));
        ex10.typeCheck(new MyDictionary<>());

        PrgState prg10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex10, new FileTable<>(), new Heap<>());
        IRepository repo10 = new Repository("log10.txt");
        repo10.addPrg(prg10);
        Controller ctr10 = new Controller(repo10);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctr9));
        menu.addCommand(new RunExample("10",ex10.toString(),ctr10));
        menu.show();
    }
}

 */