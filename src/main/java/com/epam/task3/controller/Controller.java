package com.epam.task3.controller;

import com.epam.task3.controller.command.Command;

/**
 * Class which to dispatch a number of commands
 */
public final class Controller {
    private final CommandProvider provider = new CommandProvider();

    private final char paramDelimiter = ' ';

    public String executeTask(String request) {
        String commandName;
        Command executionCommand;

        commandName = request.substring(0, request.indexOf(paramDelimiter));
        executionCommand = provider.getCommand(commandName);

        String response;
        response = executionCommand.execute(request);

        return response;
    }
}
