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

public class ForStmt_2 implements IStmt {
    String varName;
    Exp exp1, exp2, exp3;
    IStmt stmt;

    public ForStmt_2(String varName, Exp exp1, Exp exp2, Exp exp3, IStmt stmt) {
        this.varName = varName;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stk = state.getStack();
        IStmt st = new CompStmt(new VarDeclStmt(varName, new IntType()),
                new CompStmt(new AssignStmt(varName, exp1), new WhileStmt(new RelationalExp(new VarExp(varName), exp2, 1), new CompStmt(stmt, new AssignStmt(varName, exp3)))));
        stk.push(st.deepCopy());
        state.setExeStack(stk);
        return null;
    }


    @Override
    public IStmt deepCopy() {
        return new ForStmt_2(varName, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy(), stmt.deepCopy());
    }

    @Override
    public String toString() {
        return "(for (" + varName + "=" + exp1 + ";" + varName + "<" + exp2 + ";" + varName + "=" + exp3 + ") " + stmt + ")";
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException, ADTException {
        typeEnvironment.add(varName, new IntType());
        Type exp1Type = exp1.typecheck(typeEnvironment);
        if (exp1Type.equals(new IntType())) {
            Type exp2Type = exp2.typecheck(typeEnvironment);
            if (exp2Type.equals(new IntType())) {
                Type exp3Type = exp3.typecheck(typeEnvironment);
                if (exp3Type.equals(new IntType())) {
                    return typeEnvironment;
                } else throw new StmtException("Exp3 value is not of type int!");
            } else throw new StmtException("Exp2 value is not of type int!");
        } else throw new StmtException("Exp1 value is not of type int!");
    }
}