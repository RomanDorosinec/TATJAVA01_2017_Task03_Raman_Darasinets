package com.epam.task3.controller.command.impl;

import com.epam.task3.controller.command.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;

/**
 * Class AddNews which implements the interface methods of Command
 */
public class AddNews implements Command {
    /**
     * Add news of file
     *
     * @param request request of user
     * @return message to user, if news added or doesn't add
     */
    @Override
    public String execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();
        String response = null;
        try {
            response = newsService.addNews(request);
        } catch (IllegalArgumentException e) {
            response = e.getMessage();
        } catch (ServiceException e) {
            e.printStackTrace();
            // TODO: 04.02.2017  
        }
        return response;
    }
}
