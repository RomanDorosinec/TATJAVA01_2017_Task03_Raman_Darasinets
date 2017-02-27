package com.epam.task3.bean;

/**
 * Bean object. This class has constructor without params, setter and getter,
 * Has overridden methods equals(), hashCode() and toString()
 */
public class News {
    private String category;
    private String title;
    private String author;

    public News(){

    }

    public News(String category, String title, String author) {
        this.category = category;
        this.title = title;
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
}

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "News{" +
                "category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (category != null ? !category.equals(news.category) : news.category != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        return author != null ? author.equals(news.author) : news.author == null;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
