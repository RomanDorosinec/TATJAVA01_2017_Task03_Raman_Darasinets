package com.epam.task3.controller.command;

/**
 * Interface that can be execute the command
 */
public interface Command {
    /**
     * Execute the entered command
     *
     * @param request request of user
     * @return message when the command was executed
     */
    public String execute(String request);
}
