package com.kuntsevich.task6.service;

import com.kuntsevich.task6.dao.BookListDao;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookNotFoundException;
import com.kuntsevich.task6.factory.DaoFactory;

import java.util.List;
import java.util.Optional;

public class BookService {

    public boolean addBook(Book book) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.addBook(book);
    }

    public void removeBook(Book book) throws BookNotFoundException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        bookListDao.removeBook(book);
    }

    public Optional<Book> findById(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.findById(id);
    }

    public List<Book> findByTitle(String title) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.findByTitle(title);
    }

    public List<Book> findByGenres(List<String> genres) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.findByGenres(genres);
    }

    public List<Book> findByPageCount(int pageCount) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.findByPageCount(pageCount);
    }

    public List<Book> findByAuthors(List<String> authors) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.findByAuthors(authors);
    }

    public List<Book> sortById() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.sortById();
    }

    public List<Book> sortByTitle() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.sortByTitle();
    }

    public List<Book> sortByGenres() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.sortByGenres();
    }

    public List<Book> sortByPageCount() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.sortByPageCount();
    }

    public List<Book> sortByAuthors() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        return bookListDao.sortByAuthors();
    }
}
