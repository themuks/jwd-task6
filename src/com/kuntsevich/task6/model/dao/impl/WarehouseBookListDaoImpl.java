package com.kuntsevich.task6.model.dao.impl;

import com.kuntsevich.task6.comparator.BookAuthorsComparator;
import com.kuntsevich.task6.comparator.BookGenreComparator;
import com.kuntsevich.task6.comparator.BookTitleComparator;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.entity.BookWarehouse;
import com.kuntsevich.task6.exception.DaoException;
import com.kuntsevich.task6.model.dao.BookListDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseBookListDaoImpl implements BookListDao {

    @Override
    public void addBook(Book book) throws DaoException {
        if (!BookWarehouse.getInstance().add(book)) {
            throw new DaoException("Can't add book");
        }
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        boolean result = BookWarehouse.getInstance().remove(book);
        if (!result) {
            throw new DaoException("Book not found");
        }
    }

    @Override
    public Book findById(int id) throws DaoException {
        List<Book> books = getBooks();
        Book result = books.stream().filter(book -> book.getBookId() == id).findFirst().orElse(null);
        if (result == null) {
            throw new DaoException("Book not found");
        }
        return result;
    }

    @Override
    public List<Book> findByTitle(String title) throws DaoException {
        List<Book> books = getBooks();
        List<Book> result = books.stream().filter(book -> book.getTitle().equals(title)).collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new DaoException("Books not found");
        }
        return result;
    }

    @Override
    public List<Book> findByGenre(String genre) throws DaoException {
        List<Book> books = getBooks();
        List<Book> result = books.stream().filter(book -> book.getGenre().equals(genre)).collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new DaoException("Books not found");
        }
        return result;
    }

    @Override
    public List<Book> findByPageCount(int pageCount) throws DaoException {
        List<Book> books = getBooks();
        List<Book> result = books.stream().filter(book -> book.getPageCount() == pageCount).collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new DaoException("Books not found");
        }
        return result;
    }

    @Override
    public List<Book> findByAuthors(List<String> authors) throws DaoException {
        List<Book> books = getBooks();
        List<Book> result = books.stream().filter(book -> getAuthors(book).containsAll(authors)).collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new DaoException("Books not found");
        }
        return result;
    }

    @Override
    public List<Book> sortById() {
        List<Book> books = getBooks();
        List<Book> result = books.stream().sorted(Comparator.comparingInt(Book::getBookId)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Book> sortByTitle() {
        List<Book> books = getBooks();
        BookTitleComparator bookTitleComparator = new BookTitleComparator();
        List<Book> result = books.stream().sorted(bookTitleComparator).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Book> sortByGenre() {
        List<Book> books = getBooks();
        BookGenreComparator bookGenreComparator = new BookGenreComparator();
        List<Book> result = books.stream().sorted(bookGenreComparator).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Book> sortByPageCount() {
        List<Book> books = getBooks();
        List<Book> result = books.stream().sorted(Comparator.comparingInt(Book::getPageCount)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Book> sortByAuthors() {
        List<Book> books = getBooks();
        BookAuthorsComparator bookAuthorsComparator = new BookAuthorsComparator();
        List<Book> result = books.stream().sorted(bookAuthorsComparator).collect(Collectors.toList());
        return result;
    }

    private List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        int count = BookWarehouse.getInstance().getBooksCount();
        for (int i = 0; i < count; i++) {
            Book book = BookWarehouse.getInstance().getBook(i);
            books.add(book);
        }
        return books;
    }

    private List<String> getAuthors(Book book) {
        int count = book.getAuthorsCount();
        List<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String author = book.getAuthor(i);
            result.add(author);
        }
        return result;
    }

    private boolean isBookContainsAuthors(Book book, List<String> authors) {
        List<String> bookAuthors = getAuthors(book);
        boolean result = true;
        for (var author : authors) {
            if (!bookAuthors.contains(author)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
