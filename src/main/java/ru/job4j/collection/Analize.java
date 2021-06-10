package ru.job4j.collection;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.id, user);
        }
        int change = 0;
        int unchanged = 0;
        for (User user : current) {
            if (map.keySet().contains(user.id)) {
                if (!map.get(user.id).name.equals(user.name)) {
                    change++;
                } else {
                    unchanged++;
                }
            }
        }
        for (User user : current) {
            previous.remove(user);
        }
        int deleted = previous.size() - change;
        int add = current.size() - unchanged - change;
        return new Info(add, change, deleted);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
            return id == user.id && name.equals(user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }

}
