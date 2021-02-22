package model.expression;

import model.ADT.IMyHeap;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyDictionary;
import model.value.Value;

public interface Exp {
    public Value eval(IMyDictionary<String,Value> tbl, IMyHeap<Value> heap) throws ExprException;

    Exp deepCopy();
}
