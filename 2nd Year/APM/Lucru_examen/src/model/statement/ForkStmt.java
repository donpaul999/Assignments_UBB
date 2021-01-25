package model.statement;

import model.ADT.*;
import model.PrgState;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.StmtException;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.util.Map;

public class ForkStmt implements IStmt{
    private IStmt statement;

    public ForkStmt(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ADTException {
        IMyDictionary<String, Value> newSymbolTable = new MyDictionary<>();
        for (Map.Entry<String, Value> entry: state.getSymTable().getContent().entrySet()) {
            newSymbolTable.add(entry.getKey(), entry.getValue());
        }
        IMyStack <IStmt> stack = new MyStack<>();
        stack.push(statement);
        PrgState newProgram = new PrgState(stack, newSymbolTable, state.getOutConsole(), state.getFileTable(), state.getHeap());
        return newProgram;
    }


    @Override
    public IStmt deepCopy() {
        return new ForkStmt(statement.deepCopy());
    }

    @Override
    public String toString() {
        return "fork(" + statement + ")";
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException, ADTException {
        statement.typecheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }

}
