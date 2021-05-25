package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    public final int[] arr;
    private int point = 0;

    public EvenNumbersIterator(int[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        for (int i = point; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                point = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw  new NoSuchElementException();
        }
        return arr[point++];
    }
}
