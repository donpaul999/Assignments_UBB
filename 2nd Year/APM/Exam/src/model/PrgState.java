package model;

import model.ADT.*;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.exceptions.StmtException;
import model.statement.IStmt;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PrgState {
    private IMyStack<IStmt> exeStack;
    private IMyHeap<Value> heap;
    private IMyDictionary<String, Value> symTable;
    private IMyList<Value> out;
    private IMyDictionary<StringValue, BufferedReader> fileTable;
    private IStmt originalProgram; //optional field, but good to have
    private int stateID;
    private static int freeID = 0;

    public PrgState(IMyStack<IStmt> stk, IMyDictionary<String, Value> symtbl, IMyList<Value> ot, IMyDictionary<StringValue, BufferedReader> fT, IMyHeap<Value> givenHeap, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = fT;
        originalProgram = prg;
        heap = givenHeap;
        stateID = getNewPrgStateID();
        exeStack.push(prg);
    }

    public PrgState(IMyStack<IStmt> executionStack, IMyDictionary<String, Value> symbolTable, IMyList<Value> outputConsole, IMyDictionary<StringValue, BufferedReader> fileTable, IMyHeap<Value> heapTable) {
        this.exeStack = executionStack;
        this.symTable = symbolTable;
        this.out = outputConsole;
        this.fileTable = fileTable;
        this.heap = heapTable;
        stateID = getNewPrgStateID();
    }

    public PrgState(IMyStack<IStmt> stack, MyDictionary<String, Value> stringValueMyDictionary, MyList<Value> valueMyList) {
        exeStack = stack;
        symTable = stringValueMyDictionary;
        out = valueMyList;
        heap = new MyHeap<Value>();
    }

    public PrgState oneStep() throws MyException, StmtException, ExprException, ADTException {
        if (exeStack.isEmpty()) {
            throw new MyException("Program stack is empty");
        }
        IStmt currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    public boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

        public IMyStack<IStmt> getStack() {
        return exeStack;
    }

    public static synchronized int getNewPrgStateID() {
        ++freeID;
        return freeID;
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
        str.append("ID: ").append(stateID).append(" \n");
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

    public static PrgState emptyPrgState(IStmt program) {
        return new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String, Value>(),
                new MyList<Value>(),
                new MyDictionary<StringValue, BufferedReader>(),
                new MyHeap<Value>(),
                program);
    }
    public int getStateID(){
        return stateID;
    }
}
