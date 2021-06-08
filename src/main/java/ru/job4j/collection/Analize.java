package ru.job4j.collection;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        List<User> newCurrent = new ArrayList<>(current);
        int added;
        int changed = 0;
        int deleted;
        newCurrent.removeAll(previous); // добавленные + измененные
        for (User cur : newCurrent) {
            for (User prev : previous) {
                if (cur.id == prev.id) {
                    changed++;
                }
            }
        }
        added = newCurrent.size() - changed;
        previous.removeAll(current);
        deleted = previous.size() - changed;
        return new Info(added, changed, deleted);
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
