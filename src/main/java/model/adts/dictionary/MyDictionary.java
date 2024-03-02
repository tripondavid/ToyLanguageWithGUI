package model.adts.dictionary;

import exception.MyException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyDictionary<K, V> implements MyDictionaryInterface<K, V> {
    private final ConcurrentHashMap<K, V> map;

    public MyDictionary() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void add(K key, V val) {
        map.put(key, val);
    }

    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public void update(K key, V val) {
        map.replace(key, val);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean existsKey(K key) {
        return map.get(key) != null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public MyDictionaryInterface<K, V> deepCopy() {
        MyDictionaryInterface<K, V> newDictionary = new MyDictionary<K, V>();

        for (K key : map.keySet()) {
            newDictionary.add(key, map.get(key));
        }

        return newDictionary;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
