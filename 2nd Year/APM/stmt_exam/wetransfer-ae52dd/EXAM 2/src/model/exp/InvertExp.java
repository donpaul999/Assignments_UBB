package model.exp;

import exception.MyException;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.type.Type;
import model.value.BoolValue;
import model.value.IValue;

public class InvertExp implements Exp{
    private Exp exp;

    public InvertExp(Exp e) {exp=e;}


    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, IHeap<Integer, IValue> heap) throws MyException {
        BoolValue b = (BoolValue) exp.eval(symTable,heap);
        return !b.getVal() ? new BoolValue(true) : new BoolValue(false);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return exp.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return "!(" + exp.toString() + ')';
    }
}
