package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;

    private int size;
    private int capacity;
    private int cursor;
    private int modCount;

    public SimpleArray() {
        container = new Object[10];
        capacity = 10;
    }

    public SimpleArray(int capacity) {
        container = new Object[capacity];
        this.capacity = capacity;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size == capacity) {
            grow();
        }
        container[size++] = model;
        modCount++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        container[index] = model;
        modCount++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        if (index == size) {
            container[size--] = null;
        } else {
            System.arraycopy(container, index + 1, container, index, size - 1 - index);
            size--;
        }
        modCount++;
    }

    public int size() {
        return size;
    }

    public int indexOf(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element.equals(container[i])) {
                index = i;
            }
        }
        return index;
    }

    private void grow() {
        capacity = (int) (container.length * 1.5);
        Object[] newContainer = new Object[capacity];
        System.arraycopy(container, 0, newContainer, 0, container.length);
        container = newContainer;
    }


    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[cursor++];
            }
        };
    }
}