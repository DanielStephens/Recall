package com.apakgroup.recall.bimap;

import java.util.ArrayList;
import java.util.List;

public class InstanceBiMap<V> {

    private final List<V> keys;

    private final List<V> values;

    public InstanceBiMap() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public InstanceBiMap(final InstanceBiMap<V> proxyMap) {
        this.keys = new ArrayList<>(proxyMap.keys);
        this.values = new ArrayList<>(proxyMap.values);
    }

    private int indexOfKey(final V key) {

        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) == key) {
                return i;
            }
        }

        return -1;
    }

    public void put(final V key, final V value) {
        int index = indexOfKey(key);

        if (index == -1) {
            keys.add(key);
            values.add(value);
            return;
        }

        keys.set(index, key);
        values.set(index, value);
    }

    public boolean containsKey(final V key) {
        return indexOfKey(key) != -1;
    }

    public V get(final V key) {
        int index = indexOfKey(key);

        if (index == -1) {
            return null;
        }

        return values.get(index);
    }

}

