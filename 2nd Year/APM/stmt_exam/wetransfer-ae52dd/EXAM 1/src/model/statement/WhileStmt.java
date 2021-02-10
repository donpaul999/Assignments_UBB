package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.*;
import model.exp.Exp;
import model.type.Type;
import model.value.BoolValue;
import model.value.IValue;

import java.io.IOException;

public class WhileStmt implements IStmt {

    private Exp expression;
    private IStmt statement;

    public WhileStmt(Exp expression, IStmt statement){
        this.expression = expression;
        this.statement = statement;
    }

    public Exp getExpression() {
        return expression;
    }

    public void setExpression(Exp expression) {
        this.expression = expression;
    }

    public IStmt getStatement() {
        return statement;
    }

    public void setStatement(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "while( " + expression.toString() + ") { " + statement.toString() + " }";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException{
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        IHeap<Integer,IValue> heap = state.getHeap();

        IValue expVal = this.expression.eval(symTable, heap);
        if (expVal instanceof BoolValue) {
            BoolValue boolValue = (BoolValue) expVal;
            if (boolValue.getVal()) {
                MyIStack<IStmt> stack = state.getExeStack();
                stack.push(this);
                stack.push(this.statement);
            }
        } else {
            throw new MyException("The expression is not a boolValue!");
        }
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
