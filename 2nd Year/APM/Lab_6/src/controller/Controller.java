package controller;

import model.ADT.*;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.PrgState;
import model.exceptions.StmtException;
import model.expression.*;
import model.statement.*;
import model.type.*;
import model.value.*;
import repository.IRepo;
import repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepo repository;
    private ExecutorService executor;


    public Controller(IRepo repo) {
        this.repository = repo;
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heap) {
        return Stream.concat(
                heap.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {
                            RefValue v1 = (RefValue) v;
                            return v1.getAddress();
                        })
                ,symTableValues.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {
                            RefValue v1 = (RefValue) v;
                            return v1.getAddress();
                        }))
                .collect(Collectors.toList());
    }

    Map<Integer, Value> unsafeGarbageCollector(List<Integer> addresses, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(elem -> addresses.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    void garbageCollector(List<PrgState> prgList) {
        List<Integer> adresses = Objects.requireNonNull(prgList.stream()
                .map(p -> getAddrFromSymTable(
                        p.getSymTable().getContent().values(),
                        p.getHeap().getContent().values()))
                .map(Collection::stream)
                .reduce(Stream::concat).orElse(null)).collect(Collectors.toList());
        prgList.forEach(programState -> {
            programState.getHeap().setContent(
                    unsafeGarbageCollector(
                            adresses,
                            prgList.get(0).getHeap().getContent()
                    ));
        });
    }


    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException, MyException {
       // System.out.println(prgList);

        prgList.forEach(prg-> {
            try {
                repository.printPrgState(prg);
            } catch (MyException | IOException e) {
                e.printStackTrace();
            }
        });
        List <Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p)->(Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());
        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (ExecutionException | InterruptedException e) {
                            //System.out.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            prgList.addAll(newPrgList);
        }
        catch(InterruptedException e)
        {
            throw  new MyException(e.getMessage());
        }
        prgList.forEach(prg -> {
            try {
                repository.printPrgState(prg);
            } catch (MyException | IOException e) {
                e.printStackTrace();
            }
        });


        repository.setPrgList(prgList);
    }


    List<PrgState> removeCompletedPrograms(List<PrgState> prgList){
        return prgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }


    public PrgState oneStep(PrgState state) throws MyException, ADTException, StmtException, ExprException {
        IMyStack<IStmt> stack = state.getStack();
        if (stack.isEmpty()) {
            throw new MyException("Stack is empty");
        }
        IStmt currentStmt = stack.pop();
        return currentStmt.execute(state);
    }


    public void allStep() throws MyException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrograms(repository.getPrgList());
        while(prgList.size() > 0){
            garbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrograms(repository.getPrgList());
        }

        executor.shutdownNow();

        repository.setPrgList(prgList);
    }


    public void example() {
        IMyStack<IStmt> stack = new MyStack<>();
        IStmt example_1 = new CompStmt(
                new VarDeclStmt("x", new IntType()),
                new CompStmt(
                        new AssignStmt("x", new ValueExp(new IntValue(17))),
                        new PrintStmt(new VarExp("x"))
                )
        );

        IStmt example_2 = new CompStmt(
                new VarDeclStmt("x" , new IntType()),
                new CompStmt(new AssignStmt("x", new ArithExp(
                                        new ValueExp(new IntValue(3)),
                                        new ArithExp(
                                                new ValueExp(new IntValue(5)), new ValueExp(new IntValue(7)), '*'
                                        ),
                                        '+'
                                    )
                             ),
                        new PrintStmt(new VarExp("x"))
                )
        );

        IStmt example_3 = new CompStmt(
                new VarDeclStmt("s" , new BoolType()),
                new CompStmt(new VarDeclStmt("x", new IntType()),
                        new CompStmt(
                                new AssignStmt("s", new ValueExp(new BoolValue(true))),
                                new CompStmt(
                                    new IfStmt(
                                            new VarExp("s"),
                                            new AssignStmt("x", new ValueExp(new IntValue(20))),
                                            new AssignStmt("x", new ValueExp(new IntValue(2)))
                                    ),
                                    new PrintStmt(new VarExp("x"))
                                )
                        )
                )
        );

        stack.push(example_3);
        PrgState state = new PrgState(stack, new MyDictionary<String, Value>(), new MyList<Value>());
        System.out.println(state);
        repository.addState(state);
    }


    public void typecheckOriginalProgram() throws StmtException, ExprException {
        MyDictionary<String, Type> typeEnvironment = new MyDictionary<String, Type>();
        IStmt originalProgram = repository.getOriginalProgram();
        originalProgram.typecheck(typeEnvironment);
    }

    @Override
    public String toString() {
        return repository.getOriginalProgram().toString();
    }

    public IRepo getRepository() {
        return repository;
    }

    public void executeOneStep() throws MyException, InterruptedException {
        List<PrgState> list = removeCompletedPrograms(repository.getPrgList());
        garbageCollector(list);
        oneStepForAllPrg(list);
        list = removeCompletedPrograms(repository.getPrgList());
        repository.setPrgList(list);
        if (list.isEmpty()) {
            executor.shutdownNow();
        }
    }
}

