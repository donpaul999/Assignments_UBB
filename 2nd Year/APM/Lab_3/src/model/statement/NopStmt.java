package model.statement;

import exceptions.MyException;
import model.PrgState;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }
}
