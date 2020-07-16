package com.kuntsevich.task6.model.service.impl;

import com.kuntsevich.task6.creator.BookCreator;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.exception.DaoException;
import com.kuntsevich.task6.exception.ServiceException;
import com.kuntsevich.task6.model.dao.BookListDao;
import com.kuntsevich.task6.model.dao.factory.DaoFactory;
import com.kuntsevich.task6.model.service.BookService;
import com.kuntsevich.task6.validator.NumberValidator;

import java.util.List;

public class BookServiceImpl implements BookService {

    private static volatile BookServiceImpl instance;

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            synchronized (BookServiceImpl.class) {
                if (instance == null) {
                    instance = new BookServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean addBook(String title, String genres, String pageCount, String authors) throws ServiceException {
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.isNumberStringValid(pageCount)) {
            throw new ServiceException("Invalid page count string value");
        }
        int pageCountValue = Integer.parseInt(pageCount);
        List<String> genresList = splitWords(genres);
        List<String> authorsList = splitWords(authors);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookCreator bookCreator = new BookCreator();
        Book book = null;
        try {
            book = bookCreator.createBook(title, genresList, pageCountValue, authorsList);
        } catch (BookCreationException e) {
            throw new ServiceException("Can't create book");
        }
        try {
            return bookListDao.addBook(book);
        } catch (DaoException e) {
            throw new ServiceException("Can't add book");
        }
    }

    @Override
    public void removeBook(String title, String genres, String pageCount, String authors) throws ServiceException {
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.isNumberStringValid(pageCount)) {
            throw new ServiceException("Invalid page count string value");
        }
        int pageCountValue = Integer.parseInt(pageCount);
        List<String> genresList = splitWords(genres);
        List<String> authorsList = splitWords(authors);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookCreator bookCreator = new BookCreator();
        Book book = null;
        try {
            book = bookCreator.createBook(title, genresList, pageCountValue, authorsList);
        } catch (BookCreationException e) {
            throw new ServiceException("Can't create book");
        }
        try {
            bookListDao.removeBook(book);
        } catch (DaoException e) {
            throw new ServiceException("Can't remove book");
        }
    }

    @Override
    public Book findById(String id) throws ServiceException {
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.isNumberStringValid(id)) {
            throw new ServiceException("Invalid id value");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        int intId = Integer.parseInt(id);
        try {
            return bookListDao.findById(intId);
        } catch (DaoException e) {
            throw new ServiceException("Book not found");
        }
    }

    @Override
    public List<Book> findByTitle(String title) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.findByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    @Override
    public List<Book> findByGenres(List<String> genres) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.findByGenres(genres);
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    @Override
    public List<Book> findByPageCount(String pageCount) throws ServiceException {
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.isNumberStringValid(pageCount)) {
            throw new ServiceException("Invalid page count value");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        int intPageCount = Integer.parseInt(pageCount);
        try {
            return bookListDao.findByPageCount(intPageCount);
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    @Override
    public List<Book> findByAuthors(List<String> authors) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.findByAuthors(authors);
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    @Override
    public List<Book> sortById() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.sortById();
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    @Override
    public List<Book> sortByTitle() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.sortByTitle();
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    @Override
    public List<Book> sortByGenres() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.sortByGenres();
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    @Override
    public List<Book> sortByPageCount() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.sortByPageCount();
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    @Override
    public List<Book> sortByAuthors() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.sortByAuthors();
        } catch (DaoException e) {
            throw new ServiceException("Books not found");
        }
    }

    private List<String> splitWords(String text) {
        String[] words = text.split("//s+");
        return List.of(words);
    }
}
