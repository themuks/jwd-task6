package com.kuntsevich.task6.service.impl;

import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookNotFoundException;
import com.kuntsevich.task6.service.BookListDao;

import java.util.ArrayList;
import java.util.List;

public class BookListDaoImpl implements BookListDao {

    private static volatile BookListDaoImpl instance;

    private List<Book> books = new ArrayList<>();

    public static BookListDaoImpl getInstance() {
        if (instance == null) {
            synchronized (BookListDaoImpl.class) {
                if (instance == null) {
                    instance = new BookListDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean addBook(Book book) {
        if (books.contains(book)) {
            return false;
        }
        books.add(book);
        return true;
    }

    @Override
    public void removeBook(Book book) throws BookNotFoundException {
        boolean result = books.remove(book);
        if (!result) {
            throw new BookNotFoundException();
        }
    }

    @Override
    public List<Book> findById(int id) {
        List<Book> result = new ArrayList<>();
        for (var book : books) {
            if (book.getId() == id) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (var book : books) {
            if (book.getTitle().equals(title)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByGenres(List<String> genres) {
        List<Book> result = new ArrayList<>();
        for (var book : books) {
            if (isBookContainsGenres(book, genres)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByPageCount(int pageCount) {
        List<Book> result = new ArrayList<>();
        for (var book : books) {
            if (book.getPageCount() == pageCount) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        List<Book> result = new ArrayList<>();
        for (var book : books) {
            if (book.getAuthorName().equals(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> sortById() {
        List<Book> result = new ArrayList<>(books);
        int length = result.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (result.get(j).getId() > result.get(j + 1).getId()) {
                    Book temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    @Override
    public List<Book> sortByTitle() {
        List<Book> result = new ArrayList<>(books);
        int length = result.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                Book book1 = result.get(j);
                Book book2 = result.get(j + 1);
                if (book1.getTitle().compareTo(book2.getTitle()) > 0) {
                    Book temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    @Override
    public List<Book> sortByPageCount() {
        List<Book> result = new ArrayList<>(books);
        int length = result.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (result.get(j).getPageCount() > result.get(j + 1).getPageCount()) {
                    Book temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    @Override
    public List<Book> sortByAuthorName() {
        List<Book> result = new ArrayList<>(books);
        int length = result.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                Book book1 = result.get(j);
                Book book2 = result.get(j + 1);
                if (book1.getAuthorName().compareTo(book2.getAuthorName()) > 0) {
                    Book temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    private List<String> getGenres(Book book) {
        int count = book.getGenresCount();
        List<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String genre = book.getGenre(i);
            result.add(genre);
        }
        return result;
    }

    private boolean isBookContainsGenres(Book book, List<String> genres) {
        List<String> bookGenres = getGenres(book);
        boolean result = true;
        for (var genre : genres) {
            if (!bookGenres.contains(genre)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
