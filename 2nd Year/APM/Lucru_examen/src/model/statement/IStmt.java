package model.statement;
import model.ADT.IMyDictionary;
import model.exceptions.*;
import model.PrgState;
import model.type.Type;

public interface IStmt {
   public PrgState execute(PrgState state) throws StmtException, ExprException, ADTException; // execution method for a statement

    IStmt deepCopy();

    IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException, ADTException;

}
