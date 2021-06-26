package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(2131, "1212211");
        File tmp = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp))) {
            oos.writeObject(contact);
            Contact contact1 = (Contact) ois.readObject();
            System.out.println(contact1);
        }
    }
}