package model.adt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLock implements ILock {

    AtomicInteger freeValue;
    Map<Integer,Integer> lockMap;

    public MyLock() {
        lockMap = new HashMap<>();
        freeValue=new AtomicInteger(0);
    }

    @Override
    public int allocate(Integer val) {
        lockMap.put(freeValue.incrementAndGet(),val);
        return freeValue.get();
    }

    @Override
    public synchronized boolean exist(int key) {
        return lockMap.containsKey(key);
    }

    @Override
    public synchronized void put(int key, int value) {
        lockMap.put(key,value);
    }

    @Override
    public synchronized Integer get(int key) {
        return lockMap.get(key);
    }

    @Override
    public synchronized boolean containsKey(int key) {
        return lockMap.containsKey(key);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return lockMap;
    }

    @Override
    public void setContent(Map<Integer, Integer> content) {
        lockMap=content;
    }

    @Override
    public void update(Integer address, int value) {
        lockMap.put(address,value);
    }
}
