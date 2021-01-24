package model.expression;

import model.ADT.IMyHeap;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyDictionary;
import model.type.Type;
import model.value.Value;

public interface Exp {
    public Value eval(IMyDictionary<String,Value> tbl, IMyHeap<Value> heap) throws ExprException;

    Exp deepCopy();
    Type typecheck(IMyDictionary<String,Type> typeEnv) throws ExprException;
}
