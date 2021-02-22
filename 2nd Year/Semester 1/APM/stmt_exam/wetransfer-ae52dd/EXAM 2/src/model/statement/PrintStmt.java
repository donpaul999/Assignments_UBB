package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIList;
import model.exp.Exp;
import model.type.Type;
import model.value.IValue;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return ("print(" +exp.toString()+")");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<IValue> list = state.getOut();
        MyIDictionary<String, IValue> symbolTable = state.getSymTable();
        list.add(this.exp.eval(symbolTable,state.getHeap()));
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }
}

