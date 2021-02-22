package model.adt;

import model.PrgState;

import java.util.LinkedList;
import java.util.Queue;

public class MyList<T> implements MyIList<T> {
    Queue<T> list;

    public MyList() {
        this.list = new LinkedList<T>();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public PrgState pop() { return (PrgState) this.list.poll();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Queue<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        String s = "";
        for (T t : this.list)
            s += t.toString() + "\n";
        return s;
    }

}
