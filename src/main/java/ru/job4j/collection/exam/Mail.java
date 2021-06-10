package ru.job4j.collection.exam;

import java.util.*;

public class Mail {

    public static void main(String[] args) {
        Map<User, List<String>> map = new HashMap<>();
        map.put(new User("user1"), new ArrayList<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        map.put(new User("user2"), new ArrayList<>(Arrays.asList("foo@gmail.com", "ups@pisem.net")));
        map.put(new User("user3"), new ArrayList<>(Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        map.put(new User("user4"), new ArrayList<>(Arrays.asList("ups@pisem.net", "aaa@bbb.ru")));
        map.put(new User("user5"), new ArrayList<>(Arrays.asList("xyz@pisem.net")));

        Map<User, List<String>> rsl = mergeUsers(map);


    }


    public static Map<User, List<String>> mergeUsers(Map<User, List<String>> map) {
        HashSet<String> set = new HashSet<>();
        for (Map.Entry<User, List<String>> entry: map.entrySet()) {
            set.addAll(entry.getValue());
        }
        System.out.println(set);
        //rsl - хешмапа где ключ это емейл а значение список юзеров

        Map<User, Set<String>> result = new HashMap<>();

        for (String email : set) {
            List<User> users = new ArrayList<>();
            Set<String> emails = new HashSet<>();
            for (Map.Entry<User, List<String>> entry : map.entrySet()) {
                if (entry.getValue().contains(email)) {
                    users.add(entry.getKey());
                    emails.addAll(entry.getValue());
                }
            }
            result.put(users.get(0), emails);
            users = new ArrayList<>();
            emails = new HashSet<>();
        }

        System.out.println(result);





        HashMap<String, List<User>> emailKeySet = new HashMap<>();
        //set хранит всебе уникальные емейлы
        //для каждого уникального емейла
        for (String string : set) {
            List<User> list = new ArrayList<>();
            for (Map.Entry<User, List<String>> entry : map.entrySet()) {
                if (entry.getValue().contains(string)) {
                    list.add(entry.getKey());
                }
            }
            emailKeySet.put(string, list);
            list = new ArrayList<>();
        }
        System.out.println(map);
        System.out.println(emailKeySet);

        /*Map<User, Set<String>> result = new HashMap<>();

        for (Map.Entry<String, List<User>> entry : emailKeySet.entrySet()) {
            User user = entry.getValue().get(0); //ключ для финальной коллекции
            Set<String> finalSetOfEmails = new HashSet<>();
            //список юзеров для каждого емейла
            List<User> users = entry.getValue();
            for (User user1 : users) {
                finalSetOfEmails.addAll(map.get(user1));
            }
            result.put(user, finalSetOfEmails);
            for (int i = 1; i < entry.getValue().size(); i++) {
                entry.getValue().get(i);
            }
            *//*for (Map.Entry<User, List<String>> entry1 : map.entrySet()) {

            }*//*

        }
        System.out.println(result);


*/

        return null;
    }

    public static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return name.equals(user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + '}';
        }
    }

}
