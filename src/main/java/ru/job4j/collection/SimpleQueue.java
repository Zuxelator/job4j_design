package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize;
    private int outSize;


    public T poll() {
        while (inSize != 0) {
            out.push(in.pop());
            inSize--;
            outSize++;
        }
        T rsl = out.pop();
        outSize--;
        while (outSize != 0) {
            in.push(out.pop());
            inSize++;
            outSize--;
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}