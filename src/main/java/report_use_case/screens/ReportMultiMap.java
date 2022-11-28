package report_use_case.screens;

import java.util.*;

class MultiMap<K, V>
{
    private Map<K, Collection<V>> map = new HashMap<>();

    /**
     * Add the specified value with the specified key in this multimap.
     */
    public void put(K key, V value)
    {
        if (map.get(key) == null) {
            map.put(key, new ArrayList<V>());
        }

        map.get(key).add(value);
    }


    /**
     * Returns the Collection of values to which the specified key is mapped,
     * or null if this multimap contains no mapping for the key.
     */
    public Collection<V> get(Object key) {
        return map.get(key);
    }

    /**
     * Returns a set view of the keys contained in this multimap.
     */
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * Returns a Collection view of Collection of the values present in
     * this multimap.
     */
    public Collection<Collection<V>> values() {
        return map.values();
    }

    /**
     * Returns true if this multimap contains a mapping for the specified key.
     */
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /**
     * Returns the total number of key-value mappings in this multimap.
     */
    public int size()
    {
        int size = 0;
        for (Collection<V> value: map.values()) {
            size += value.size();
        }
        return size;
    }

    /**
     * Returns true if this multimap contains no key-value mappings.
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

}

