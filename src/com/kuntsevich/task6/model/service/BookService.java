package com.kuntsevich.task6.model.service;

import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public boolean addBook(String title, String genres, String pageCount, String authors) throws BookCreationException;

    public void removeBook(String title, String genres, String pageCount, String authors) throws BookNotFoundException, BookCreationException;

    public Optional<Book> findById(String id);

    public List<Book> findByTitle(String title);

    public List<Book> findByGenres(List<String> genres);

    public List<Book> findByPageCount(String pageCount);

    public List<Book> findByAuthors(List<String> authors);

    public List<Book> sortById();

    public List<Book> sortByTitle();

    public List<Book> sortByGenres();

    public List<Book> sortByPageCount();

    public List<Book> sortByAuthors();
}
