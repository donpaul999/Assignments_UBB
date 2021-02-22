package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.type.Type;

public class NopStmt implements IStmt{

    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) {
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "no operation statement";
    }
}
