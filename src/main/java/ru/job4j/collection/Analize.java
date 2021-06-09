package ru.job4j.collection;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        ArrayList<User> deleted = new ArrayList<>(previous);
        deleted.removeAll(current);
        int delete = deleted.size();
        ArrayList<User> added = new ArrayList<>(current);
        added.removeAll(previous);
        int add = added.size();
        ArrayList<User> changedAndUnchanged = new ArrayList<>(current);
        changedAndUnchanged.retainAll(previous);
        Set<User> set = new HashSet<>(changedAndUnchanged);
        int change = 0;
        for (User user : previous) {
            if (!set.add(user)) {
                change++;
            }
        }
        return new Info(add, change, delete);
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
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
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
