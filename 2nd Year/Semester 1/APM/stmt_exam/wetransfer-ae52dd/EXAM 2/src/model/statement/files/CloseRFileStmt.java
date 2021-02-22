package model.statement.files;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.exp.Exp;
import model.statement.IStmt;
import model.type.StringType;
import model.type.Type;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public final class CloseRFileStmt implements IStmt {

    private final Exp fileExpression;

    public CloseRFileStmt(Exp fileExpression) {
        this.fileExpression = fileExpression;
    }


    @Override
    public String toString() {
        return " close file " + fileExpression.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<StringValue,BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String,IValue> symTable = state.getSymTable();
        IValue value = this.fileExpression.eval(symTable, state.getHeap());
        if (value instanceof StringValue) {
            StringValue v1 = (StringValue) value;
            if (fileTable.isDefined(v1)) {
                BufferedReader bufferedReader = fileTable.lookup(v1);
                bufferedReader.close();
                fileTable.delete(v1);
            }
            else throw new MyException("File not found");
        }
        else throw new MyException("Expression not string");
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

}



