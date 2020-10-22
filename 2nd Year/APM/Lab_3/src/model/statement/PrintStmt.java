package model.statement;
import exceptions.MyException;
import model.ADT.IMyList;
import model.ADT.IMyStack;
import model.PrgState;
import model.expression.Exp;
import model.value.Value;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp deepCopy) {
        exp = deepCopy;
    }

    @Override
    public String toString(){
        return "print(" + exp.toString() +")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IMyStack<IStmt> stack = state.getStack();
        IMyList<Value> outConsole = state.getOutConsole();
        outConsole.add(exp.eval(state.getSymTable()));
        state.setExeStack(stack);
        state.setOutConsole(outConsole);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }


}
