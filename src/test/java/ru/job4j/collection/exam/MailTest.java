package ru.job4j.collection.exam;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MailTest {

    @Test
    public void whenSizeIs2() {
        Map<Mail.User, Set<String>> map = new HashMap<>();
        map.put(new Mail.User("user1"), new HashSet<String>(List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        map.put(new Mail.User("user2"), new HashSet<String>(List.of("foo@gmail.com", "ups@pisem.net")));
        map.put(new Mail.User("user3"), new HashSet<String>(List.of("xyz@pisem.net", "vasya@pupkin.com")));
        map.put(new Mail.User("user4"), new HashSet<String>(List.of("ups@pisem.net", "aaa@bbb.ru")));
        map.put(new Mail.User("user5"), new HashSet<String>(List.of("xyz@pisem.net")));
        Iterator<Map.Entry<Mail.User, Set<String>>> iterator = Mail.mergeUsers(map).entrySet().iterator();

        assertThat(Mail.mergeUsers(map).size(), is(2));
        assertThat(iterator.next().getValue().containsAll(List.of("aaa@bbb.ru", "ups@pisem.net", "lol@mail.ru", "xxx@ya.ru", "foo@gmail.com")), is(true));
        assertThat(iterator.next().getValue().containsAll(List.of("xyz@pisem.net", "vasya@pupkin.com")), is(true));
    }
}