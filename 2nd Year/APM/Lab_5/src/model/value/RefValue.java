package model.value;

import model.type.RefType;
import model.type.Type;

public class RefValue implements Value {
    private Type locationType;
    private int address;

    public RefValue(Type locationType, int address) {
        this.locationType = locationType;
        this.address = address;
    }

    public Type getLocationType() {
        return locationType;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(locationType.deepCopy(), address);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }
}