package model.statement;

import model.ADT.*;
import model.PrgState;
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
    public PrgState execute(PrgState state) throws StmtException {
        IMyStack<IStmt> stk = state.getStack();
        IMyDictionary<String, Value> symTable = state.getSymTable();
        IMyHeap<Value> heap = state.getHeap();
        IMyList<Value> outList = state.getOutConsole();
        IMyDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        MyStack<IStmt> newStk = new MyStack<IStmt>();
        MyDictionary<String, Value> newSymTable = new MyDictionary<String, Value>();
        for (Map.Entry<String, Value> entry: symTable.getContent().entrySet()) {
            newSymTable.update(new String(entry.getKey()), entry.getValue().deepCopy());
        }
        return new PrgState(newStk, newSymTable, outList, fileTable, heap, statement);
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
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException {
        statement.typecheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }

}
