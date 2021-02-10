package model.value;

import model.type.RefType;
import model.type.Type;

public class RefValue implements IValue {

    private int address;
    private Type locationType;

    public RefValue(int i, Type inner) {
        this.address=i;
        this.locationType=inner;
    }

    public int getAddr() { return address; }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return "RefValue{" +
                "address=" + address +
                ", locationType=" + locationType +
                '}';
    }
}
