package model;

import model.value.IValue;
import model.value.RefValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GarbageCollector {


    //Stream: a sequence of elements from a source that supports aggregate operations.


    public static Map<Integer, IValue> unsafe_garbage_collector(List<Integer> symbols_tables_addresses, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(entry -> symbols_tables_addresses.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Integer, IValue> garbage_collector(List<Integer> symbols_tables_addresses, List<Integer> heap_addresses, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(entry -> symbols_tables_addresses.contains(entry.getKey()) || heap_addresses.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> get_addresses_value_collection(Collection<IValue> values_collection) {
        return values_collection.stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> ((RefValue)value).getAddr())
                .collect(Collectors.toList());
    }


}
