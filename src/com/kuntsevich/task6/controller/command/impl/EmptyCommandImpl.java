package com.kuntsevich.task6.controller.command.impl;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.CommandExecutionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmptyCommandImpl implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> params)  {
        Map<String, List<Book>> response = new HashMap<>();
        response.put("Empty", null);
        return response;
    }
}
