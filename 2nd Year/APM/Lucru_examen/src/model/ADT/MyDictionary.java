package model.ADT;

import model.exceptions.ADTException;
import model.exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements IMyDictionary<K,V> {
    private Map<K, V> map;

    public MyDictionary() {
        map = new HashMap<>();
    }
    @Override
    public void add(K id, V value) throws ADTException {
        if (map.containsKey(id)) {
            throw new ADTException("Element already exists");
        }
        map.put(id, value);
    }

    @Override
    public void setContent(Map<K, V> content) {
        map = content;
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public V lookup(K key) {
        return map.get(key);
    }

    @Override
    public void update(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void remove(K key) throws ADTException {
        if (!map.containsKey(key)) {
            throw new ADTException("Key not exists in the map");
        }
        map.remove(key);
    }

    @Override
    public boolean isDefined(K key) {
        return map.containsKey(key);
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (Map.Entry<K, V> el : map.entrySet()) {
            content.append(el.getKey()).append("-").append(el.getValue()).append(" ");
        }
        return content.toString();
    }



    @Override
    public IMyDictionary<K, V> deepCopy() {
        HashMap<K, V> newMap = new HashMap<K, V>(map);
        MyDictionary<K, V> newDictionary = new MyDictionary<K, V>();
        newDictionary.setContent(newMap);
        return newDictionary;
    }

}
