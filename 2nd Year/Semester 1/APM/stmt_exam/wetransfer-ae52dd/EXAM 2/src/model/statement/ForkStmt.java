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
        /*
        MyIStack<IStmt> stack = new MyStack<>();
        MyIDictionary<String, IValue> symTbl = state.getSymTable();
        MyIDictionary<String,IValue> mySymTbl = new MyDictionary<>();
        for (Map.Entry<String,IValue> entry:symTbl.getDictionary().entrySet())
            mySymTbl.update(entry.getKey(),entry.getValue());
        //return new PrgState(new MyStack<>(), state.getSymTable().clone_dict(),state.getOutputList(), this.stmt, state.getFileTable(), state.getHeap(), state.getId_thread() * 10);
        return new PrgState(stack,mySymTbl,state.getOut(),this.stmt, state.getFileTable(),state.getHeap());

         */

        var symTable = state.getSymTable();
        var heap = state.getHeap();
        var out = state.getOut();
        var fileTable = state.getFileTable();
        var lockTable = state.getLockTable();
        MyIStack<IStmt> newStack = new MyStack<>();
        MyIDictionary<String, IValue> newSymTable = new MyDictionary<>();
        for(Map.Entry<String, IValue> entry : symTable.getDictionary().entrySet()) {
            newSymTable.update(entry.getKey(), entry.getValue());
        }

        return new PrgState(newStack, newSymTable,  out, stmt, fileTable, heap, lockTable);

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
