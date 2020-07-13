package com.kuntsevich.task6.model.service.impl;

import com.kuntsevich.task6.creator.BookCreator;
import com.kuntsevich.task6.model.dao.BookListDao;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.exception.BookNotFoundException;
import com.kuntsevich.task6.model.dao.factory.DaoFactory;
import com.kuntsevich.task6.model.service.BookService;
import com.kuntsevich.task6.validator.NumberValidator;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    public boolean addBook(String title, String genres, String pageCount, String authors) throws BookCreationException {
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.validateNumberString(pageCount)) {
            throw new BookCreationException("Invalid page count string value");
        }
        int pageCountValue = Integer.parseInt(pageCount);
        List<String> genresList = splitWords(genres);
        List<String> authorsList = splitWords(authors);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookCreator bookCreator = new BookCreator();
        Book book = bookCreator.createBook(title, genresList, pageCountValue, authorsList);
        return bookListDao.addBook(book);
    }

    public void removeBook(String title, String genres, String pageCount, String authors) throws BookNotFoundException, BookCreationException {
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.validateNumberString(pageCount)) {
            throw new BookCreationException("Invalid page count string value");
        }
        int pageCountValue = Integer.parseInt(pageCount);
        List<String> genresList = splitWords(genres);
        List<String> authorsList = splitWords(authors);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookCreator bookCreator = new BookCreator();
        Book book = bookCreator.createBook(title, genresList, pageCountValue, authorsList);
        bookListDao.removeBook(book);
    }

    @Override
    public Optional<Book> findById(String id) {
        // TODO Realize method
        return Optional.empty();
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

    @Override
    public List<Book> findByPageCount(String pageCount) {
        // TODO Realize method
        return null;
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

    private List<String> splitWords(String text) {
        String[] words = text.split("//s+");
        return List.of(words);
    }
}
