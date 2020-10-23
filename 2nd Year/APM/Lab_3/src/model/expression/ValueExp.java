package model.expression;

import exceptions.MyException;
import model.ADT.IMyDictionary;
import model.value.Value;

public class ValueExp implements Exp {
    Value e;

    public ValueExp(Value deepCopy) {
        e = deepCopy;
    }

    @Override
    public Value eval(IMyDictionary<String, Value> tbl) throws MyException {
        return e;
    }



    @Override
    public String toString() {
        return e.toString();
    }
}
