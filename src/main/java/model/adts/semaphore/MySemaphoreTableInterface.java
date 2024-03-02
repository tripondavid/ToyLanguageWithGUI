package model.adts.semaphore;

import model.adts.barrier.Pair;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface MySemaphoreTableInterface {
    int allocate(Pair<Integer, List<Integer>> val);
    Pair<Integer, List<Integer>> deallocate(int address);
    Pair<Integer, List<Integer>> get(int address);
    boolean exists(int address);
    void put(int address, Pair<Integer, List<Integer>> val);
    ConcurrentHashMap<Integer, Pair<Integer, List<Integer>>> getTable();
    void setTable(ConcurrentHashMap<Integer, Pair<Integer, List<Integer>>> map);
}
