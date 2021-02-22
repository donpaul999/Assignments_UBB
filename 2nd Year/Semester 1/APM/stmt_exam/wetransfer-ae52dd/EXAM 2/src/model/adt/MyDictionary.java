package model.adt;

import exception.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K,V> implements MyIDictionary<K,V> {

    HashMap<K,V> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<K,V>();
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<K, V> h : this.dictionary.entrySet()) {
            s += "Key: " + h.getKey() + ", Value:" + h.getValue() + "\n";
        }
        return s;
    }

    @Override
    public void add(K key, V value) {
        this.dictionary.put(key, value);
    }

    @Override
    public void update(K key, V value) {
        this.dictionary.put(key,value);
    }

    @Override
    public V lookup(K key) throws MyException {
        if (this.dictionary.get(key)!=null){
            return this.dictionary.get(key);
        }
        throw new MyException("Doesn't exist!");
    }

    @Override
    public boolean isDefined(K key) {
        return this.dictionary.containsKey(key);
    }

    @Override
    public void delete(K v1) {
        V val = lookup(v1);
        this.dictionary.remove(v1,val);
    }

    @Override
    public HashMap<K, V> getDictionary() {
        return this.dictionary;
    }

    @Override
    public MyIDictionary<K, V> cloneDictionary() {
        MyIDictionary<K, V> dic = new MyDictionary<K, V>();
        for (K k : this.dictionary.keySet()) {
            dic.add(k, this.dictionary.get(k));
        }
        return dic;
    }
}
