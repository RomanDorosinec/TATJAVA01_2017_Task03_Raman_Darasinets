package com.epam.task3.controller;

import com.epam.task3.controller.command.Command;
import com.epam.task3.controller.command.CommandName;
import com.epam.task3.controller.command.impl.AddNews;
import com.epam.task3.controller.command.impl.GetNews;
import com.epam.task3.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class access to instances of the class
 */
final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.ADD_NEWS, new AddNews());
        repository.put(CommandName.GET_NEWS, new GetNews());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    /**
     * By title team returns an instance of this command
     *
     * @param name of command which entered
     * @return instances of the class
     */
    Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
