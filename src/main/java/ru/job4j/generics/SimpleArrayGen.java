package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayGen<T> implements Iterable<T> {

    private Object[] array;

    private int capacity = 0;
    private int cursor = 0;

    public SimpleArrayGen(int size) {
        this.array =  new Object[size];
    }

    public void add(T model) {
        Objects.checkIndex(capacity, array.length);
        array[capacity++] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, capacity);
        return (T) array[index];
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, capacity);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, capacity);
        System.arraycopy(array, index + 1, array, index, capacity - index - 1);
        array[capacity - 1] = null;
        capacity--;
    }

   @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return capacity > cursor;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[cursor++];
            }
        };
    }
}
