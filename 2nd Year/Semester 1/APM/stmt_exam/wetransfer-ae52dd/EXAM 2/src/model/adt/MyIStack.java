package model.adt;

import model.statement.IStmt;

import java.util.Stack;

public interface MyIStack<T> {
    T pop();
    void push(T elem);
    boolean isEmpty();
    String toString();

    Stack<T> getStack();
}
