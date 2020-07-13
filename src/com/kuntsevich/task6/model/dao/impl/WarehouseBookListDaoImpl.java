package com.kuntsevich.task6.model.dao.impl;

import com.kuntsevich.task6.model.dao.BookListDao;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.entity.BookWarehouse;
import com.kuntsevich.task6.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WarehouseBookListDaoImpl implements BookListDao {

    @Override
    public boolean addBook(Book book) {
        return BookWarehouse.getInstance().add(book);
    }

    @Override
    public void removeBook(Book book) throws BookNotFoundException {
        boolean result = BookWarehouse.getInstance().remove(book);
        if (!result) {
            throw new BookNotFoundException();
        }
    }

    @Override
    public Optional<Book> findById(int id) {
        List<Book> books = getBooks();
        Book result = null;
        for (var book : books) {
            if (book.getId() == id) {
                return Optional.of(result);
            }
        }
        return Optional.of(result);
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        List<Book> books = getBooks();
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
        List<Book> books = getBooks();
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
        List<Book> books = getBooks();
        for (var book : books) {
            if (book.getPageCount() == pageCount) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByAuthors(List<String> authors) {
        List<Book> result = new ArrayList<>();
        List<Book> books = getBooks();
        for (var book : books) {
            if (isBookContainsAuthors(book, authors)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> sortById() {
        List<Book> books = getBooks();
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
        List<Book> books = getBooks();
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
    public List<Book> sortByGenres() {
        List<Book> books = getBooks();
        List<Book> result = new ArrayList<>(books);
        int length = result.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                Book book1 = result.get(j);
                Book book2 = result.get(j + 1);
                int count1 = book1.getGenresCount();
                int count2 = book2.getGenresCount();
                boolean flag = false;
                if (count1 == count2) {
                    int count = book1.getGenresCount();
                    for (int k = 0; k < count; k++) {
                        String genre1 = book1.getGenre(k);
                        String genre2 = book2.getGenre(k);
                        if (genre1.compareTo(genre2) > 0) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (count1 > count2 || flag) {
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
        List<Book> books = getBooks();
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
    public List<Book> sortByAuthors() {
        List<Book> books = getBooks();
        List<Book> result = new ArrayList<>(books);
        int length = result.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                Book book1 = result.get(j);
                Book book2 = result.get(j + 1);
                int count1 = book1.getAuthorsCount();
                int count2 = book2.getAuthorsCount();
                boolean flag = false;
                if (count1 == count2) {
                    int count = book1.getAuthorsCount();
                    for (int k = 0; k < count; k++) {
                        String author1 = book1.getAuthor(k);
                        String author2 = book2.getAuthor(k);
                        if (author1.compareTo(author2) > 0) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (count1 > count2 || flag) {
                    Book temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
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

    private List<String> getGenres(Book book) {
        int count = book.getGenresCount();
        List<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String genre = book.getGenre(i);
            result.add(genre);
        }
        return result;
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
