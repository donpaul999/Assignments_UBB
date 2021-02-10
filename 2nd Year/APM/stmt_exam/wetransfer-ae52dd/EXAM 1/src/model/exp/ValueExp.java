package model.exp;

import exception.MyException;
import model.adt.IHeap;
import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.type.Type;
import model.value.IValue;

public class ValueExp implements Exp {
    IValue val;

    public ValueExp(IValue val) {
        this.val=val;
    }

    public IValue getVal() {return val;}

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, IHeap<Integer,IValue> heap){
        return this.val;
    }

    @Override
    public String toString() {
        return this.val.toString();
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return val.getType();
    }
}
