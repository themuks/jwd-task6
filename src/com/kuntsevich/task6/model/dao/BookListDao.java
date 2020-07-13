package com.kuntsevich.task6.model.dao;

import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {

    boolean addBook(Book book);

    void removeBook(Book book) throws BookNotFoundException;

    Optional<Book> findById(int id);

    List<Book> findByTitle(String title);

    List<Book> findByGenres(List<String> genres);

    List<Book> findByPageCount(int pageCount);

    List<Book> findByAuthors(List<String> authors);

    List<Book> sortById();

    List<Book> sortByTitle();

    List<Book> sortByGenres();

    List<Book> sortByPageCount();

    List<Book> sortByAuthors();
}
