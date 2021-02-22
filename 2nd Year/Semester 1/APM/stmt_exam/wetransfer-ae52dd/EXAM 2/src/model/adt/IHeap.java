package model.adt;

import exception.MyException;
import model.value.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public interface IHeap<T1,T2> {
    void put(T1 address, T2 content);
    T2 getContentFromAddress(T1 address);
    int getValidAddress();
    void update(T1 address, T2 content);
    HashMap<T1, T2> getContent();
    void setContent(Map<T1, T2> content);
    Set entrySet();

    Set<T1> getAddresses();

    Map<T1, T2> getDictionary();
    /*
    public boolean isDefined(int id);
    public IValue getValue(int id) throws MyException;
    public Map<Integer,IValue> getHeap();
    public void update(Integer e1, IValue e2) throws MyException;
    public void add(IValue e);
    public int size();
    public String toString();
    public IValue remove(int id) throws MyException;
    public int getFreeAddress();
    public void setFreeAddress(int newFreeAddress);
    public Set<Map.Entry<Integer,IValue>> entrySet();
    public HashMap<Integer,IValue> getContent();
    public void setContent(Map<Integer,IValue> content);
    IValue lookup(Integer key);

    boolean getContentFromAddress(T1 address);

    Map<T1, T2> getDictionary();

     */
}

