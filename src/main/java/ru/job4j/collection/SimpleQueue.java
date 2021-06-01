package ru.job4j.collection;

public class SimpleQueue<T> {
    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();
    private int inSize;
    private int outSize;


    public T poll() {
        /*while (inSize != 0) {
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
        return rsl;*/

        int iterationCount = inSize * 2 - 1;
        int newInSize = inSize - 1;
        T rsl = null;
        SimpleStack<T> temp;
        while (iterationCount != 0) {
            out.push(in.pop());
            inSize--;
            if (inSize == 0) {
                rsl = out.pop();
                temp = in;
                in = out;
                out = temp;
            }
            iterationCount--;
        }
        temp = in;
        in = out;
        out = temp;
        inSize = newInSize;
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }

}