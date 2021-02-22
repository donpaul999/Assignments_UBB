package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.type.Type;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    public IStmt getFirst() {
        return first;
    }

    public IStmt getSnd() {
        return snd;
    }

    @Override
    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        //MyIDictionary<String,Type> typEnv1 = first.typeCheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = snd.typeCheck(typEnv1);
        // return typEnv2;
        return snd.typeCheck(first.typeCheck(typeEnv));
    }
}
