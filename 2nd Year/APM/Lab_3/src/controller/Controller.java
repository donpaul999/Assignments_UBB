package controller;

import exceptions.MyException;
import model.ADT.IMyStack;
import model.PrgState;
import model.statement.IStmt;
import repository.IRepo;

public class Controller {
    private IRepo repository;

    public  PrgState oneStep(PrgState state) throws MyException {
        IMyStack<IStmt> stk=state.getStack();
        if(stk.isEmpty())
            throw  new MyException("prgstate stack is empty");
        IStmt  crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException {
        PrgState prg = repository.getCrtPrg();
        // PrgState prg = repository.getPrgList();
        // repo is the controller field of type MyRepoInterface
        // here you can display the prg state
         while (!prg.getStack().isEmpty()){
             oneStep(prg);
             repository.printPrgState(prg);
             //here you can display the prg state
         }
    }


}
