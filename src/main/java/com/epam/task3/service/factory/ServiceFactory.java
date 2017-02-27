package com.epam.task3.service.factory;

import com.epam.task3.service.NewsService;
import com.epam.task3.service.impl.NewsServiceImpl;

/**
 * Class it is a singleton
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final NewsService newsService = new NewsServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public NewsService getNewsService() {
        return newsService;
    }
}
