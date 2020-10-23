package model.expression;

import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyDictionary;
import model.value.Value;

public interface Exp {
    public Value eval(IMyDictionary<String,Value> tbl) throws ExprException;

}
