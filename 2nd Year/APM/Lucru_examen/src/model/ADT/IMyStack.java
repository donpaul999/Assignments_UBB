package model.ADT;

import model.exceptions.ADTException;
import model.exceptions.MyException;

import java.util.List;

public interface IMyStack<T> {
    public T pop() throws ADTException;
    public void push(T value) ;

    boolean isEmpty();

    public List<T> toList();
}
