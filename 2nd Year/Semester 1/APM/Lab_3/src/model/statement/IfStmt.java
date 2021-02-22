package model.statement;

import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyStack;
import model.PrgState;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.Value;

public class IfStmt implements IStmt {
    private Exp expression;
    private IStmt thenStatement;
    private IStmt elseStatement;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        expression = e;
        thenStatement = t;
        elseStatement = el;

    }

    @Override
    public String toString() {
        return "if (" + expression + ") then {" + thenStatement + "} else {" + elseStatement + "}";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stack = state.getStack();
        Value cond = expression.eval(state.getSymTable());
        if (!cond.getType().equals(new BoolType())) {
            throw new StmtException("Condition is not of boolean");
        }
        if (cond.equals(new BoolValue(true))) {
            stack.push(thenStatement.deepCopy());
        } else {
            stack.push(elseStatement.deepCopy());
        }
        state.setExeStack(stack);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

}
