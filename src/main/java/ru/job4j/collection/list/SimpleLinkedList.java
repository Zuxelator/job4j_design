package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    private Node<E> cursor;

    private int modCount;


    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> oldNode = last;
        Node<E> newNode = new Node<>(oldNode, value, null);
        last = newNode;
        if (oldNode == null) {
            first = newNode;
        } else {
            oldNode.next = newNode;
        }
        size++;
        cursor = first;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.element;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        cursor = first;
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                if (cursor == null) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                E rsl = cursor.element;
                cursor = cursor.next;
                return rsl;
            }
        };
    }
}