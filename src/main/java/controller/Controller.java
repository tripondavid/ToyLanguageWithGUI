package controller;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.adts.latch.MyLatchTableInterface;
import model.adts.list.MyListInterface;
import model.values.RefValue;
import model.values.StringValue;
import model.values.ValueInterface;
import repository.RepositoryInterface;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private final RepositoryInterface repository;
    private boolean displayFlag;
    private ExecutorService executor;

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
        displayFlag = false;
    }

    public List<ProgramState> getAllPrograms() {
        return repository.getProgramList();
    }
    public void addProgram(ProgramState programState) {
        repository.addProgram(programState);
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inputProgramList) {
        return inputProgramList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    public void allSteps() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramList());

        while (!programStateList.isEmpty()) {
            oneStepForAllPrograms(programStateList);

            programStateList = removeCompletedPrograms(repository.getProgramList());
        }

        executor.shutdownNow();

        repository.setProgramList(programStateList);
    }

    public void oneStepForAllProgramsGUI() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramList());
        repository.setProgramList(programStateList);
        if (programStateList.isEmpty())
            throw new MyException("Program is done");

        conservativeGarbageCollector(programStateList);
        oneStepForAllPrograms(programStateList);
        executor.shutdownNow();
    }

    public void oneStepForAllPrograms(List<ProgramState> programStateList) throws MyException {
        for (ProgramState programState : programStateList) repository.logProgramStateExecution(programState);

        List<Callable<ProgramState>> callableList = programStateList.stream().map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep)).toList();

        try {
            List<ProgramState> newProgramStateList = executor.invokeAll(callableList).stream().map(future -> { try {
                return future.get();
            } catch (ExecutionException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
                return null;
            }).filter(Objects::nonNull).toList();

            programStateList.addAll(newProgramStateList);
        } catch (InterruptedException e) {
            throw new MyException(e.getMessage());
        }

        conservativeGarbageCollector(programStateList);

        for (ProgramState programState : programStateList)repository.logProgramStateExecution(programState);

        repository.setProgramList(programStateList);
    }

    private ConcurrentHashMap<Integer, ValueInterface> safeGarbageCollector(List<Integer> symTableAddresses, ConcurrentHashMap<Integer, ValueInterface> heap) {
        return (ConcurrentHashMap<Integer, ValueInterface>) heap.entrySet().stream().filter(e -> symTableAddresses.contains(e.getKey())).collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddressesFromSymTable(Collection<ValueInterface> symTableValues, Collection<ValueInterface> heap) {
        return Stream.concat(
                heap.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {
                            RefValue v1 = (RefValue) v;
                            return v1.getAddress();
                        }),
                symTableValues.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {
                            RefValue v1 = (RefValue) v;
                            return v1.getAddress();
                        })
        ).collect(Collectors.toList());
    }

    private void conservativeGarbageCollector(List<ProgramState> programStateList) {
         List<Integer> allAddressesFromSymTables = Objects.requireNonNull(
                programStateList.stream()
                        .map(p -> getAddressesFromSymTable(p.getSymTable().getContent().values(), p.getHeap().getHeap().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null)).toList();

         for (ProgramState programState : programStateList) {
             programState.getHeap().setHeap(safeGarbageCollector(allAddressesFromSymTables, programStateList.getFirst().getHeap().getHeap()));
         }
    }

    public void setDisplayFlag(Boolean flag) {
        this.displayFlag = flag;
    }

    public String displayProgramState(ProgramState programState) {
        return programState.toString();
    }

    //public void typeCheck() throws MyException { repository.getProgramList().getFirst().typeCheck(); }

    public MyListInterface<ValueInterface> getOutGUI() {return getAllPrograms().getFirst().getOut();}

    public MyDictionaryInterface<StringValue, BufferedReader> getFileTableGUI() {return getAllPrograms().getFirst().getFileTable();}

    public MyHeapInterface<ValueInterface> getHeapTableGUI() {return getAllPrograms().getFirst().getHeap();}
    public MyLatchTableInterface getLatchTableGUI() {return getAllPrograms().getFirst().getLatchTable();}
}
