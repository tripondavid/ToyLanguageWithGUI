package model.adts.stack;

public interface MyStackInterface<T> {
    T pop();
    void push(T val);
    boolean isEmpty();
    int size();
    T top();
    void clear();
}
