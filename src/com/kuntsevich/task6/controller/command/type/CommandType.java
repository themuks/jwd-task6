package com.kuntsevich.task6.controller.command.type;

import com.kuntsevich.task6.controller.command.Command;
import com.kuntsevich.task6.controller.command.impl.BookAddCommandImpl;
import com.kuntsevich.task6.controller.command.impl.BookRemoveCommandImpl;

public enum CommandType {

    ADD_BOOK {
        {
            this.command = new BookAddCommandImpl();
        }
    },

    REMOVE_BOOK {
        {
            this.command = new BookRemoveCommandImpl();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }
}
