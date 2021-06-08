package ru.job4j.collection.tree;

import org.junit.Test;
import ru.job4j.collection.tree.SimpleTree;
import ru.job4j.collection.tree.Tree;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleTreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildAlreadyExists() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertFalse(tree.add(1, 2));
    }

    @Test
    public void whenIsBinary() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 2);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenIsNotBinary() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        assertFalse(tree.isBinary());
    }
}