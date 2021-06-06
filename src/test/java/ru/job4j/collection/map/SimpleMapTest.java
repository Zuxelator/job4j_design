package ru.job4j.collection.map;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutThenGet() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "Один");
        String rsl = map.get("1");
        assertThat(rsl, is("Один"));
    }

    @Test
    public void whenPutPutThenGet() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "Один");
        map.put("1", "Один!!!");
        String rsl = map.get("1");
        assertThat(rsl, is("Один!!!"));
    }

    @Test
    public void whenGet() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("3", "Три");
        String rsl = map.get("3");
        assertThat(rsl, is("Три"));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("3", "Три");
        assertNull(map.get("4"));
    }

    @Test
    public void whenRemoveTrue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("3", "Три");
        assertTrue(map.remove("3"));
    }

    @Test
    public void whenRemoveFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("3", "Три");
        assertFalse(map.remove("4"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "Один");
        Iterator<String> it = map.iterator();
        map.put("2", "Два");
        it.next();
    }

}