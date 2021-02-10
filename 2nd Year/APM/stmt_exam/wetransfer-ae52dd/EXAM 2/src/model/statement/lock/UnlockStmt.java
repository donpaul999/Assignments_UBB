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

public class UnlockStmt implements IStmt {

    String var;

    public UnlockStmt(String var) {
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
                    throw new MyException("UnLock: lock does not exist");
                } else {
                    if (lockTable.get(foundIndex.getVal()).equals(state.getId())) {
                        lockTable.update(foundIndex.getVal(), -1);
                    }
                }
            } else {
                throw new MyException("UnLock: var not of type int");
            }
        } else throw new MyException("UnLock: var not defined");
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
        throw new MyException("Unlock: Variable not of type int");
    }

    @Override
    public String toString() {
        return "Unlock(" + var + ')';
    }
}
