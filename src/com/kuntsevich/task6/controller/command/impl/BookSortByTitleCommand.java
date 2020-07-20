package com.kuntsevich.task6.controller.command.impl;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.controller.entity.Response;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.model.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Map;

public class BookSortByTitleCommand implements Command {

    @Override
    public Response execute(Map<String, String> params) {
        Response response;
        List<Book> books = BookServiceImpl.getInstance().sortByTitle();
        response = new Response(false, books);
        return response;
    }
}
