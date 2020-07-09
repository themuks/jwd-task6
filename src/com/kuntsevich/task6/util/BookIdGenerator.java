package com.kuntsevich.task6.util;

public class BookIdGenerator {

    private static volatile BookIdGenerator instance;

    private static int currentId = 0;

    private BookIdGenerator() {
    }

    public static BookIdGenerator getInstance() {
        if (instance == null) {
            synchronized (BookIdGenerator.class) {
                if (instance == null) {
                    instance = new BookIdGenerator();
                }
            }
        }
        return instance;
    }

    public int generateId() {
        return currentId++;
    }
}