package model.type;

import model.value.IValue;
import model.value.RefValue;

public class RefType implements Type{

    Type inner;

    public RefType(Type inner) {
        this.inner=inner;
    }

    public Type getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof  RefType)
            return inner.equals(((RefType) another).getInner());
        else
            return false;
    }

    @Override
    public String toString() {
        return "Ref(" +inner.toString()+")";
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0,inner);
    }
}
