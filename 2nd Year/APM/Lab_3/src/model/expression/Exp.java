package model.expression;

import exceptions.MyException;
import model.ADT.IMyDictionary;
import model.value.Value;

public interface Exp {
    public Value eval(IMyDictionary<String,Value> tbl) throws MyException;

    Exp deepCopy();
}
