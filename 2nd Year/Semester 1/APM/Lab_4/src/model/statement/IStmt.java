package model.statement;
import model.exceptions.*;
import model.PrgState;

public interface IStmt {
   public PrgState execute(PrgState state) throws StmtException, ExprException, ADTException; // execution method for a statement

    IStmt deepCopy();
}
