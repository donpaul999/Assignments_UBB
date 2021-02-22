package model.statement;

import exception.MyException;
import javafx.beans.binding.BooleanExpression;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.exp.Exp;
import model.exp.LogicExp;
import model.exp.RelationalExp;
import model.exp.VarExp;
import model.type.IntType;
import model.type.Type;

import java.io.IOException;

public class ForStmt implements IStmt {
    private String var;
    private Exp initialization;
    private Exp condition;
    private Exp increment;
    private IStmt statement;

    public ForStmt(String var, Exp initialization, Exp condition, Exp increment, IStmt statement) {
        this.var = var;
        this.initialization = initialization;
        this.condition = condition;
        this.increment = increment;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> executionStack = state.getExeStack();
        IStmt newStatement = new CompStmt(new AssignStmt(var, initialization), new WhileStmt(new RelationalExp(new VarExp("v"), condition, 1),
                new CompStmt(statement, new AssignStmt(var, increment))));
        executionStack.push(newStatement);
        state.setExeStack(executionStack);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = initialization.typeCheck(typeEnv);
        Type type1 = condition.typeCheck(typeEnv);
        Type type2 = increment.typeCheck(typeEnv);

        if (type.equals(new IntType()) && type1.equals(new IntType()) && type2.equals(new IntType())) return typeEnv;
        else throw new RuntimeException("ForStmt: Invalid types");
    }

    @Override
    public String toString(){
        return "for( " + var + "=" + initialization.toString() + "; " + var + "<" + condition.toString() + "; " + var + "=" + increment.toString() + " ) \n " + statement.toString();
    }
}
