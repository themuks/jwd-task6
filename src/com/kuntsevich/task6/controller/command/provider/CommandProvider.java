package com.kuntsevich.task6.controller.command.provider;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.controller.command.impl.EmptyCommandImpl;
import com.kuntsevich.task6.controller.command.type.CommandType;

public class CommandProvider {

    public static Command defineCommand(String action) {
        if (action == null || action.isBlank()) {
            return new EmptyCommandImpl();
        }
        CommandType currentType;
        try {
            currentType = CommandType.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new EmptyCommandImpl();
        }
        Command current = currentType.getCommand();
        return current;
    }
}
