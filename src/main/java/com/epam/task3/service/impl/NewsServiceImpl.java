package com.epam.task3.service.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.command.NewsCategory;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.factory.DAOFactory;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Class NewsServiceImpl which implements the interface methods of NewsService
 */
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    /**
     * Add news of file
     *
     * @param news request of user
     * @return message to user, if news added or doesn't add
     * @throws ServiceException
     */
    @Override
    public void addNews(News news) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            NewsDAO newsDAO = daoFactory.getNewDAO();
            NewsCategory.valueOf(news.getCategory().toUpperCase());
            newsDAO.addNews(null);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Receive all news of file
     *
     * @param request request of user
     * @return all news which searched
     * @throws ServiceException
     */
    @Override
    public String getNews(String request) throws ServiceException {
        String response;
        NewsDAO newsDAO;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            newsDAO = daoFactory.getNewDAO();
            response = newsDAO.getNews(getSearchParamOfNews(request));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return response;
    }

    /**
     * Receive all searching params which entered
     *
     * @param request request of user
     * @return list with all searching params
     */
    private ArrayList<String> getSearchParamOfNews(String request) {
        ArrayList<String> searchParam = new ArrayList<>();
        request = request.substring(request.indexOf(' ') + 1, request.length());
        String[] paramNews = request.split(",");
        if (paramNews.length < 1 || paramNews.length > 3) {
            throw new IllegalArgumentException("Entered incorrect number of parameters!");
        } else {
            for (int i = 0; i < paramNews.length; i++) {
                if (!paramNews[i].equals("")) {
                    searchParam.add(paramNews[i]);
                }
            }
        }
        return searchParam;
    }
}
