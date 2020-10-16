package model.value;

import model.type.BoolType;
import model.type.Type;

public class BoolValue implements Value {
    boolean val;

    public BoolValue(boolean v) {
        this.val = v;
    }

    public boolean getValue() {
        return val;
    }

    @Override
    public String toString() {
        return val ? "true" : "false";
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }
}
