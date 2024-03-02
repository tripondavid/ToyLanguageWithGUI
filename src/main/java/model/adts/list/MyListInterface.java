package model.adts.list;

import java.util.List;

public interface MyListInterface<T> {
    void add(T val);
    T remove(int pos);
    boolean isEmpty();
    int size();
    T get(int pos);
    void clear();
    List<T> getList();
}
