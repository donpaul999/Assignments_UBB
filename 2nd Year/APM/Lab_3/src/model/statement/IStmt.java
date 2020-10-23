package model.statement;
import exceptions.*;
import model.PrgState;

public interface IStmt {
   public PrgState execute(PrgState state) throws MyException; // execution method for a statement
}
