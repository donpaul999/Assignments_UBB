package model.statement;
import model.exceptions.*;
import model.ADT.IMyStack;
import model.PrgState;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt snd;

    public CompStmt(IStmt deepCopy, IStmt deepCopy1) {
        first = deepCopy;
        snd = deepCopy1;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException {
        IMyStack<IStmt> stack = state.getStack();
        stack.push(snd);
        stack.push(first);
        state.setExeStack(stack);
        return state;
    }

    @Override
    public String toString() {
        return "(" + first + ";" + snd + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), snd.deepCopy());
    }
}
