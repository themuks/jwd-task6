package com.kuntsevich.task6.controller;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.controller.command.provider.CommandProvider;
import com.kuntsevich.task6.controller.entity.Request;
import com.kuntsevich.task6.controller.entity.Response;

import java.util.Map;

public class Controller {

    public Response executeTask(Request request) {
        Command command = CommandProvider.defineCommand(request.getCommand());
        Map<String, String> params = request.getParams();
        Response result = command.execute(params);
        return result;
    }
}
