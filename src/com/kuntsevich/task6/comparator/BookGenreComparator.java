package com.kuntsevich.task6.comparator;

import com.kuntsevich.task6.entity.Book;

import java.util.Comparator;

public class BookGenreComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getGenre().compareTo(o2.getGenre());
    }
}
