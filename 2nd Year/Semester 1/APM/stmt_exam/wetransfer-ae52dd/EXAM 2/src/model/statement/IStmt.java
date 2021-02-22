package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.type.Type;

import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;
    //which is the execution method for a statement.
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
