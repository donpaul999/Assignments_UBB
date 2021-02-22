package model.statement;

import com.sun.jdi.IntegerType;
import model.ADT.IMyDictionary;
import model.ADT.IMyStack;
import model.PrgState;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.StmtException;
import model.expression.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.Type;
import model.value.Value;

public class RepeatUntil implements IStmt {
    IStmt stmt;
    Exp exp;

    public RepeatUntil(IStmt stmt, Exp exp1) {
        this.stmt = stmt;
        this.exp = exp1;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stk = state.getStack();
        IStmt st = new CompStmt(stmt, new WhileStmt(new LogicExp("!", exp), stmt));
        stk.push(st.deepCopy());
        state.setExeStack(stk);
        return null;
    }


    @Override
    public IStmt deepCopy() {
        return new RepeatUntil(stmt.deepCopy(), exp.deepCopy());
    }

    @Override
    public String toString() {
        return "repeat " + stmt + " until " + exp;
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException, ADTException {
        Type expType = exp.typecheck(typeEnvironment);
        if (expType.equals(new BoolType())) {
            stmt.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        } else throw new StmtException("Exp value is not of type bool!");
    }
}