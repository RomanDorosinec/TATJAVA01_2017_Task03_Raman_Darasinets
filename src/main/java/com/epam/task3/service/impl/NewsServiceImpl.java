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
     * @throws ServiceException if there are exceptions in Service layer
     */
    @Override
    public void addNews(News news) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            NewsDAO newsDAO = daoFactory.getNewDAO();
            NewsCategory.valueOf(news.getCategory().toUpperCase());
            newsDAO.addNews(news);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Receive all news of file
     *
     * @param news
     * @return all news which searched
     * @throws ServiceException
     */
    @Override
    public ArrayList<News> getNews(News news) throws ServiceException {
        ArrayList<News> allNews;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            NewsDAO newsDAO = daoFactory.getNewDAO();
            allNews = newsDAO.getNews(news);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return allNews;
    }
}
