package model;

import model.ADT.*;
import model.statement.IStmt;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;

public class PrgState {
    private IMyStack<IStmt> exeStack;
    private IMyHeap<Value> heap;
    private IMyDictionary<String, Value> symTable;
    private IMyList<Value> out;
    private IMyDictionary<StringValue, BufferedReader> fileTable;
    private IStmt originalProgram; //optional field, but good to have

    public PrgState(IMyStack<IStmt> stk, IMyDictionary<String, Value> symtbl, IMyList<Value> ot, IMyDictionary<StringValue, BufferedReader> fT, IMyHeap<Value> givenHeap, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = fT;
        originalProgram = prg;
        heap = givenHeap;
        stk.push(prg);
    }

    public PrgState(IMyStack<IStmt> stack, MyDictionary<String, Value> stringValueMyDictionary, MyList<Value> valueMyList) {
        exeStack = stack;
        symTable = stringValueMyDictionary;
        out = valueMyList;
        heap = new MyHeap<Value>();
    }

    public IMyStack<IStmt> getStack() {
        return exeStack;
    }

    public IMyDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public IMyHeap<Value> getHeap() { return heap; }
    public IStmt getOriginalProgram(){
        return originalProgram;
    }

    public IMyDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Program state\n");
        str.append("Exe Stack: ").append(exeStack).append(" \n");
        str.append("Sym Table: ").append(symTable).append(" \n");
        str.append("Heap: ").append(heap).append(" \n");
        str.append("Output Console: ").append(out).append(" \n");
        str.append("File Table: ").append(fileTable).append(" \n");
        return str.toString();
    }

    public void setExeStack(IMyStack<IStmt> stack) {
        exeStack = stack;
    }

    public void setHeap(IMyHeap<Value> gHeap) {
        heap = gHeap;
    }

    public void setSymTable(IMyDictionary<String, Value> table) {
        symTable = table;
    }

    public IMyList<Value> getOutConsole() {
        return out;
    }

    public void setOutConsole(IMyList<Value> outConsole) {
        out = outConsole;
    }
}
