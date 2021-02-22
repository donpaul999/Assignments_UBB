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
import org.hamcrest.core.Is;

public class SwitchStmt implements IStmt {
    Exp exp1, exp2, exp;
    IStmt stmt1, stmt2, stmt3;

    public SwitchStmt(Exp exp, Exp exp1, Exp exp2, IStmt stmt1, IStmt stmt2, IStmt stmt3) {
        this.exp1 = exp1;
        this.exp = exp;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stk = state.getStack();
        new NopStmt();
        IStmt st = new IfStmt(new RelationalExp(exp, exp1, 3), stmt1, new IfStmt(new RelationalExp(exp, exp2, 3), stmt2, stmt3));
        stk.push(st.deepCopy());
        state.setExeStack(stk);
        return null;
    }


    @Override
    public IStmt deepCopy() {
        return new SwitchStmt(exp.deepCopy(), exp1.deepCopy(), exp2.deepCopy(), stmt1.deepCopy(), stmt2.deepCopy(), stmt3.deepCopy());
    }

    @Override
    public String toString() {
        return "switch(" + exp + ")(case " + exp1 + ": " + stmt1 + ")(case " + exp2 + ": " + stmt2 + ")(default: " + stmt3 + ")";
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException, ADTException {
        Type expType = exp.typecheck(typeEnvironment);
        Type exp1Type = exp1.typecheck(typeEnvironment);
        Type exp2Type = exp2.typecheck(typeEnvironment);
        if (expType.equals(new BoolType()) && exp1Type.equals(new BoolType()) && exp2Type.equals(new BoolType()) || expType.equals(new IntType()) && exp1Type.equals(new IntType()) && exp2Type.equals(new IntType())) {
            stmt1.typecheck(typeEnvironment.deepCopy());
            stmt2.typecheck(typeEnvironment.deepCopy());
            stmt3.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        } else throw new StmtException("Expressions doesn't have the same value types");
    }
}