package com.kuntsevich.task6.controller;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.controller.command.provider.CommandProvider;
import com.kuntsevich.task6.entity.Request;
import com.kuntsevich.task6.entity.Response;
import com.kuntsevich.task6.exception.CommandException;
import com.kuntsevich.task6.exception.ControllerException;

import java.util.ArrayList;

public class Controller {

    public Response executeTask(Request request) throws ControllerException {
        Command command = CommandProvider.defineCommand(request.getCommand());
        Response result;
        try {
            var params = request.getParams();
            result = command.execute(params);
        } catch (CommandException e) {
            throw new ControllerException("Command execution error");
        }
        if (result == null) {
            result = new Response(true, new ArrayList<>());
        }
        return result;
    }
}
