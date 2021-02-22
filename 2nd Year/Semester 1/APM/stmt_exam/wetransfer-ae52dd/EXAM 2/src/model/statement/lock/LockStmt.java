package model.statement.lock;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.adt.MyLock;
import model.statement.IStmt;
import model.type.IntType;
import model.type.Type;
import model.value.IValue;
import model.value.IntValue;

import java.io.IOException;

public class LockStmt implements IStmt {

    String var;

    public LockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stack = state.getExeStack();
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        MyLock lockTable = state.getLockTable();

        if (symTable.isDefined(var)) {
            IValue varVal = symTable.lookup(var);
            if (varVal.getType().equals(new IntType())) {
                IntValue foundIndex = (IntValue) symTable.lookup(var);
                if (!lockTable.exist(foundIndex.getVal())) {
                    throw new MyException("Lock: lock does not exist");
                } else {
                    if (lockTable.get(foundIndex.getVal()) == -1) {
                        lockTable.update(foundIndex.getVal(), state.getId());
                    } else stack.push(this);
                }
            } else {
                throw new MyException("Lock: var not of type int");
            }
        } else throw new MyException("Lock: var not defined");
        state.setLockTable(lockTable);
        state.setSymTable(symTable);
        state.setExeStack(stack);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type varType = typeEnv.lookup(var);
        if (varType.equals(new IntType())) {
            return typeEnv;
        }
        throw new MyException("Lock: Variable not of type int");
    }

    @Override
    public String toString() {
        return "Lock(" + var + ')';
    }
}
