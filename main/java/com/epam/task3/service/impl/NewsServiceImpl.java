package com.epam.task3.service.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.NewsCategory;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.factory.DAOFactory;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Class NewsServiceImpl which implements the interface methods of NewsService
 */
public class NewsServiceImpl implements NewsService {
    /**
     * Add news of file
     *
     * @param request request of user
     * @return message to user, if news added or doesn't add
     * @throws ServiceException
     */
    @Override
    public String addNews(String request) throws ServiceException {
        String response;
        try {
            response = checkCorrectCategoryOfNews(request);
            DAOFactory daoFactory = DAOFactory.getInstance();
            NewsDAO newsDAO = daoFactory.getNewDAO();
            newsDAO.addNews(getParamOfNews(request));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return response;
    }

    /**
     * Receive bean object with params and check this params
     *
     * @param request request of user
     * @return bean object of news
     */
    private News getParamOfNews(String request) {
        request = request.substring(request.indexOf(' ') + 1, request.length());
        String[] paramNews = request.split(",");
        News news;
        if (paramNews.length < 3 || paramNews.length > 3) {
            throw new IllegalArgumentException("Entered incorrect number of parameters!");
        } else {
            news = new News(paramNews[0], paramNews[1], paramNews[2]);
        }
        return news;
    }

    /**
     * Checks the ability to add news from a category
     *
     * @param request request of user
     * @return message to user, if news added or doesn't add
     */
    private String checkCorrectCategoryOfNews(String request) {
        String category = request.substring(request.indexOf(' ') + 1, request.indexOf(','));
        String response = null;
        try {
            if (category.toUpperCase().equals(NewsCategory.valueOf(category.toUpperCase()).toString())) {
                response = "News added!";
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error when you add news!");
        }
        return response;
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
