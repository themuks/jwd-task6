package com.kuntsevich.task6.model.service;

import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.ServiceException;

import java.util.List;

public interface BookService {

    void addBook(String title, String genres, String pageCount, String authors) throws ServiceException;

    void removeBook(String title, String genres, String pageCount, String authors) throws ServiceException;

    Book findById(String id) throws ServiceException;

    List<Book> findByTitle(String title) throws ServiceException;

    List<Book> findByGenres(List<String> genres) throws ServiceException;

    List<Book> findByPageCount(String pageCount) throws ServiceException;

    List<Book> findByAuthors(List<String> authors) throws ServiceException;

    List<Book> sortById() throws ServiceException;

    List<Book> sortByTitle() throws ServiceException;

    List<Book> sortByGenres() throws ServiceException;

    List<Book> sortByPageCount() throws ServiceException;

    List<Book> sortByAuthors() throws ServiceException;
}
