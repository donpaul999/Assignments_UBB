package model.ADT;

import model.exceptions.ADTException;
import model.exceptions.MyException;

public interface IMyList<T> {
    void add(T item);
    void remove(T item) throws ADTException;
    int size();
    T get(int index) throws ADTException;

}
