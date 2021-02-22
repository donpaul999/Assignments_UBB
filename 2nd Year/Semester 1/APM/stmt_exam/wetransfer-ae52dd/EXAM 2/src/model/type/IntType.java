package model.type;

import model.value.IValue;
import model.value.IntValue;

public class IntType implements Type{

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IValue defaultValue() {
        IntValue value = new IntValue(0);
        return value;
    }
}
