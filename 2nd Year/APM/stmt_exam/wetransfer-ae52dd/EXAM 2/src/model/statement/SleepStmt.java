package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.type.Type;

import java.io.IOException;

public class SleepStmt implements IStmt {

    private int nr;

    public SleepStmt(int n) { nr=n;}

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        if (nr != 0)
            state.getStk().push(new SleepStmt(nr-1));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Sleep(" + nr + ')';
    }
}
