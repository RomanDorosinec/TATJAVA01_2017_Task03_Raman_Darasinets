package com.epam.task3.service;

import com.epam.task3.service.exception.ServiceException;


/**
 * Interface that can be added and receive the news
 */
public interface NewsService {
    /**
     * News add's entered by user to file
     *
     * @param request request of user
     * @return message if news added
     * @throws ServiceException
     */
    String addNews(String request) throws ServiceException;

    /**
     * Receive all the news from the file
     *
     * @param request request of user
     * @return all news which searching
     * @throws ServiceException
     */
    String getNews(String request) throws ServiceException;
}
