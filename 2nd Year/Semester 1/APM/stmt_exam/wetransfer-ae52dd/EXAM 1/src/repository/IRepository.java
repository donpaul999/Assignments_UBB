package repository;

import exception.MyException;
import model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void addPrg(PrgState newPrg);
    //PrgState getCrtPrg();

    void logPrgStateExec(PrgState prg) throws MyException;

    //A5
    List<PrgState> getPrgList();
    void setPrgList( List<PrgState> list );
}
