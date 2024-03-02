package model.adts.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyList<T> implements MyListInterface<T> {
    private final List<T> list;

    public MyList() {
        list = Collections.synchronizedList(new ArrayList<T>());
    }

    @Override
    public void add(T val) {
        list.add(val);
    }

    @Override
    public T remove(int pos) {
        return list.remove(pos);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T get(int pos) {
        return list.get(pos);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
