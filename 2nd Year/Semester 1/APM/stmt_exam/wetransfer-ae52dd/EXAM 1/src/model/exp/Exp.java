package model.exp;

import exception.MyException;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.type.Type;
import model.value.IValue;

public interface Exp {

    //IValue eval(MyIDictionary<String,IValue> symTable) throws MyException;
    IValue eval(MyIDictionary<String,IValue> symTable, IHeap<Integer,IValue> heap) throws MyException;
    String toString();
    Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
