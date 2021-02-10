package model.adt;

import exception.MyException;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import model.value.StringValue;

public class FileTable<StringValue,BufferedReader> implements MyIDictionary<StringValue,BufferedReader> {

    HashMap <StringValue, BufferedReader> fileTable;

    public FileTable() {
        this.fileTable = new HashMap<StringValue,BufferedReader>();
    }

    @Override
    public void add(StringValue key, BufferedReader value) {
        this.fileTable.put(key,value);
    }

    @Override
    public void update(StringValue key, BufferedReader value) {
        this.fileTable.put(key, value);
    }

    @Override
    public BufferedReader lookup(StringValue key) {
        if (this.fileTable.get(key)!=null){
            return this.fileTable.get(key);
        }
        throw new MyException("Doesn't exist!");
    }

    @Override
    public boolean isDefined(StringValue key) {
        return this.fileTable.containsKey(key);
    }

    @Override
    public void delete(StringValue v1) {
        BufferedReader val = lookup(v1);
        this.fileTable.remove(v1,val);
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<StringValue, BufferedReader> h : this.fileTable.entrySet()) {
            s += "FileName: " + h.getKey() + ", FileDescriptor:" + h.getValue().toString() + "\n";
        }
        return s;
    }

    @Override
    public HashMap<StringValue, BufferedReader> getDictionary() {
        return fileTable;
    }

    @Override
    public MyIDictionary<StringValue, BufferedReader> cloneDictionary() {
        MyIDictionary<StringValue, BufferedReader> dic = new MyDictionary<StringValue, BufferedReader>();
        for (StringValue k : this.fileTable.keySet()) {
            dic.add(k, this.fileTable.get(k));
        }
        return dic;
    }

}


