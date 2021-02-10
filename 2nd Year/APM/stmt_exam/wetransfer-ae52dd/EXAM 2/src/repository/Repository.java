package repository;

import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIList;
import model.adt.MyIStack;
import model.statement.IStmt;
import model.value.IValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    IStmt basePrg;
    PrgState currentPrg;

    private List<PrgState> myPrgStates;
    private String logFilePath;

    public Repository(String logFilePath) {
        this.myPrgStates = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    @Override
    public void addPrg(PrgState newPrg) {
        this.myPrgStates.add(newPrg);
    }

    @Override
    public List<PrgState> getPrgList() {
        return myPrgStates;
    }

    @Override
    public void setPrgList(List<PrgState> list) {
        this.myPrgStates=list;
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logFile.write(prg.toString() + "\n");

            logFile.close();
        } catch (Exception e) {
            throw new MyException("cannot open logfile");
        }
    }

}
