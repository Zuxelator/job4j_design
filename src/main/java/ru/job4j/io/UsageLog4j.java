package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 25;
        byte g = 4;
        short a = 290;
        long l = 1200;
        char c = 'k';
        boolean bool = true;
        double d = 5.0;
        float f = 13f;
        LOG.debug("vars: int {}, byte {}, short {}, long {}, char {},"
                + " boolean {}, double {}, float {}", age, g, a, l, c, bool, d, f);

    }
}