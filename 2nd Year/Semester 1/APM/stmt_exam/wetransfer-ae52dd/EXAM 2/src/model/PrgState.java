package model;

import exception.EmptyStackException;
import exception.MyException;
import model.adt.*;
import model.statement.IStmt;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {

    MyIStack<IStmt> exeStack;
    MyIDictionary<String, IValue> symTable;
    MyIList<IValue> out;
    IStmt originalProgram; //optional field, but good to have
    FileTable<StringValue,BufferedReader> fileTable;
    //IHeap<Integer,IValue> heap;
    IHeap<Integer,IValue> heap;
    MyLock lockTable;
    private int id;
    private static int freeId=0;

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, IValue> symTable, MyIList<IValue> out, IStmt originalProgram, FileTable<StringValue,BufferedReader> fileTable, IHeap<Integer,IValue> heap, MyLock lockTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.exeStack.push(originalProgram);
        this.fileTable=fileTable;
        this.heap=heap;
        this.lockTable=lockTable;
        this.id=getFreeId();
        setFreeId(getFreeId()+1);
    }

    public MyLock getLockTable() {
        return lockTable;
    }

    public void setLockTable(MyLock lockTable) {
        this.lockTable = lockTable;
    }

    public static synchronized int getFreeId() {
        return freeId;
    }

    public static synchronized void setFreeId(int newId) {
        freeId=newId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIStack<IStmt> getStk() {
        return exeStack;
    }

    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIList<IValue> getOut() {
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    @Override
    public String toString() {
        return "ExeStack: \n"+this.exeStack.toString() + "SymTable: \n"+ this.symTable.toString() + "Out: " + this.out.toString() + "\nFileTable: " + this.fileTable.toString() +"\n";
    }

    public FileTable<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IHeap<Integer,IValue> getHeap() {
        return heap;
    }

    public void setHeap(IHeap<Integer,IValue> heap) {
        this.heap = heap;
    }

    public boolean isNotCompleted() {
        return (!this.exeStack.isEmpty());
    }

    public PrgState oneStep() throws MyException, IOException {
        if (this.exeStack.isEmpty())
            throw new MyException("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);   }

    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable=symTable;
    }
}