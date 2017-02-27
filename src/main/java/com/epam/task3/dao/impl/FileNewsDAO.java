package com.epam.task3.dao.impl;

import com.epam.task3.bean.News;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.exception.DAOException;

import java.io.*;
import java.util.ArrayList;

/**
 * Class FileNewsDAO which implements the interface methods of NewsDAO
 */
public class FileNewsDAO implements NewsDAO {

    private static final String ERROR_NOT_FOUND_FILE_MESSAGE = "Not found txt file!";
    private static final String ERROR_CLOSING_MESSAGE = "Error closing of stream!";

    /**
     * File location
     */
    private static final String TXT_FILE_PATH = "newsCatalog.txt";

    /**
     * Opens a stream for writing. Creates a string to record. Closes all streams
     *
     * @param news entered news
     * @throws DAOException not found txt file, error closing of stream FileWriter
     */
    @Override
    public void addNews(News news) throws DAOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(TXT_FILE_PATH, true);
            writer.write(news.getCategory() + "," + news.getTitle() + "," + news.getAuthor());
            writer.append("\n");
        } catch (FileNotFoundException e) {
            throw new DAOException(ERROR_NOT_FOUND_FILE_MESSAGE);
        } catch (IOException e) {
            throw new DAOException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new DAOException(ERROR_CLOSING_MESSAGE);
            }
        }
    }

    /**
     * Searches for all the news that match the search criteria
     *
     * @param news news bean to find news in file
     * @return all searching news
     * @throws DAOException not found txt file, end file, error closing of stream
     */
    @Override
    public ArrayList<News> getNews(News news) throws DAOException {
        ArrayList<News> allNews = new ArrayList<>();
        FileReader reader = null;
        BufferedReader br = null;
        try {
            boolean flag;
            reader = new FileReader(TXT_FILE_PATH);
            br = new BufferedReader(reader);
            String s;
            while ((s = br.readLine()) != null) {
                flag = getFlag(s, news.getCategory());
                flag = getFlag(s, news.getTitle());
                flag = getFlag(s, news.getAuthor());
                if (flag) {
                    allNews.add(getParamNews(s));
                }
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(ERROR_NOT_FOUND_FILE_MESSAGE);
        } catch (IOException e) {
            throw new DAOException(e);
        } finally {
            try {
                reader.close();
                br.close();
            } catch (IOException e) {
                throw new DAOException(ERROR_CLOSING_MESSAGE);
            }
        }
        return allNews;
    }

    private News getParamNews(String line) {
        String[] param = line.split(",");
        return new News(param[0], param[1], param[2]);
    }

    private boolean getFlag(String lineOfFile, String param) {
        boolean flag;
        if (lineOfFile.indexOf(param) != -1) {
            flag = true;
        } else if (param.equals("")) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}
