package model.adts.procedure;

import model.adts.barrier.Pair;
import model.statements.StatementInterface;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface MyProcedureTableInterface {
    boolean exists(String key);
    void put(String key, Pair<List<String>, StatementInterface> value);
    Pair<List<String>, StatementInterface> get(String key);
    void remove(String key);
    ConcurrentHashMap<String, Pair<List<String>, StatementInterface>> getTable();
    void setTable(ConcurrentHashMap<String, Pair<List<String>, StatementInterface>> table);
}
