package ru.job4j.collection.exam;

import java.util.*;

public class Mail {

    private static boolean content(Set<String> one, Set<String> two) {
        boolean rsl = false;
        for (String str : one) {
            if (two.contains(str)) {
                return true;
            }
        }
        return rsl;
    }

    public static Map<User, Set<String>> mergeUsers(Map<User, Set<String>> map) {
        HashMap<User, Set<String>> rsl = new HashMap<>();
        Iterator<Map.Entry<User, Set<String>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<User, Set<String>> entry = it.next();
            for (Map.Entry<User, Set<String>> innerEntry : map.entrySet()) {
                if (content(entry.getValue(), innerEntry.getValue())) {
                entry.getValue().addAll(innerEntry.getValue());
                rsl.put(entry.getKey(), entry.getValue());
                }
            }
        }
        HashMap<Set<String>, User> revertMap = new HashMap<>();
        for (Map.Entry<User, Set<String>> revertEntry : rsl.entrySet()) {
            revertMap.put(revertEntry.getValue(), revertEntry.getKey());
        }
        HashMap<User, Set<String>> finalMap = new HashMap<>();
        for (Map.Entry<Set<String>, User> finalEntry : revertMap.entrySet()) {
            finalMap.put(finalEntry.getValue(), finalEntry.getKey());
        }
        return finalMap;
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
