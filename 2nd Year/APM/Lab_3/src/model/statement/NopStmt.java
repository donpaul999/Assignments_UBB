package model.statement;

import model.exceptions.MyException;
import model.PrgState;
import model.exceptions.StmtException;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws StmtException {
        return state;
    }
}
