package model.statement;

import model.exceptions.ADTException;
import model.exceptions.MyException;
import model.ADT.IMyDictionary;
import model.ADT.IMyStack;
import model.PrgState;
import model.exceptions.StmtException;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

public class VarDeclStmt implements IStmt {
    String name;
    Type type;

    public VarDeclStmt(String s, Type deepCopy) {
        name = s;
        type = deepCopy;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ADTException {
        IMyStack<IStmt> stack = state.getStack();
        IMyDictionary<String, Value> table = state.getSymTable();
        if (table.isDefined(name)) {
            throw new StmtException("Variable is already declared");
        } else {
            if (type.equals(new IntType())) {
                table.add(name, new IntValue());
            }else if (type.equals(new BoolType())) {
                table.add(name, new BoolValue());
            }else if (type.equals(new StringType())) {
                table.add(name, new StringValue());
            }  else {
                throw new StmtException("Type does not exist");
            }
        }
        state.setSymTable(table);
        state.setExeStack(stack);
        return state;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(new String(name), type.deepCopy());
    }
}
