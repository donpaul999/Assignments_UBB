package model.statement;

import exceptions.MyException;
import model.PrgState;
import model.type.Type;

public class VarDeclStmt implements IStmt {
    String name;
    Type typ;

    public VarDeclStmt(String s, Type deepCopy) {
        name = s;
        typ = deepCopy;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(new String(name), typ.deepCopy());
    }
}
