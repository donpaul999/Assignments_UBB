package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.exp.Exp;
import model.exp.InvertExp;
import model.type.BoolType;
import model.type.Type;

import java.io.IOException;

public class DoWhileStmt implements IStmt{

    private final IStmt doThis;
    private final Exp whileThis;

    public DoWhileStmt(IStmt doThis, Exp whileThis) {
        this.doThis = doThis;
        this.whileThis = whileThis;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IStmt newRepeat = new CompStmt(doThis, new WhileStmt(whileThis, doThis));
        state.getStk().push(newRepeat);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = whileThis.typeCheck(typeEnv);
        if (type.equals(new BoolType())) {
            doThis.typeCheck(typeEnv.cloneDictionary());
            return typeEnv;
        }
        else throw new RuntimeException("DoWhileStmt: Expression must be book type");
    }

    @Override
    public String toString() {
        return "do" + doThis.toString() +
                "while" + whileThis.toString();
    }
}
