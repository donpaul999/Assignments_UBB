package controller;

import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyStack;
import model.ADT.MyDictionary;
import model.ADT.MyList;
import model.ADT.MyStack;
import model.PrgState;
import model.exceptions.StmtException;
import model.expression.ArithExp;
import model.expression.ValueExp;
import model.expression.VarExp;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;
import repository.IRepo;

import java.io.IOException;

public class Controller {
    private IRepo repository;

    public Controller(IRepo repo) {
        this.repository = repo;
    }

    public PrgState oneStep(PrgState state) throws MyException, ADTException, StmtException, ExprException {
        IMyStack<IStmt> stack = state.getStack();
        if (stack.isEmpty()) {
            throw new MyException("Stack is empty");
        }
        IStmt currentStmt = stack.pop();
        return currentStmt.execute(state);
    }

    public void allStep() throws MyException, IOException {
        PrgState prg = repository.getCrtPrg();
        repository.printPrgState(prg);
        //System.out.println(prg);

        while (!prg.getStack().isEmpty()) {
            try {
                oneStep(prg);
                //System.out.println(prg);
                repository.printPrgState(prg);
            } catch (MyException | ADTException | StmtException | ExprException exception) {
                throw new MyException(exception.getMessage());
            }
        }
    }

    public void example() {
        IMyStack<IStmt> stack = new MyStack<>();
        IStmt example_1 = new CompStmt(
                new VarDeclStmt("x", new IntType()),
                new CompStmt(
                        new AssignStmt("x", new ValueExp(new IntValue(17))),
                        new PrintStmt(new VarExp("x"))
                )
        );

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

        stack.push(example_3);
        PrgState state = new PrgState(stack, new MyDictionary<String, Value>(), new MyList<Value>());
        System.out.println(state);
        repository.addState(state);
    }

}
