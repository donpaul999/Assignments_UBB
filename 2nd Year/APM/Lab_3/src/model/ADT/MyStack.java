package model.ADT;

import exceptions.MyException;

import java.util.Stack;

public class MyStack<T> implements IMyStack<T> {
    private Stack<T> stk;

    @Override
    public T pop() throws MyException {
        if (stk.size() == 0) {
            throw new MyException("Stack is empty");
        }
        T top = (T) stk.pop();
        return top;
    }

    @Override
    public void push(T value) {
        stk.push(value);
    }

    @Override
    public boolean isEmpty() {
        return stk.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T el: stk) {
            str.append(el).append(" ");
        }
        return str.toString();
    }
}
