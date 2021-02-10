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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStmt implements IStmt {

    private String var;
    private static Lock lock = new ReentrantLock();

    public NewLockStmt(String var) {
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
                int addr = lockTable.allocate(-1);
                symTable.update(var, new IntValue(addr));
            } else {
                throw new MyException("NewLock: variable not of type int");
            }
        } else {
            throw new MyException("NewLock: variable not defined");

        }

        state.setExeStack(stack);
        state.setSymTable(symTable);
        state.setLockTable(lockTable);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (typeEnv.lookup(var).equals(new IntType())) {
            return typeEnv;
        }
        else throw new MyException("NewLock: " + var + " not of type int");
    }

    @Override
    public String toString() {
        return "NewLock( " +
                var +
                ')';
    }
}


