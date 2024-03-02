package model.adts.latch;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLatchTable implements MyLatchTableInterface{
    private ConcurrentHashMap<Integer, Integer> map;
    private final AtomicInteger freeLocation;

    public MyLatchTable() {
        map = new ConcurrentHashMap<>();
        freeLocation = new AtomicInteger(0);
    }
    @Override
    public int allocate(int val) {
        map.put(freeLocation.incrementAndGet(), val);
        return freeLocation.get();
    }

    @Override
    public int deallocate(int address) {
        return map.remove(address);
    }

    @Override
    public int get(int address) {
        return map.get(address);
    }

    @Override
    public boolean exists(int address) {
        return map.containsKey(address);
    }

    @Override
    public void put(int address, int val) {
        map.put(address, val);
    }

    @Override
    public ConcurrentHashMap<Integer, Integer> getTable() {
        return map;
    }

    @Override
    public void setTable(ConcurrentHashMap<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
