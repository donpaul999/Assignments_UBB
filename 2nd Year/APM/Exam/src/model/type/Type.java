package model.type;

import model.value.Value;

public interface Type {
    Type deepCopy();
    Value defaultValue();

}
