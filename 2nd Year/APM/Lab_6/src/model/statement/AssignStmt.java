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
        Value value = exp.eval(symTable, state.getHeap());
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
    @Override
    public IStmt deepCopy() {
        return new AssignStmt(new String(id), exp.deepCopy());
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnv) throws StmtException, ExprException {
        if (!typeEnv.isDefined(id)) {
            throw new StmtException("The variable " + id + " is not defined in the assignment statement " + this.toString());
        }
        else {
            Type variableType = typeEnv.lookup(id);
            Type expType = exp.typecheck(typeEnv);
            if (variableType.equals(expType)) {
                return typeEnv;
            }
            else {
                throw new StmtException("The right side and left side of the assignment " + this.toString() + " have different types");
            }
        }
    }
}
