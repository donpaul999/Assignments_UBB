package model.statement;

import exception.InvalidDataType;
import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.type.*;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;

public class VarDeclStmt implements IStmt{
    String name;
    Type typ;

    public VarDeclStmt(String name, Type typ) {
        this.name = name;
        this.typ = typ;
    }

    public String getName() {
        return name;
    }

    public Type getTyp() {
        return typ;
    }

    @Override
    public String toString() {
        return "Type "+ this.typ.toString()+ " " + this.name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIDictionary<String, IValue> symTable= state.getSymTable();
        if(symTable.isDefined(name))
            throw new MyException("Variable is already declared");
        else {
            symTable.add(this.name, this.typ.defaultValue());
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(name,typ);
        return typeEnv;
    }
}
