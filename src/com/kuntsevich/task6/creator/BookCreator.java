package com.kuntsevich.task6.creator;

import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.util.BookIdGenerator;
import com.kuntsevich.task6.validator.BookValidator;

import java.util.List;

public class BookCreator {

    public Book createBook(String title, List<String> genres, int pageCount, List<String> authors) throws BookCreationException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isTitleValid(title)
                || !bookValidator.isGenresValid(genres)
                || !bookValidator.isPageCountValid(pageCount)
                || !bookValidator.isAuthorsValid(authors)) {
            throw new BookCreationException("Some of parameters are incorrect");
        }
        int id = BookIdGenerator.getInstance().generateId();
        return new Book(id, title, genres, pageCount, authors);
    }
}
