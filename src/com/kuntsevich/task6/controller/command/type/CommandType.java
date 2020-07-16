package com.kuntsevich.task6.controller.command.type;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.controller.command.impl.BookAddCommandImpl;

public enum CommandType {

    ADD_BOOK(new BookAddCommandImpl());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
