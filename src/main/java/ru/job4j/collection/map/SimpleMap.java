package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = table[index] == null;
        table[index] = new MapEntry<>(key, value);
        if (rsl) {
            count++;
        }
        modCount++;
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        int newCapacity = (int) (capacity * 1.5);
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        capacity = newCapacity;
        for (MapEntry entry : table) {
            if (entry != null) {
                K key = (K) entry.key;
                int index = indexFor(hash(key.hashCode()));
                newTable[index] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        V rsl = null;
        if (table[index] != null && table[index].key.equals(key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = table[index] != null;
        if (rsl && table[index].key.equals(key)) {
            table[index] = null;
            count--;
        }
        modCount++;
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;

        return new Iterator<K>() {
            int cursor;
            int elementsFound;
            @Override
            public boolean hasNext() {
                return count > elementsFound;
            }

            @Override
            public K next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[cursor] == null) {
                    cursor++;
                }
                K rsl = (K) table[cursor++].key;
                elementsFound++;
                return rsl;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}