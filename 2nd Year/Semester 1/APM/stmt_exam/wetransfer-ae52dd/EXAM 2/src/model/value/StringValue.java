package model.value;

import model.type.StringType;
import model.type.Type;

public class StringValue implements IValue{

    private String val;

    public StringValue(String v){
        val=v;
    }

    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StringValue)
            return (this.getVal() == ((StringValue)obj).getVal());
        return false;
    }
}
