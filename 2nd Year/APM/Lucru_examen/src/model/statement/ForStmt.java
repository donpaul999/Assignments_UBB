package model.statement;

import model.ADT.IMyDictionary;
import model.ADT.IMyStack;
import model.PrgState;
import model.exceptions.ExprException;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class ForStmt implements IStmt {
    private Exp exp;
    private IStmt stmt1;
    private IStmt stmt2;
    private IStmt statement;

    public ForStmt(IStmt stmt1, Exp exp, IStmt stmt2, IStmt statement) {
        this.stmt1 = stmt1;
        this.exp = exp;
        this.stmt2 = stmt2;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stk = state.getStack();
        IMyDictionary<String, Value> symTable = state.getSymTable();

        IStmt st = new CompStmt(stmt1, new WhileStmt(exp, new CompStmt(statement, stmt2)));

        stk.push(st.deepCopy());
        state.setExeStack(stk);
        return null;
    }


    @Override
    public IStmt deepCopy() {
        return new ForStmt(stmt1.deepCopy(), exp.deepCopy(), stmt2.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return "(for (" + stmt1 + ";" + exp + ";" + stmt2 + ") " + statement + ")";
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException {
        Type expType = exp.typecheck(typeEnvironment);
        if (expType.equals(new BoolType())) {
            statement.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else {
            throw new StmtException("The condition in " + this.toString() + " is not a boolean");
        }
    }
}