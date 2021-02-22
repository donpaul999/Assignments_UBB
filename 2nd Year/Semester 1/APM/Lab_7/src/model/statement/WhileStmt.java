package model.statement;

import model.ADT.IMyDictionary;
import model.ADT.IMyStack;
import model.PrgState;
import model.exceptions.ExprException;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt statement;

    public WhileStmt(Exp exp, IStmt statement) {
        this.exp = exp;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stk = state.getStack();
        IMyDictionary<String, Value> symTable = state.getSymTable();
        Value val = exp.eval(symTable, state.getHeap());
        if (val.getType().equals(new BoolType())) {
            BoolValue boolVal = (BoolValue) val;
            if (boolVal.getValue()) {
                stk.push(this.deepCopy());
                stk.push(statement);
            }
        }
        else {
            throw new StmtException("The While condition is not a boolean");
        }
        return null;
    }


    @Override
    public IStmt deepCopy() {
        return new WhileStmt(exp.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return "(while (" + exp + ") " + statement + ")";
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException {
        Type expType = exp.typecheck(typeEnvironment);
        if (expType.equals(new BoolType())) {
            statement.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else {
            throw new StmtException("The condition in " + this.toString() + " is not a boolean");
        }
    }
}