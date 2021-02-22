package model.type;

import model.value.BoolValue;
import model.value.IValue;

public class BoolType implements Type {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public IValue defaultValue() {
        BoolValue value = new BoolValue(false);
        return value;
    }
}
