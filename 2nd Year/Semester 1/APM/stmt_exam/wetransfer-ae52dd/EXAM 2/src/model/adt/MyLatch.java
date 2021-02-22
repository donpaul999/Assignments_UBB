package model.adt;

import java.util.Map;

public class MyLatch implements ILatch {

    MyIDictionary<Integer,Integer> latchMap;

    public MyLatch() {
        latchMap = new MyDictionary<>();
    }

    @Override
    public synchronized boolean exist(int key) {
        return latchMap.lookup(key)!=null;
    }

    @Override
    public synchronized void put(int key, int value) {
        latchMap.add(key,value);
    }

    @Override
    public synchronized Integer get(int key) {
        return latchMap.lookup(key);
    }

    @Override
    public synchronized boolean containsKey(int key) {
        return latchMap.isDefined(key);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return this.latchMap.getDictionary();
    }
}
