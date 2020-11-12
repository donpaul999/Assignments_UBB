package repository;

import model.exceptions.MyException;
import model.PrgState;
import model.statement.IStmt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Repository implements IRepo {
    private List<PrgState> states;
    private IStmt originalProgram;
    private String fileName;

    public Repository(PrgState prgState, String fileName) throws IOException, MyException {
        this.originalProgram = prgState.getOriginalProgram();
        this.fileName = fileName;
        File yourFile = new File(fileName);
        yourFile.createNewFile();
        try (FileWriter fileWriter = new FileWriter(yourFile)) {
            fileWriter.write("");
        }
        catch (IOException e) {
            throw new MyException(e.getMessage());
        }
        states = new LinkedList<>();
    }

    public Repository() {
        states = new LinkedList<>();
    }

    public Repository(String givenFile) {
        this.fileName = givenFile;
        states = new LinkedList<>();
    }

    @Override
    public List<PrgState> getPrgList() {
        return states;
    }


    @Override
    public PrgState getCrtPrg() {
        PrgState state = states.get(0);
        states.remove(0);
        return state;
    }

    @Override
    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    @Override
    public void printPrgState(PrgState prgState) throws MyException, IOException {
        File yourFile = new File(fileName);
        yourFile.createNewFile();
        try (FileWriter fileWriter = new FileWriter(yourFile, true)) {
            fileWriter.write(prgState + "\n");
        }
        catch (IOException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void addState(PrgState state) {
        states.add(state);
    }
}
