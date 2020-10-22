package repository;

import exceptions.MyException;
import model.PrgState;
import model.statement.IStmt;

import java.util.List;

public interface IRepo {
    public List<PrgState> getPrgList();
    PrgState getCrtPrg();
    IStmt getOriginalProgram();
    void printPrgState(PrgState prgState) throws MyException;

    void addState(PrgState state);
}
