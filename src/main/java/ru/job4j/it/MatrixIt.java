package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }


    @Override
    public boolean hasNext() {
        for (int i = column; i < data.length; i++) {
            for (int j = row; j < data[i].length; j++) {
                column = i;
                row = j;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = data[column][row];
        if (data[column].length - 1 == row) {
            column++;
            row = 0;
        } else {
            row++;
        }
        return rsl;
    }
}