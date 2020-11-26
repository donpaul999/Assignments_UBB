package model.statement;
import model.ADT.IMyDictionary;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyList;
import model.ADT.IMyStack;
import model.PrgState;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.Type;
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
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stack = state.getStack();
        IMyList<Value> outConsole = state.getOutConsole();
        outConsole.add(exp.eval(state.getSymTable(), state.getHeap()));
        state.setExeStack(stack);
        state.setOutConsole(outConsole);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException {
        exp.typecheck(typeEnvironment);
        return typeEnvironment;
    }


}
