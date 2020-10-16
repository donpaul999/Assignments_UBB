package model.ADT;

import exceptions.MyException;

public interface IMyStack<T> {
    public T pop() throws MyException;
    public void push(T value);
}
