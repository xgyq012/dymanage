package com.base.springmvc;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @param <K>
 * @param <V>
 * @author Will WM. Zhang
 * @since 2017-01-28 01:18:56
 */
public class WapperMap<K, V> {

    private Map<K, V> innerMap;

    public Map<K, V> getInnerMap() {
        return innerMap;
    }

    public void setInnerMap(Map<K, V> innerMap) {
        this.innerMap = innerMap;
    }

    public int size() {
        return innerMap.size();
    }

    public boolean isEmpty() {
        return innerMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return innerMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return innerMap.containsValue(value);
    }

    public V get(Object key) {
        return innerMap.get(key);
    }

    public V put(K key, V value) {
        return innerMap.put(key, value);
    }

    public V remove(Object key) {
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        innerMap.putAll(m);
    }

    public void clear() {
        innerMap.clear();
    }

    public Set<K> keySet() {
        return innerMap.keySet();
    }

    public Collection<V> values() {
        return innerMap.values();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return innerMap.entrySet();
    }

    @Override
    public boolean equals(Object obj) {
        return innerMap.equals(obj);
    }

    @Override
    public int hashCode() {
        return innerMap.hashCode();
    }

    @Override
    public String toString() {
        return innerMap.toString();
    }
}
