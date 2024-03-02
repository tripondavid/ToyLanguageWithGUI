package model.adts.stack;

import java.util.Stack;

public class MyStack<T> implements MyStackInterface<T> {
    private final Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T val) {
        stack.push(val);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public T top() {
        return stack.peek();
    }

    @Override
    public void clear() {
        this.stack.clear();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
