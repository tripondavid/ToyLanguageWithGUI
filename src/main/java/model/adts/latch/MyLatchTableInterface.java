package model.adts.latch;

import java.util.concurrent.ConcurrentHashMap;

public interface MyLatchTableInterface {
    public int allocate(int val);

    public int deallocate(int address);

    public int get(int address);

    public boolean exists(int address);

    public void put(int address, int val);

    public ConcurrentHashMap<Integer, Integer> getTable();

    public void setTable(ConcurrentHashMap<Integer, Integer> map);

}
