package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.exp.Exp;
import model.exp.VarExp;
import model.type.BoolType;
import model.type.Type;

import java.io.IOException;

public class CondStmt implements IStmt{

    private String var;
    private Exp cond;
    private Exp trueCond;
    private Exp falseCond;

    public CondStmt(String var, Exp cond, Exp trueCond, Exp falseCond) {
        this.var = var;
        this.cond = cond;
        this.trueCond = trueCond;
        this.falseCond = falseCond;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        IStmt newStatement = new IfStmt(cond, new AssignStmt(var, trueCond), new AssignStmt(var, falseCond));
        exeStack.push(newStatement);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typev = new VarExp(var).typeCheck(typeEnv);
        Type type = cond.typeCheck(typeEnv);
        Type type1 = trueCond.typeCheck(typeEnv);
        Type type2 = falseCond.typeCheck(typeEnv);

        if (type.equals(new BoolType()) && type1.equals(typev) && type2.equals(typev)) return typeEnv;
        else throw new RuntimeException("CondStmt: Invalid types");
    }

    @Override
    public String toString() {
        return var + " = " + cond.toString() + " ? " + trueCond.toString() + " : " + falseCond.toString();
    }
}
