package repository;

import model.exceptions.MyException;
import model.PrgState;
import model.statement.IStmt;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    public List<PrgState> getPrgList();
    PrgState getCrtPrg();
    IStmt getOriginalProgram();
    void printPrgState(PrgState prgState) throws MyException, IOException;
    void setPrgList(List<PrgState> list);

    void addState(PrgState state);

    PrgState getPrgStateWithId(int currentId);
}
