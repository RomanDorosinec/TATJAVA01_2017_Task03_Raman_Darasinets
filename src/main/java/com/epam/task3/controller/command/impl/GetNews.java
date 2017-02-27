package com.epam.task3.controller.command.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.command.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Class GetNews which implements the interface methods of Command
 */
public class GetNews implements Command {
    private static final String ERROR_GETTING_NEWS = "Error when getting news!";
    private static final String ERROR_NEWS_NOT_EXIST = "News does not exist!";

    private static final Logger logger = LogManager.getLogger(GetNews.class);

    /**
     * Receive all news of file
     *
     * @param request request of user
     * @return all news which searched
     */
    @Override
    public String execute(String request) {
        String response;
        ArrayList<News> allNews;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();
        try {
            StringBuilder builder = new StringBuilder();
            allNews = newsService.getNews(getParams(request));
            if (allNews.size() != 0) {
                for (News news : allNews) {
                    builder.append(news.toString() + "\n");
                }
                response = builder.toString();
            } else {
                response = ERROR_NEWS_NOT_EXIST;
            }
        } catch (ServiceException e) {
            response = ERROR_GETTING_NEWS;
            logger.error(e);
        }
        return response;
    }

    private News getParams(String request) {
        request = request.substring(request.indexOf(' ') + 1, request.length());
        String[] paramNews = request.split(",", 3);
        return new News(paramNews[0], paramNews[1], paramNews[2]);
    }
}
