package ru.job4j.collection;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.hamcrest.Matchers.is;

public class AnalizeTest {

    @Test
    public void whenAdd2Then2() {
        Analize analize = new Analize();
        ArrayList<Analize.User> previous = new ArrayList<>();

        ArrayList<Analize.User> current = new ArrayList<>(
                Arrays.asList(
                        new Analize.User(1, "Вася"),
                        new Analize.User(2, "Петя")
                )
        );
        Assert.assertThat(analize.diff(previous, current).added, is(2));
        Assert.assertThat(analize.diff(previous, current).deleted, is(0));
        Assert.assertThat(analize.diff(previous, current).changed, is(0));
    }

    @Test
    public void whenAddChangeDelete() {
        Analize analize = new Analize();
        ArrayList<Analize.User> previous = new ArrayList<>(
                Arrays.asList(
                        new Analize.User(1, "Вася"),
                        new Analize.User(2, "Петя")
                )
        );

        ArrayList<Analize.User> current = new ArrayList<>(
                Arrays.asList(
                        new Analize.User(1, "Маша"),
                        new Analize.User(3, "Вова")
                )
        );
        Assert.assertThat(analize.diff(previous, current).added, is(1));
        Assert.assertThat(analize.diff(previous, current).changed, is(1));
        Assert.assertThat(analize.diff(previous, current).deleted, is(1));
    }

    @Test
    public void whenDeleteAll() {
        Analize analize = new Analize();
        ArrayList<Analize.User> previous = new ArrayList<>(
                Arrays.asList(
                        new Analize.User(1, "Вася"),
                        new Analize.User(2, "Петя")
                )
        );
        ArrayList<Analize.User> current = new ArrayList<>();
        Assert.assertThat(analize.diff(previous, current).added, is(0));
        Assert.assertThat(analize.diff(previous, current).changed, is(0));
        Assert.assertThat(analize.diff(previous, current).deleted, is(2));
    }

    @Test
    public void whenAddAll() {
        Analize analize = new Analize();
        ArrayList<Analize.User> previous = new ArrayList<>();
        ArrayList<Analize.User> current = new ArrayList<>(
                Arrays.asList(
                new Analize.User(1, "Вася"),
                new Analize.User(2, "Петя")
                )
        );
        Assert.assertThat(analize.diff(previous, current).added, is(2));
        Assert.assertThat(analize.diff(previous, current).changed, is(0));
        Assert.assertThat(analize.diff(previous, current).deleted, is(0));
    }

    @Test
    public void whenAdd3Change2Delete1() {
        Analize analize = new Analize();
        ArrayList<Analize.User> previous = new ArrayList<>(
                Arrays.asList(
                        new Analize.User(1, "Вася"),
                        new Analize.User(2, "Петя"),
                        new Analize.User(3, "Гоша"),
                        new Analize.User(4, "Ваня")
                )
        );

        ArrayList<Analize.User> current = new ArrayList<>(
                Arrays.asList(
                        new Analize.User(1, "Маша"),
                        new Analize.User(2, "Вова"),
                        new Analize.User(4, "Ваня"),
                        new Analize.User(5, "Наташа"),
                        new Analize.User(6, "Марина"),
                        new Analize.User(7, "Оля")
                )
        );
        Assert.assertThat(analize.diff(previous, current).added, is(3));
        Assert.assertThat(analize.diff(previous, current).changed, is(2));
        Assert.assertThat(analize.diff(previous, current).deleted, is(1));
    }


}