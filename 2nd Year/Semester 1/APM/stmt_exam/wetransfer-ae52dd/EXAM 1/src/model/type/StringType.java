package model.type;

import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

public class StringType implements Type{

    public static StringType T = new StringType();
    //private StringType(){}


    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }
}
