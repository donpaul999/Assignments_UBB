package model.adt;

import exception.MyException;

import java.util.HashMap;
import java.util.Map;

public interface MyIDictionary<K, V> {
    void add(K key, V value);
    void update(K key, V value);
    V lookup(K key);
    boolean isDefined(K key);
    String toString();

    void delete(K v1);


    Map<K, V> getDictionary();

    MyIDictionary<K,V> cloneDictionary();
}
