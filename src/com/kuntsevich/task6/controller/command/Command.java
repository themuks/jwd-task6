package com.kuntsevich.task6.controller.command;

import com.kuntsevich.task6.controller.entity.Response;

import java.util.Map;

public interface Command {

    Response execute(Map<String, String> params);
}
