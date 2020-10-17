package repository;

import exceptions.MyException;
import model.PrgState;
import model.statement.IStmt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Repository implements IRepo {
    private List<PrgState> states;
    private IStmt originalProgram;
    private String fileName;

    public Repository(PrgState prgState, String fileName) {
        this.originalProgram = prgState.getOriginalProgram();
        this.fileName = fileName;
    }

    @Override
    public List<PrgState> getPrgList() {
        return states;
    }

    @Override
    public void setPrgList(List<PrgState> list) {
        states = list;
    }

    @Override
    public PrgState getCrtPrg() {
        return states.get(states.size() - 1);
    }

    @Override
    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    @Override
    public void printPrgState(PrgState prgState) throws MyException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(prgState + "\n");
        }
        catch (IOException e) {
            throw new MyException(e.getMessage());
        }
    }
}
