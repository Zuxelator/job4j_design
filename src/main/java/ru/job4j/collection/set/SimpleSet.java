package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Optional;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    public boolean remove(T element) {
        int index = set.indexOf(element);
        if (index == -1) {
            return false;
        } else {
            set.remove(index);
        }
        return true;
    }

    @Override
    public boolean contains(T value) {
        return set.indexOf(value) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}