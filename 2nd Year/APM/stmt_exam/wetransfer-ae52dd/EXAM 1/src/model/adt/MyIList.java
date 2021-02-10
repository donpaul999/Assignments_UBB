package model.adt;

import model.PrgState;

import java.util.Queue;

public interface MyIList<T> {
    void add(T elem);
    PrgState pop();
    String toString();

    int size();
    boolean isEmpty();

    Queue<T> getList();
}
