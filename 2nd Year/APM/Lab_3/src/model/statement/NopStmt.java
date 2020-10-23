package model.statement;

import exceptions.MyException;
import model.ADT.IMyStack;
import model.PrgState;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IMyStack<IStmt> stack = state.getStack();
        state.setExeStack(stack);
        return state;
    }
}
