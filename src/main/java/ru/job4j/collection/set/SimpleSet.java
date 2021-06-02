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
        for (int i = 0; i < set.size(); i++) {
            Optional<T> optional = Optional.ofNullable(set.get(i));
            if (optional.isPresent()) {
                if (optional.get().equals(value)) {
                    return true;
                }
            } else if (value == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}