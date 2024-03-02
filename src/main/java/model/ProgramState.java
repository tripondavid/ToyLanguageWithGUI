package model;

import exception.MyException;
import model.adts.barrier.MyBarrierTableInterface;
import model.adts.dictionary.MyDictionary;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.adts.latch.MyLatchTableInterface;
import model.adts.list.MyListInterface;
import model.adts.lock.MyLockTableInterface;
import model.adts.procedure.MyProcedureTableInterface;
import model.adts.semaphore.MySemaphoreTableInterface;
import model.adts.stack.MyStackInterface;
import model.statements.StatementInterface;
import model.values.StringValue;
import model.values.ValueInterface;

import java.io.BufferedReader;

public class ProgramState {
    private MyStackInterface<StatementInterface> exeStack;
    private MyDictionaryInterface<String, ValueInterface> symTable;
    private MyListInterface<ValueInterface> out;
    private MyDictionaryInterface<StringValue, BufferedReader> fileTable;
    private MyHeapInterface<ValueInterface> heap;
    StatementInterface originalProgram;
    private static int currentID = 1;
    private int id = 1;
    private MyLockTableInterface lockTable;
    private MyBarrierTableInterface barrierTable;
    private MyLatchTableInterface latchTable;
    private MySemaphoreTableInterface semaphoreTable;
    private MyProcedureTableInterface procedureTable;

    public synchronized void setID() {
        currentID++;
        id = currentID;
    }

    public ProgramState(MyStackInterface<StatementInterface> exeStack, MyDictionaryInterface<String, ValueInterface> symTable, MyListInterface<ValueInterface> out, MyDictionaryInterface<StringValue, BufferedReader> fileTable, MyHeapInterface<ValueInterface> heap, StatementInterface originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram;
        this.id = 1;

        if (this.originalProgram != null)
            exeStack.push(this.originalProgram);
    }

    public ProgramState(MyStackInterface<StatementInterface> exeStack, MyDictionaryInterface<String, ValueInterface> symTable, MyListInterface<ValueInterface> out, MyDictionaryInterface<StringValue, BufferedReader> fileTable, MyHeapInterface<ValueInterface> heap, StatementInterface originalProgram, MyLockTableInterface lockTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram;
        this.lockTable = lockTable;
        this.id = 1;

        if (this.originalProgram != null)
            exeStack.push(this.originalProgram);
    }

    public ProgramState(MyStackInterface<StatementInterface> exeStack, MyDictionaryInterface<String, ValueInterface> symTable, MyListInterface<ValueInterface> out, MyDictionaryInterface<StringValue, BufferedReader> fileTable, MyHeapInterface<ValueInterface> heap, StatementInterface originalProgram, MyLockTableInterface lockTable, MyBarrierTableInterface barrierTable, MyLatchTableInterface latchTable, MySemaphoreTableInterface semaphoreTable, MyProcedureTableInterface procedureTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram;
        this.lockTable = lockTable;
        this.barrierTable = barrierTable;
        this.latchTable = latchTable;
        this.semaphoreTable = semaphoreTable;
        this.procedureTable = procedureTable;
        this.id = 1;

        if (this.originalProgram != null)
            exeStack.push(this.originalProgram);
    }

    public ProgramState(MyStackInterface<StatementInterface> exeStack, MyDictionaryInterface<String, ValueInterface> symTable, MyListInterface<ValueInterface> out, MyDictionaryInterface<StringValue, BufferedReader> fileTable, MyHeapInterface<ValueInterface> heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
    }

    public MyStackInterface<StatementInterface> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyStackInterface<StatementInterface> exeStack) {
        this.exeStack = exeStack;
    }

    public MyDictionaryInterface<String, ValueInterface> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyDictionaryInterface<String, ValueInterface> symTable) {
        this.symTable = symTable;
    }

    public MyListInterface<ValueInterface> getOut() {
        return out;
    }

    public void setOut(MyListInterface<ValueInterface> out) {
        this.out = out;
    }

    public MyDictionaryInterface<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyDictionaryInterface<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyHeapInterface<ValueInterface> getHeap() {
        return heap;
    }

    public void setHeap(MyHeapInterface<ValueInterface> heap) {
        this.heap = heap;
    }

    public StatementInterface getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(StatementInterface originalProgram) {
        this.originalProgram = originalProgram;
    }

    public static int getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(int currentID) {
        ProgramState.currentID = currentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyLockTableInterface getLockTable() {
        return lockTable;
    }

    public void setLockTable(MyLockTableInterface lockTable) {
        this.lockTable = lockTable;
    }

    public MyBarrierTableInterface getBarrierTable() {
        return barrierTable;
    }

    public void setBarrierTable(MyBarrierTableInterface barrierTable) {
        this.barrierTable = barrierTable;
    }

    public MyLatchTableInterface getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(MyLatchTableInterface latchTable) {
        this.latchTable = latchTable;
    }

    public MySemaphoreTableInterface getSemaphoreTable() {
        return semaphoreTable;
    }

    public void setSemaphoreTable(MySemaphoreTableInterface semaphoreTable) {
        this.semaphoreTable = semaphoreTable;
    }

    public MyProcedureTableInterface getProcedureTable() {
        return procedureTable;
    }

    public void setProcedureTable(MyProcedureTableInterface procedureTable) {
        this.procedureTable = procedureTable;
    }

    public boolean isNotCompleted() {
        return !this.exeStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException {
        if (exeStack.isEmpty()) throw new MyException("ProgramState stack is empty");
        StatementInterface currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    public void typeCheck() throws MyException {
        originalProgram.typeCheck(new MyDictionary<>());
    }

    @Override
    public String toString() {
        return ">>> ProgramState: " + "\n----------\n" +
                "* ID: \n" + Integer.toString(this.id) + "\n----------\n" +
                "* exeStack: \n" + exeStack.toString() + "\n----------\n" +
                "* symbolTable: \n" + symTable + "\n----------\n" +
                "* out: " + out + "\n----------\n" +
                "* fileTable=" + fileTable.toString() + "\n----------\n" +
                "* heap: " + heap.toString() + "\n----------\n\n";
    }
}
