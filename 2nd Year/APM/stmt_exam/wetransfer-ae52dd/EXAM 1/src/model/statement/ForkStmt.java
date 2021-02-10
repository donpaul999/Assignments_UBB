package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.*;
import model.type.Type;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class ForkStmt implements IStmt {

    private IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public IStmt getStmt() {
        return stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        var symTable = state.getSymTable();
        var heap = state.getHeap();
        var out = state.getOut();
        var fileTable = state.getFileTable();
        MyIStack<IStmt> newStack = new MyStack<>();
        MyIDictionary<String, IValue> newSymTable = new MyDictionary<>();
        for(Map.Entry<String, IValue> entry : symTable.getDictionary().entrySet()) {
            newSymTable.update(entry.getKey(), entry.getValue());
        }

        return new PrgState(newStack, newSymTable,  out, stmt, fileTable, heap);

         /*
        MyIDictionary<String, IValue> newSymbolTable = new MyDictionary<>();
        for (Map.Entry<String, IValue> entry: state.getSymTable().getDictionary().entrySet()) {
            newSymbolTable.add(entry.getKey(), entry.getValue().createCopy());
        }
        MyIStack <IStmt> stack = new MyStack<>();
        stack.push(new NopStmt());
        stack.push(stmt);
        PrgState newProgram = new PrgState(stack, newSymbolTable, state.getOut(), stmt, state.getFileTable(), state.getHeap());
        //newProgram.setId();
        return newProgram;

          */

    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return stmt.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return "fork(" + this.stmt.toString() + ")";
    }
}
