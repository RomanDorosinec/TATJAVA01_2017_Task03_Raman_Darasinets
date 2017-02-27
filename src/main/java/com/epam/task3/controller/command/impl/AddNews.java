package com.epam.task3.controller.command.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.command.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class AddNews which implements the interface methods of Command
 */
public class AddNews implements Command {

    private static final String NEWS_ADD_MESSAGE = "News add";
    private static final String ERROR_ADDED_MESSAGE = "Error added news!";
    private static final String INCORRECT_NUMBER_PARAM = "Entered incorrect number of parameters!";

    private static final Logger logger = LogManager.getLogger(AddNews.class);
    /**
     * Add news of file
     *
     * @param request request of user
     * @return message to user, if news added or doesn't add
     */
    @Override
    public String execute(String request) {
        String response;
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            NewsService newsService = serviceFactory.getNewsService();
            newsService.addNews(getParams(request));
            response = NEWS_ADD_MESSAGE;
        } catch (IllegalArgumentException e) {
            response = ERROR_ADDED_MESSAGE;
            logger.error(e);
        } catch (ServiceException e) {
            response = ERROR_ADDED_MESSAGE;
            logger.error(e);
        }
        return response;
    }

    private News getParams(String request) {
        request = request.substring(request.indexOf(' ') + 1, request.length());
        String[] paramNews = request.split(",");
        if (paramNews.length < 3 || paramNews.length > 3) {
            throw new IllegalArgumentException(INCORRECT_NUMBER_PARAM);
        }
        return new News(paramNews[0], paramNews[1], paramNews[2]);
    }
}
