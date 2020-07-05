package com.kuntsevich.task6.service;

import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookNotFoundException;

import java.util.List;

public interface BookListDao {

    boolean addBook(Book book);
    void removeBook(Book book) throws BookNotFoundException;
    List<Book> findById(int id);
    List<Book> findByTitle(String title);
    List<Book> findByGenres(List<String> genres);
    List<Book> findByPageCount(int pageCount);
    List<Book> findByAuthorName(String authorName);
    List<Book> sortById();
    List<Book> sortByTitle();
    List<Book> sortByPageCount();
    List<Book> sortByAuthorName();
}
