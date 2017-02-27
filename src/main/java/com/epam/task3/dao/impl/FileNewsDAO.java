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
     * @param searchParam params of searching news
     * @return all searching news
     * @throws DAOException not found txt file, end file, error closing of stream
     */
    @Override
    public String getNews(ArrayList<String> searchParam) throws DAOException {
        String response;
        FileReader reader = null;
        BufferedReader br = null;
        try {
            reader = new FileReader(TXT_FILE_PATH);
            br = new BufferedReader(reader);
            String s;
            StringBuilder stringBuilder = new StringBuilder();
            while ((s = br.readLine()) != null) {
                int count = 0;
                for (String param : searchParam) {
                    if (s.indexOf(param) != -1) {
                        count++;
                    }
                    if (count == searchParam.size()) {
                        stringBuilder.append(getParamNews(s).toString() + "\n");
                    }
                }
            }
            response = stringBuilder.toString();
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
        return response;
    }

    /**
     * Parses line from a file on the params
     *
     * @param line one line of file
     * @return bean object News with all params
     */
    private News getParamNews(String line) {
        String[] param = line.split(",");
        return new News(param[0], param[1], param[2]);
    }
}
