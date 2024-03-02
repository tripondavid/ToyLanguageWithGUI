package model.adts.lock;

import exception.MyException;

import java.util.concurrent.ConcurrentHashMap;

public interface MyLockTableInterface {
    public int allocate(int val);
    public Integer get(int address);
    public Integer deallocate(int address);
    public boolean exists(int address);
    public ConcurrentHashMap<Integer, Integer> getLockTable();
    public void update(int key, int val) throws MyException;
}
