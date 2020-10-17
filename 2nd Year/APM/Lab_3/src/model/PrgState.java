package model;

import model.ADT.IMyDictionary;
import model.ADT.IMyList;
import model.ADT.IMyStack;
import model.statement.IStmt;
import model.value.Value;

public class PrgState {
    private IMyStack<IStmt> exeStack;
    private IMyDictionary<String, Value> symTable;
    private IMyList<Value> out;
    private IStmt originalProgram; //optional field, but good to have

    PrgState(IMyStack<IStmt> stk, IMyDictionary<String,Value> symtbl, IMyList<Value> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg.deepCopy();
        stk.push(prg);
    }

    public IMyStack<IStmt> getStack() {
        return exeStack;
    }

    public IMyDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public IStmt getOriginalProgram(){
        return originalProgram;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Program state\n");
        str.append("Exe Stack: ").append(exeStack).append(" \n");
        str.append("Sym Table: ").append(symTable).append(" \n");
        str.append("Out List: ").append(out).append(" \n");
        return str.toString();
    }
}
