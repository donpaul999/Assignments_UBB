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
import model.value.IntValue;
import model.value.Value;
import org.hamcrest.core.Is;

public class WaitStmt implements IStmt {
    Integer nr;

    public WaitStmt(Integer number) {
        this.nr = number;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stk = state.getStack();
        if(nr != 0) {
            IStmt st = new CompStmt(new PrintStmt(new ValueExp(new IntValue(nr))),new WaitStmt(nr - 1));
            stk.push(st.deepCopy());
            state.setExeStack(stk);
        }
        return null;
    }


    @Override
    public IStmt deepCopy() {
        return new WaitStmt(nr);
    }

    @Override
    public String toString() {
        return "wait(" + nr.toString() + ")" ;
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException, ADTException {
        return typeEnvironment;
    }
}