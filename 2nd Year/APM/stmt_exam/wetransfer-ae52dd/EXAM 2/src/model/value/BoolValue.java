package model.value;

import model.type.BoolType;
import model.type.Type;

public class BoolValue implements IValue {
    private boolean val;

    public BoolValue(boolean val) {
        this.val = val;
    }

    public boolean getVal() {
        return val;
    }


    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolValue;
    }
}
