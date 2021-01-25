package model.statement;

import model.ADT.IMyDictionary;
import model.exceptions.MyException;
import model.PrgState;
import model.exceptions.StmtException;
import model.type.Type;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws StmtException {
        return null;
    }
    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException {
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
