package com.epam.task3.service;

import com.epam.task3.bean.News;
import com.epam.task3.service.exception.ServiceException;


/**
 * Interface that can be added and receive the news
 */
public interface NewsService {
    /**
     * News add's entered by user to file
     */
    void addNews(News news) throws ServiceException;

    /**
     * Receive all the news from the file
     */
    String getNews(String request) throws ServiceException;
}
