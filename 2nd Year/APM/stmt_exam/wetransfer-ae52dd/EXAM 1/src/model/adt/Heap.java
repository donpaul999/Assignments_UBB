package model.adt;

import exception.MyException;
import model.exp.VarExp;
import model.value.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.*;

/*
public class Heap<T> implements IHeap<T> {
    private Map<Integer, T> heap;
    private int nextAddr;

    public Heap(){
        heap = Collections.synchronizedMap( new HashMap<>() );
        nextAddr = 1;
    }


    @Override
    public int allocate(T value) {
        heap.put(nextAddr, value);
        nextAddr += 1;
        return (nextAddr - 1);
    }

    @Override
    public void update(int addr, T value) {
        heap.put(addr, value);
    }

    @Override
    public T get(int addr) {
        return heap.get(addr);
    }

    @Override
    public void deallocate(int addr) {
        heap.remove(addr);
    }

    @Override
    public boolean isAllocated(int addr) {
        return heap.containsKey(addr);
    }

    @Override
    public void setContent(Map<Integer, T> heap) {
        this.heap = heap;
    }

    @Override
    public Map<Integer, T> getContent() {
        return heap;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer i : heap.keySet()){
            stringBuilder.append(i + " -> " + heap.get(i) + "\n");
        }
        return stringBuilder.toString();
    }
}

 */


/*
it is a Hashmap (Address (int) -> Value (Value) )
it has a next free elm (how?)
*/


public class Heap<T1,T2> implements IHeap<T1,T2> {
    protected HashMap<T1, T2> heap;

    public Heap() {
        this.heap = new HashMap<T1, T2>();
    }

    @Override
    public HashMap<T1, T2> getContent() {
        return this.heap;
    }

    @Override
    public void setContent(Map<T1, T2> content) {
        Map<T1, T2> heap1 = this.heap;
        heap1 = content;
        this.heap = new HashMap<T1, T2>(heap1);
    }

    @Override
    public void put(T1 address, T2 content) {
        if (getContentFromAddress(address) == null)
            this.heap.put(address, content);
        else
            throw new MyException("This address isn't free for the location!");
    }

    @Override
    public T2 getContentFromAddress(T1 address) {
        return this.heap.get(address);
    }

    public int getValidAddress() {
        int i = 0;
        do {
            i++;
        } while (this.heap.containsKey(i));
        return i;
    }

    @Override
    public void update(T1 address, T2 content) {
        this.heap.put(address, content);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<T1, T2> h : this.heap.entrySet()) {
            s.append(h.getKey()).append(" -> ").append(h.getValue().toString()).append("\n");
        }
        return s.toString();
    }

    @Override
    public Set entrySet() {
        return this.heap.entrySet();
    }

    @Override
    public Set<T1> getAddresses()
    {
        return this.heap.keySet();
    }

    @Override
    public Map<T1, T2> getDictionary() {
        return heap;
    }


}


    /*

    private HashMap<Integer,IValue> heap;
    private int freeAddress = 1;

    public Heap() {
        this.heap = new HashMap<>();
    }

    @Override
    public boolean isDefined(int id) {
        return this.heap.containsKey(id);
    }

    @Override
    public IValue getValue(int id) throws MyException {
        if (this.isDefined(id))
            return this.heap.get(id);
        else
            throw new MyException("invalid address");
    }

    @Override
    public Map<Integer, IValue> getHeap() {
        return heap;
    }

    @Override
    public void update(Integer e1, IValue e2) throws MyException {
        if (this.freeAddress<=e1){
            throw new MyException("invalid address");
        }
        this.heap.put(e1,e2);
    }

    @Override
    public void add(IValue e) {
        this.heap.put(this.freeAddress,e);
        this.freeAddress++;
    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public IValue remove(int id) throws MyException {
        if (this.isDefined(id))
            return this.heap.remove(id);
        else
            throw new MyException("invalid address");
    }

    @Override
    public int getFreeAddress() {
        return this.freeAddress;
    }

    @Override
    public void setFreeAddress(int newFreeAddress) {
        this.freeAddress=newFreeAddress;
    }

    @Override
    public Set<Map.Entry<Integer, IValue>> entrySet() {
        return this.heap.entrySet();
    }

    @Override
    public HashMap<Integer, IValue> getContent() {
        return this.heap;
    }

    @Override
    public void setContent(Map<Integer, IValue> content) {
        Map<Integer, IValue> heap1 = this.heap;
        heap1 = content;
        this.heap = new HashMap<Integer, IValue>(heap1);
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<Integer, IValue> h : this.heap.entrySet()) {
            s += "" + h.getKey() + "->" + h.getValue() + "\n";
        }
        return s;
    }

    @Override
    public IValue lookup(Integer key) {
        if (this.heap.get(key)!=null){
            return this.heap.get(key);
        }
        throw new MyException("Doesn't exist!");
    }

    @Override
    public boolean getContentFromAddress(T1 address) {
        return false;
    }

    @Override
    public Map<T1, T2> getDictionary() {
        return (Map<T1, T2>) heap;
    }
}

     */
