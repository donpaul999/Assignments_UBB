package model.statement;

import model.ADT.IMyDictionary;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyStack;
import model.PrgState;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.BoolType;
import model.type.Type;
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
        Value cond = expression.eval(state.getSymTable(), state.getHeap());
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

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException {
        Type expType = expression.typecheck(typeEnvironment);
        if (expType.equals(new BoolType())) {
            thenStatement.typecheck(typeEnvironment.deepCopy());
            elseStatement.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else {
            throw new StmtException("The condition in " + this.toString() + " statement is not a boolean");
        }
    }

}
