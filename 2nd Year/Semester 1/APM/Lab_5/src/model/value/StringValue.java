package model.value;

import model.type.StringType;
import model.type.Type;

import java.util.Objects;

public class StringValue implements Value{
    private String str;

    public StringValue(String str) {
        this.str = str;
    }

    public StringValue() {
        str = "";
    }

    public String getValue() {
        return str;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return Objects.equals(str, that.str);
    }

    @Override
    public String toString() {
        return str;
    }
}
