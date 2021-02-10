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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class OpenRFileStmt implements IStmt
{
    private Exp fileExpression;

    public OpenRFileStmt(Exp fileExpression) {
        this.fileExpression = fileExpression;
    }

    public Exp getFileExpression() {
        return fileExpression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIDictionary<StringValue,BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String,IValue> symTable = state.getSymTable();
        IValue value = this.fileExpression.eval(symTable, state.getHeap());

        if (value.getType() instanceof StringType) {
            StringValue v1 = (StringValue) value;
            String path=v1.getVal();
            if (!symTable.isDefined(path)) {
                try {
                    FileReader reader = new FileReader(path);
                    BufferedReader buffReader = new BufferedReader(reader);
                    fileTable.add(v1, buffReader);

                } catch (FileNotFoundException e) {
                    throw new MyException("Inexistent file");
                }
            }
            else
                throw new MyException("File already exists");
        }
        else
            throw new MyException(""+this.fileExpression.toString()+" not string type");
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = fileExpression.typeCheck(typeEnv);
        if(typ.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("The operand is not a string type!");
    }

    @Override
    public String toString() {
        return "Open file "+ this.fileExpression.toString();
    }
}






