package com.epam.task3.dao;

import com.epam.task3.bean.News;
import com.epam.task3.dao.exception.DAOException;

import java.util.ArrayList;

/**
 * Interface that can be added and receive the news
 */
public interface NewsDAO {
    /**
     * News add's entered by user to file
     *
     * @param news entered news
     * @throws DAOException errors that may occur when you add a file
     */
    void addNews(News news) throws DAOException;

    /**
     * Receive all the news from the file
     *
     * @param news params of searching news
     * @return line with all find news
     * @throws DAOException errors that may occur when you receive news a file
     */
    ArrayList<News> getNews(News news) throws DAOException;
}
