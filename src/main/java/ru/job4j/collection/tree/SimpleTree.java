package ru.job4j.collection.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentNode = findBy(parent);
        if (parentNode.isPresent()) {
            Optional<Node<E>> childNode = findBy(child);
            if (childNode.isEmpty()) {
                parentNode.get().children.add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(x -> x.value.equals(value));
    }

    public boolean isBinary() {
        Optional<Node<E>> node = findByPredicate(x -> x.children.size() > 2);
        return node.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> node = data.poll();
            if (condition.test(node)) {
                rsl = Optional.of(node);
                break;
            }
            data.addAll(node.children);
        }
        return rsl;
    }
}
