package com.epam.task3.controller.command.impl;

import com.epam.task3.controller.command.Command;

/**
 * Class incorrect request of user
 */
public class WrongRequest implements Command {
    private static final String INCORRECT_MESSAGE = "Incorrect command";

    /**
     * @param request request of user
     * @return message of incorrect command
     */
    @Override
    public String execute(String request) {
        return INCORRECT_MESSAGE;
    }
}
