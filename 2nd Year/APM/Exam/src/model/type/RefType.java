package model.type;

import model.value.RefValue;
import model.value.Value;

import java.util.Objects;

public class RefType implements Type {
    private Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefType refType = (RefType) o;
        return Objects.equals(inner, refType.inner);
    }


    @Override
    public Value defaultValue() {
        return new RefValue(inner, 0);
    }

    @Override
    public Type deepCopy() {
        return new RefType(inner.deepCopy());
    }

    @Override
    public String toString() {
        return "Ref(" + inner + ")";
    }
}