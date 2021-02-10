package model.value;

import model.type.IntType;
import model.type.Type;

public class IntValue implements IValue {

    Integer val;

    public IntValue(Integer v){
        val=v;
    }

    public Integer getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntValue;
    }
}
