package com.kuntsevich.task6.controller.command.impl;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.entity.Response;

import java.util.ArrayList;
import java.util.Map;

public class EmptyCommandImpl implements Command {

    @Override
    public Response execute(Map<String, String> params) {
        Response response = new Response(false, new ArrayList<>());
        return response;
    }
}
