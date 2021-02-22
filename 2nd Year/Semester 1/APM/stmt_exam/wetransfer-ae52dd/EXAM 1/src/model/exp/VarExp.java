package model.exp;

import exception.MyException;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.type.Type;
import model.value.IValue;

public class VarExp implements Exp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, IHeap<Integer,IValue> heap) throws MyException {
        return symTable.lookup(id);
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }
}