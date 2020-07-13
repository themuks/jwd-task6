package com.kuntsevich.task6.controller;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.controller.command.provider.CommandProvider;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.CommandExecutionException;

import java.util.List;
import java.util.Map;

public class Controller {

    public String executeTask(String request, Map<String, String> params) {
        Command command = CommandProvider.defineCommand(request);
        StringBuilder response = new StringBuilder();
        Map<String, List<Book>> result = null;
        try {
            result = command.execute(params);
        } catch (CommandExecutionException e) {
            // TODO Write log
            response.append("Error executing ");
            response.append(request);
        }
        if (result != null) {
            if (result.containsKey("Empty")) {
                response.append("Command ");
                response.append(request);
                response.append(" doesn't exist");
            }
            if (result.containsKey("Success")) {
                response.append("Success executing ");
                response.append(request);
                response.append(". Result: ");
                response.append(result.get("Success"));
            }
        }
        return response.toString();
    }
}
