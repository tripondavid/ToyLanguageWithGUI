package model.adts.procedure;

import model.adts.barrier.Pair;
import model.statements.StatementInterface;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MyProcedureTable implements MyProcedureTableInterface{
    private ConcurrentHashMap<String, Pair<List<String>, StatementInterface>> map;
    public MyProcedureTable() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public boolean exists(String key) {
        return map.containsKey(key);
    }

    @Override
    public synchronized void put(String key, Pair<List<String>, StatementInterface> value) {
        map.put(key, value);
    }

    @Override
    public Pair<List<String>, StatementInterface> get(String key) {
        return map.get(key);
    }

    @Override
    public synchronized void remove(String key) {
        map.remove(key);
    }

    @Override
    public ConcurrentHashMap<String, Pair<List<String>, StatementInterface>> getTable() {
        return map;
    }

    @Override
    public void setTable(ConcurrentHashMap<String, Pair<List<String>, StatementInterface>> table) {
        map = table;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
