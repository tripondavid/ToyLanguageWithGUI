package model.adts.lock;

import exception.MyException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLockTable implements MyLockTableInterface {
    private ConcurrentHashMap<Integer, Integer> map;
    private final AtomicInteger freeLocation;

    public MyLockTable() {
        map = new ConcurrentHashMap<>();
        freeLocation = new AtomicInteger(0);
    }

    public int allocate(int val) {
        map.put(freeLocation.incrementAndGet(), val);
        return freeLocation.get();
    }

    public Integer deallocate(int address) {
        return map.remove(address);
    }

    public Integer get(int address) {
        return map.get(address);
    }

    public boolean exists(int address) {
        return map.containsKey(address);
    }

    public ConcurrentHashMap<Integer, Integer> getLockTable() {
        return map;
    }

    public synchronized void update(int key, int val) throws MyException {
        if (map.containsKey(key)) {
            map.replace(key, val);
        } else
            throw new MyException("Lock Table does not contain key");
    }
}
