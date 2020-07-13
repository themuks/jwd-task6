package com.kuntsevich.task6.controller.command;

import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.CommandExecutionException;

import java.util.List;
import java.util.Map;

public interface Command {

    Map<String, List<Book>> execute(Map<String, String> params) throws CommandExecutionException;
}
