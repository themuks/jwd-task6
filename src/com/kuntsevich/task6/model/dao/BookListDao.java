package com.kuntsevich.task6.model.dao;

import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookNotFoundException;
import com.kuntsevich.task6.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {

    boolean addBook(Book book) throws DaoException;

    void removeBook(Book book) throws DaoException;

    Book findById(int id) throws DaoException;

    List<Book> findByTitle(String title) throws DaoException;

    List<Book> findByGenres(List<String> genres) throws DaoException;

    List<Book> findByPageCount(int pageCount) throws DaoException;

    List<Book> findByAuthors(List<String> authors) throws DaoException;

    List<Book> sortById() throws DaoException;

    List<Book> sortByTitle() throws DaoException;

    List<Book> sortByGenres() throws DaoException;

    List<Book> sortByPageCount() throws DaoException;

    List<Book> sortByAuthors() throws DaoException;
}
