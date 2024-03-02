package model.adts.heap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyHeap<T> implements MyHeapInterface<T> {
    private ConcurrentHashMap<Integer, T> map;
    private final AtomicInteger freeLocation;

    public MyHeap() {
        map = new ConcurrentHashMap<>();
        freeLocation = new AtomicInteger(0);
    }

    @Override
    public int allocate(T val) {
        map.put(freeLocation.incrementAndGet(), val);
        return freeLocation.get();
    }

    @Override
    public T deallocate(int address) {
        return map.remove(address);
    }

    @Override
    public T get(int address) {
        return map.get(address);
    }

    @Override
    public boolean exists(int address) {
        return map.containsKey(address);
    }

    @Override
    public void put(int address, T val) {
        map.put(address, val);
    }

    @Override
    public ConcurrentHashMap<Integer, T> getHeap() {
        return map;
    }

    @Override
    public void setHeap(ConcurrentHashMap<Integer, T> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
