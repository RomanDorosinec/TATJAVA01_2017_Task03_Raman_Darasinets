package com.epam.task3.dao.factory;

import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.impl.FileNewsDAO;

/**
 * Class it is a singleton
 */
public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private NewsDAO xmlNewsImpl = new FileNewsDAO();

    public static DAOFactory getInstance() {
        return instance;
    }

    public NewsDAO getNewDAO() {
        return xmlNewsImpl;
    }
}
