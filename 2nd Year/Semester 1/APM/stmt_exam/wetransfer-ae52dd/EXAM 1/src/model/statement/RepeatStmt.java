package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.exp.Exp;
import model.exp.InvertExp;
import model.type.BoolType;
import model.type.Type;

import java.io.IOException;

public class RepeatStmt implements IStmt {

    private final IStmt repeatThis;
    private final Exp untilThis;

    public RepeatStmt(IStmt repeat, Exp until) {
        this.repeatThis = repeat;
        this.untilThis = until;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IStmt newRepeat = new CompStmt(repeatThis, new WhileStmt(new InvertExp(untilThis), repeatThis));
        state.getStk().push(newRepeat);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = untilThis.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            repeatThis.typeCheck(typeEnv.cloneDictionary());
            return typeEnv;
        } else {
            System.out.println(untilThis.toString());
            throw new RuntimeException("RepeatStmt: Expression must be bool type");
        }
    }

    @Override
    public String toString() {
        return " repeat " + repeatThis.toString() +
                " until " + untilThis.toString();
    }
}
