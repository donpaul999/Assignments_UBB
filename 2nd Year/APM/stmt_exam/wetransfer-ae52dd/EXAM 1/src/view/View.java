package view;

import controller.Controller;
import exception.MyException;
import model.PrgState;
import model.adt.*;
import model.exp.ArithExp;
import model.exp.ValueExp;
import model.exp.VarExp;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class View {
    /*

    Controller controller;

    public View(Controller controller) {
        this.controller=controller;
    }

    public void menu() {
        System.out.println("    Choose one");
        System.out.println("1. v=2  Print(v)");
        System.out.println("2. a=2+3*5  b=a+1   Print(b)");
        System.out.println("3. a=true   (If a Then v=2 Else v=3)    Print(v)");
        System.out.println("0. Exit");
    }

    public void init(IStmt originalPrg) throws IOException {
        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, IValue> symTable = new MyDictionary<>();
        MyIList<IValue> out = new MyList<>();
        FileTable<StringValue, BufferedReader> fileTable=new FileTable<>();
        PrgState prg = new PrgState(exeStack, symTable, out, originalPrg, fileTable );
        this.controller.addProgram(prg);
        this.controller.allStep();
    }

    public void run() {
        IStmt originalPrg;
        label:
        while (true) {
            Scanner keyboard=new Scanner(System.in);
            this.menu();
            int command;
            System.out.println("Insert command:");
            command=keyboard.nextInt();
            try {
                switch (command) {
                    case (0): {
                        break label;
                    }
                    case (1): {
                        originalPrg = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
                        System.out.println(originalPrg);
                        this.init(originalPrg);
                        break;
                    }
                    case (2): {
                        originalPrg = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
                        //originalPrg = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("a", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
                        System.out.println(originalPrg);
                        this.init(originalPrg);
                        break;
                    }
                    case (3): {
                        originalPrg = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(false))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
                        System.out.println(originalPrg);
                        this.init(originalPrg);
                        break;
                    }
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

     */
}
