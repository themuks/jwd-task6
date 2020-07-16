package com.kuntsevich.task6.controller.command.impl;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.entity.Response;
import com.kuntsevich.task6.exception.CommandException;
import com.kuntsevich.task6.exception.ServiceException;
import com.kuntsevich.task6.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Map;

public class BookAddCommandImpl implements Command {

    private static final String PARAM_NAME_TITLE = "title";
    private static final String PARAM_NAME_GENRES = "genres";
    private static final String PARAM_NAME_PAGE_COUNT = "pageCount";
    private static final String PARAM_NAME_AUTHORS = "authors";

    @Override
    public Response execute(Map<String, String> params) throws CommandException {
        Response response;
        String title = params.get(PARAM_NAME_TITLE);
        String genres = params.get(PARAM_NAME_GENRES);
        String pageCount = params.get(PARAM_NAME_PAGE_COUNT);
        String authors = params.get(PARAM_NAME_AUTHORS);
        try {
            BookServiceImpl.getInstance().addBook(title, genres, pageCount, authors);
            response = new Response(false, new ArrayList<>());
        } catch (ServiceException e) {
            response = new Response(true, new ArrayList<>());
        }
        return response;
    }
}
