package com.epam.task3.controller.command.impl;

import com.epam.task3.controller.command.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;

/**
 * Class GetNews which implements the interface methods of Command
 */
public class GetNews implements Command {
    /**
     * Receive all news of file
     *
     * @param request request of user
     * @return all news which searched
     */
    @Override
    public String execute(String request) {
        String response = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();
        try {
            response = newsService.getNews(request);
        } catch (ServiceException e) {
            e.printStackTrace();
            // TODO: 2/2/2017
        }
        return response;
    }
}
