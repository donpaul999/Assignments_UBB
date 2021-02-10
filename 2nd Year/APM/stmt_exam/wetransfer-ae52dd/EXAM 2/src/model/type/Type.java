package model.type;

import model.value.IValue;

public interface Type {
    boolean equals(Object ob);
    IValue defaultValue();
}
