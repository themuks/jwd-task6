package com.kuntsevich.task6.controller.command;

import com.kuntsevich.task6.entity.Response;
import com.kuntsevich.task6.exception.CommandException;

import java.util.Map;

public interface Command {

    Response execute(Map<String, String> params) throws CommandException;
}
