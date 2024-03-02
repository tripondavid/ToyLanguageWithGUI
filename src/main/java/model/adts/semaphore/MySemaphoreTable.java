package model.adts.semaphore;

import model.adts.barrier.Pair;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MySemaphoreTable implements MySemaphoreTableInterface{
    private ConcurrentHashMap<Integer, Pair<Integer, List<Integer>>> map;
    private final AtomicInteger freeLocation;

    public MySemaphoreTable() {
        map = new ConcurrentHashMap<>();
        freeLocation = new AtomicInteger(0);
    }

    @Override
    public int allocate(Pair<Integer, List<Integer>> val) {
        map.put(freeLocation.incrementAndGet(), val);
        return freeLocation.get();
    }

    @Override
    public Pair<Integer, List<Integer>> deallocate(int address) {
        return map.remove(address);
    }

    @Override
    public Pair<Integer, List<Integer>> get(int address) {
        return map.get(address);
    }

    @Override
    public boolean exists(int address) {
        return map.containsKey(address);
    }

    @Override
    public void put(int address, Pair<Integer, List<Integer>> val) {
        map.put(address, val);
    }

    @Override
    public ConcurrentHashMap<Integer, Pair<Integer, List<Integer>>> getTable() {
        return map;
    }

    @Override
    public void setTable(ConcurrentHashMap<Integer, Pair<Integer, List<Integer>>> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
