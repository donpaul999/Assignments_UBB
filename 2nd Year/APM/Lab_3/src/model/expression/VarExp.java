package model.expression;

import exceptions.MyException;
import model.ADT.IMyDictionary;
import model.value.Value;

public class VarExp implements Exp {
    String id;

    public VarExp(String s) {
        id = s;
    }

    @Override
    public Value eval(IMyDictionary<String, Value> tbl) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(new String(id));
    }

    @Override
    public String toString() {
        return id;
    }
}
