package model.statement.files;

import exception.MyException;
import exception.VariableNotDefined;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyPair;
import model.exp.Exp;
import model.statement.IStmt;
import model.type.StringType;
import model.type.Type;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadFileStmt implements IStmt {

    private final Exp fileExpression;
    private final String varName;

    //constructor

    public ReadFileStmt(Exp fileExpression, String varName) {
        this.fileExpression = fileExpression;
        this.varName = varName;
    }

    public Exp getFileExpression() {
        return fileExpression;
    }

    public String getVarName() {
        return varName;
    }

    //Method2:

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, IValue> symTable = state.getSymTable();

        if (symTable.isDefined(this.varName)) {
            IValue value = this.fileExpression.eval(symTable,state.getHeap());
            if (value instanceof StringValue) {
                StringValue v1 = (StringValue) value;
                if (fileTable.isDefined(v1)) {
                    BufferedReader bufferedReader = fileTable.lookup(v1);
                    String line = bufferedReader.readLine();
                    int val;
                    if (line == null) {
                        val = 0;
                    } else
                        val = Integer.parseInt(line);
                    symTable.update(varName, new IntValue(val));
                } else throw new MyException("File not defined");
            } else throw new MyException("The expression is not a string");
        } else throw new VariableNotDefined("Variable not defined");
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
        return "Read "+this.fileExpression.toString() + "/" + this.varName.toString();
    }
}

