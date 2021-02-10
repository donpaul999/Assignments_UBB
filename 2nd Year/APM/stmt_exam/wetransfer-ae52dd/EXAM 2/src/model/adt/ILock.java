package model.adt;

import java.util.Map;

public interface ILock {

    int allocate(Integer val);

    boolean exist(int key);

    void put(int key, int value);

    Integer get(int key);

    boolean containsKey(int key);

    Map<Integer, Integer> getContent();

    void setContent(Map<Integer,Integer> content);

    void update(Integer address, int value);
}
