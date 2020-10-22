package controller;

import exceptions.MyException;
import model.ADT.IMyStack;
import model.ADT.MyDictionary;
import model.ADT.MyList;
import model.ADT.MyStack;
import model.PrgState;
import model.expression.ValueExp;
import model.expression.VarExp;
import model.statement.*;
import model.type.IntType;
import model.value.IntValue;
import model.value.Value;
import repository.IRepo;

public class Controller {
    private IRepo repository;

    public Controller(IRepo repo) {
        this.repository = repo;
    }

    public PrgState oneStep(PrgState state) throws MyException {
        IMyStack<IStmt> stack = state.getStack();
        if (stack.isEmpty()) {
            throw new MyException("Stack is empty");
        }
        IStmt currentStmt = stack.pop();
        return currentStmt.execute(state);
    }

    public void allSteps() throws MyException {
        PrgState prg = repository.getCrtPrg();
        System.out.println(prg);

        while (!prg.getStack().isEmpty()) {
            try {
                oneStep(prg);
                System.out.println(prg);
            } catch (MyException exception) {
                throw new MyException(exception.getMessage());
            }
        }
    }

    public void example() {
        IMyStack<IStmt> stack = new MyStack<>();
        IStmt ex = new CompStmt(
                new VarDeclStmt(
                        "v",
                        new IntType()
                ),
                new CompStmt(
                        new AssignStmt(
                                "v",
                                new ValueExp(
                                        new IntValue(2)
                                )
                        ),
                        new PrintStmt(
                                new VarExp(
                                        "v"
                                )
                        )
                )
        );

        System.out.println(ex);

        stack.push(ex);
        PrgState state = new PrgState(stack, new MyDictionary<String, Value>(), new MyList<Value>());
        System.out.println(state);
        repository.addState(state);
    }

}
