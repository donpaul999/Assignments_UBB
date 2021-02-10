/*
   public void addProgram(PrgState newPrg) {
        this.repository.addPrg(newPrg);
    }

    public void callGarbageCollector(List<PrgState>prgStates){
        if(prgStates.isEmpty())
            throw new MyException("Trying to do garbage collection on an empty repository.");

        var originalHeap = prgStates.get(0).getHeap().getContent();

        List<Integer> systemAddresses = new ArrayList<>();

        systemAddresses.addAll(getAddressesFromSymTable(Collections.singletonList(originalHeap.values())));
        for(PrgState prgState : prgStates)
            systemAddresses.addAll(getAddressesFromSymTable(Collections.singletonList(prgState.getSymTable().getDictionary().values())));

        var newHeap = conservativeGarbageCollector(systemAddresses, originalHeap);

        for(PrgState prgState : prgStates)
            prgState.getHeap().setContent(newHeap);
        /*
        PrgState progState = prgStates.get(0);
        var dummy = prgStates.stream()
                .map(program -> program.getSymTable().getDictionary().values())
                .collect(Collectors.toList());
        progState.getHeap().setContent(safeGarbageCollector(
                getAddressesFromSymTable(prgStates.stream().map(program -> program.getSymTable().getDictionary().values()).collect(Collectors.toList())),
                getAddressesFromHeap(progState.getHeap().getContent().values()),
                progState.getHeap().getContent()
        ));



        //prgStates.forEach(prgState -> {
        //    prgState.getHeap().setContent(safeGarbageCollector(getAddressesFromSymTable(prgState.getSymTable().getDictionary().values(),prgState.getHeap().getDictionary().values()),prgState.getHeap().getContent()));
        //});
    }

    Map<Integer, IValue> conservativeGarbageCollector(List<Integer> systemAddresses, Map<Integer,IValue> heap){
        return heap.entrySet().stream()
                .filter(e->systemAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddressesFromHeap(Collection<IValue> values) {
        return values.stream()
                .filter(val -> val instanceof  RefValue)
                .map(value -> {RefValue v1 = (RefValue) value; return v1.getAddr();})
                .collect(Collectors.toList());

    }

    public void allStep() throws InterruptedException {
        this.executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrg(repository.getPrgList());
        while(prgList.size() > 0){
            //prgList.get(0).getHeap().setContent(safeGarbageCollector(getAddressesFromSymTable(prgList.get(0).getSymTable().lookup(prgList.get(0).getHeap().getContent()))));
            callGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList=removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method //setPrgList of repository in order to change the repository
        // update the repository state
        //prgList=removeCompletedPrg(repository.getPrgList());
        repository.setPrgList(prgList);
    }

    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        //before the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // RUN concurrently one step for each of the existing PrgStates
        // -----------------------------------------------------------------------
        // prepare the list of callables
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        //start the execution of the callables
        // it returns the list of new created PrgStates (namely threads)
        List<PrgState> newPrgList = this.executor.invokeAll(callList).stream()
                .map(future -> { try { return future.get();}
                         catch (Exception e) {
                            //throw new MyException(e.getMessage());
                             System.out.println(e.getMessage());
                             return null; //change here pls
                        }
                        }).filter(p-> p != null )
                .collect(Collectors.toList());
        //add the new created threads to the list of existing threads
        prgList.addAll(newPrgList);
        //after the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Save the current programs in the repository
        repository.setPrgList(prgList);

    }

    //GARBAGE COLLECTOR
    public Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddresses, HashMap<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, IValue> safeGarbageCollector(List<Integer> addresses, List<Integer> addressesFromHeap, HashMap<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> addresses.contains(e.getKey()) ||
                        heap.values().stream()
                                .filter(v -> v instanceof RefValue)
                                .anyMatch(v -> ((RefValue) v).getAddr() == e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddressesFromSymTable(List<Collection<IValue>> symTablesValues) {
        return symTablesValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddr();
                })
                .collect(Collectors.toList());
    }
}
*/

package controller;

import exception.ExeFinished;
import exception.MyException;
import model.PrgState;
import model.adt.MyIStack;
import model.adt.MyLock;
import model.statement.IStmt;
import model.value.IValue;
import model.value.RefValue;
import repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static model.GarbageCollector.garbage_collector;
import static model.GarbageCollector.get_addresses_value_collection;

public class Controller {
    private IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repo) {
        this.repository = repo;
    }

    @Override
    public String toString() {
        return repository.getPrgList().get(0).getOriginalProgram().toString();
    }


    //Stream: a sequence of elements from a source that supports aggregate operations. // lecture 5

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    //GARBAGE COLLECTOR
    public List<Integer> getAddressesFromSymTable(Collection<IValue> symTablesValues) {
        return symTablesValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddr();
                })
                .collect(Collectors.toList());
    }

    public List<Integer> getAddressFromHeap(Collection<IValue> heapVal) {
        return heapVal.stream()
                .filter(val -> val instanceof RefValue)
                .map(value -> {
                    RefValue v1 = (RefValue) value;
                    return v1.getAddr();
                })
                .collect(Collectors.toList());
    }

    public Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddresses, HashMap<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, IValue> safeGarbageCollector(List<Integer> addresses, List<Integer> heapAddress, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> addresses.contains(e.getKey()) || heapAddress.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void collectGarbage(List<PrgState> prgList) {
        PrgState prg = prgList.get(0);
        var dummy = prgList.stream()
                .map(programState -> programState.getSymTable().getDictionary().values())
                .collect(Collectors.toList());
        prg.getHeap().setContent(safeGarbageCollector(
                getAddressesFromSymTable(prg.getSymTable().getDictionary().values()),
                getAddressFromHeap(prg.getHeap().getContent().values()),
                prg.getHeap().getContent()));
    }

    public void addService(PrgState prg) {
        repository.addPrg(prg);
    }

    //one step execution of a program
    public PrgState oneStep(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty())
            throw new MyException("Program stack is empty!");
        IStmt crtStatement = stk.pop();
        return crtStatement.execute(state);
    }

    @SuppressWarnings("unChecked")
    public void oneStepForAllPrg(List<PrgState> prgList)  {
        prgList.forEach(state -> repository.logPrgStateExec(state));

        List<Callable<PrgState>> call_list = prgList.stream()
                .map((PrgState state) -> (Callable<PrgState>)(state::oneStep))
                .collect(Collectors.toList());


        List<PrgState> newPrgStates = null;
        try {
            //Stream: a sequence of elements from a source that supports aggregate operations. // lecture 5
            newPrgStates = executor.invokeAll(call_list).stream()
                    .map(future -> {
                        try {return future.get();
                        }
                        catch(InterruptedException | ExecutionException e) {
                            throw (new RuntimeException(e.getMessage()));
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw (new RuntimeException(e.getMessage()));
        }

        prgList.addAll(newPrgStates);

        for (PrgState current_program: prgList) {
            repository.logPrgStateExec(current_program);
        }
    }

    //unchecked
    // complete execution of a program
    public void allStep() throws IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());
        while (prgList.size() > 0) {
            collectGarbage(prgList);
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList = removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdown();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repository.setPrgList(prgList);
    }

    public IRepository getRepository() {
        return repository;
    }

    public void allStepGUI() {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgStateList = removeCompletedPrg(repository.getPrgList());

        for (PrgState current_program: prgStateList) {
            current_program.getHeap().setContent(
                    garbage_collector(get_addresses_value_collection(current_program.getSymTable().getDictionary().values()),
                            get_addresses_value_collection(current_program.getHeap().getContent().values()),
                            current_program.getHeap().getContent()));
        }
        oneStepForAllPrg(prgStateList);

        executor.shutdown();
        if (prgStateList.size() <= 0) {
            throw(new ExeFinished("Execution finished"));
        }

        repository.setPrgList(prgStateList);
    }

    public MyLock getLockTable() {
        if (repository.getPrgList().size()==0) {
            return new MyLock();
        }
        else
            return repository.getPrgList().get(0).getLockTable();
    }
}

