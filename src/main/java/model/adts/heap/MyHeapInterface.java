package model.adts.heap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface MyHeapInterface<T> {
    int allocate(T val);
    T deallocate(int address);
    T get(int address);
    boolean exists(int address);
    void put(int address, T val);
    ConcurrentHashMap<Integer, T> getHeap();
    void setHeap(ConcurrentHashMap<Integer, T> map);
}
