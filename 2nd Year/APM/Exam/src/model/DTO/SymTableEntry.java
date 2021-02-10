package model.DTO;

import javafx.beans.property.SimpleStringProperty;
import model.value.Value;

public class SymTableEntry {
    private SimpleStringProperty name;
    private SimpleStringProperty value;
    private String originalName;
    private Value originalValue;

    public SymTableEntry(String name, Value value) {
        this.name = new SimpleStringProperty(String.valueOf(name));
        this.value = new SimpleStringProperty(value.toString());
        this.originalName = name;
        this.originalValue = value.deepCopy();
    }

    public String getName() {
        return originalName;
    }

    public Value getValue() {
        return originalValue;
    }
}