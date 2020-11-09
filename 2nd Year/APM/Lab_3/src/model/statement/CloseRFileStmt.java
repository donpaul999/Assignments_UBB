package model.statement;

import model.ADT.IMyDictionary;
import model.PrgState;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.StringType;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt {
    private Exp exp;

    public CloseRFileStmt(Exp exp) {
        this.exp = exp;
    }


    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException, ADTException {
        IMyDictionary<String, Value> symTable = state.getSymTable();
        Value val = exp.eval(symTable);
        if (val.getType().equals(new StringType())) {
            IMyDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
            StringValue stringVal = (StringValue) val;
            if (fileTable.isDefined(stringVal)) {
                BufferedReader bufferedReader = fileTable.lookup(stringVal);
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new StmtException(e.getMessage());
                }
                fileTable.remove(stringVal);
            } else {
                throw new StmtException("The file doesn't exist in the File Table!");
            }
        }
        else {
            throw new StmtException("Expression could not be evaluated to a string in File Close!");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public String toString() {
        return "close(" + exp + ")";
    }
}
