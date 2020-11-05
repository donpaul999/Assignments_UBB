package model.ADT;

import model.exceptions.ADTException;
import model.exceptions.MyException;

import java.util.*;

public class MyList<T> implements IMyList<T> {
    private List<T> list;

    public MyList() {
        list = new ArrayList<>();
    }

    @Override
    public void add(T item) {
       list.add(item);
    }

    @Override
    public void remove(T item) throws ADTException {
        try {
            list.remove(item);
        }
        catch (NoSuchElementException e) {
            throw new ADTException("No such element " + item);
        }
    }

    @Override
    public T get(int index) throws ADTException {
        try {
            return list.get(index);
        }
        catch (IndexOutOfBoundsException e) {
            throw new ADTException("Index out of bounds");
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (T el: list) {
            content.append(el).append(" ");
        }
        return content.toString();
    }
}
