package model.statement;

import model.ADT.IMyDictionary;
import model.PrgState;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.IntType;
import model.type.StringType;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    private Exp exp;
    private String varName;
    private String fileName;

    public ReadFileStmt(Exp expression, String varName) {
        this.exp = expression;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException, ADTException {
        IMyDictionary<String, Value> symTable = state.getSymTable();
        IMyDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        if (symTable.isDefined(varName)) {
            if (symTable.lookup(varName).getType().equals(new IntType())) {
                Value val = exp.eval(symTable);
                if (val.getType().equals(new StringType())) {
                    StringValue stringVal = (StringValue) val;
                    if (fileTable.isDefined(stringVal)) {
                        BufferedReader bufferedReader = fileTable.lookup(stringVal);
                        try {
                            String line = bufferedReader.readLine();
                            Value intVal;
                            IntType type = new IntType();
                            if (line == null) {
                                intVal = type.defaultValue();
                            } else {
                                intVal = new IntValue(Integer.parseInt(line));
                            }
                            symTable.update(varName, intVal);
                        } catch (IOException e) {
                            throw new StmtException(e.getMessage());
                        }
                    }
                    else {
                        throw new StmtException("The file " + stringVal.getValue() + " is not in the File Table!");
                    }
                }
                else {
                    throw new StmtException("The value couldn't be evaluated to a string value!");
                }
            }
            else {
                throw new StmtException(varName + " is not of type int!");
            }
        }
        else {
            throw new StmtException(varName + " is not defined in Sym Table");
        }

        return null;
    }


    @Override
    public String toString() {
        return "Read From " + exp + " into " + varName;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFileStmt(exp.deepCopy(), new String(varName));
    }
}
