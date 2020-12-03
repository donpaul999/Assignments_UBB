package model.ADT;

import model.exceptions.ADTException;
import model.exceptions.MyException;

public interface IMyStack<T> {
    public T pop() throws ADTException;
    public void push(T value);

    boolean isEmpty();
}
