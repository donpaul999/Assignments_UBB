package model.statement;

import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyDictionary;
import model.PrgState;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.Type;
import model.value.Value;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String s, Exp deepCopy) {
        id = s;
        exp = deepCopy;
    }

    @Override
    public String toString(){
        return id+"="+ exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyDictionary<String, Value> symTable = state.getSymTable();
        Value value = exp.eval(symTable);
        if (symTable.isDefined(id)) {
            Type type = (symTable.lookup(id)).getType();
            if (value.getType().equals(type)) {
                symTable.update(id, value);
            }
            else {
                throw new StmtException("Declared type of variable " +
                        id +
                        " and type of the assigned expression do not match");
            }
        }
        else {
            throw new StmtException("The used variable " + id + " was not declared before");
        }
        state.setSymTable(symTable);
        return state;
    }

}
