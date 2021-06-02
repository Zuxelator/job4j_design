package ru.job4j.collection.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAdd22() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(2));
        assertFalse(set.add(2));
    }

    @Test
    public void whenRemove() {
        SimpleSet<Integer> set = new SimpleSet<>();
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        set.remove(3);
        assertFalse(set.contains(3));
    }

}