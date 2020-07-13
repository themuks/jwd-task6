package com.kuntsevich.task6.controller.command.impl;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.exception.CommandExecutionException;
import com.kuntsevich.task6.model.service.BookService;
import com.kuntsevich.task6.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookAddCommandImpl implements Command {

    private static final String PARAM_NAME_TITLE = "title";
    private static final String PARAM_NAME_GENRES = "genres";
    private static final String PARAM_NAME_PAGE_COUNT = "pageCount";
    private static final String PARAM_NAME_AUTHORS = "authors";

    private BookService bookService = new BookServiceImpl();

    @Override
    public Map<String, List<Book>> execute(Map<String, String> params) throws CommandExecutionException {
        Map<String, List<Book>> response = new HashMap<>();
        String title = params.get(PARAM_NAME_TITLE);
        String genres = params.get(PARAM_NAME_GENRES);
        String pageCount = params.get(PARAM_NAME_PAGE_COUNT);
        String authors = params.get(PARAM_NAME_AUTHORS);
        try {
            if (bookService.addBook(title, genres, pageCount, authors)) {
                response.put("Success", null);
                return response;
            }
        } catch (BookCreationException e) {
            throw new CommandExecutionException("Can't create Book object");
        }
        response.put("Error", null);
        return response;
    }
}
