package model.ADT;

import exceptions.MyException;

import java.util.Iterator;
import java.util.Map;

public class MyDictionary<K, V> implements IMyDictionary<K,V> {
    private Map<K, V> map;

    @Override
    public V lookup(K key) {
        return map.get(key);
    }

    @Override
    public void update(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void remove(K key) throws MyException {
        if (!map.containsKey(key)) {
            throw new MyException("Key not exists in the map");
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
        for (Map.Entry<K,V> el: map.entrySet()){
            content.append(el.getKey()).append("-").append(el.getValue()).append(" ");
        }
        return content.toString();
    }
}
