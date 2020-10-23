package model.expression;

import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyDictionary;
import model.value.Value;

public class VarExp implements Exp {
    String id;

    public VarExp(String s) {
        id = s;
    }

    @Override
    public Value eval(IMyDictionary<String, Value> tbl) throws ExprException {
        return tbl.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
