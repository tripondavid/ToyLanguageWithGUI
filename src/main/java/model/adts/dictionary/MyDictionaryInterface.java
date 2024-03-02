package model.adts.dictionary;

import java.util.Collection;
import java.util.Map;

public interface MyDictionaryInterface<K, V> {
    void add(K key, V val);
    V remove(K key);
    void update(K key, V val);
    V get(K key);
    boolean isEmpty();
    boolean existsKey(K key);
    void clear();
    Collection<V> values();
    Map<K, V> getContent();
    MyDictionaryInterface<K, V> deepCopy();
}
