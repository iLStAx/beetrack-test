package com.example.stax.beetrack_test.Models;

import java.util.List;

/**
 * Created by stax on 26-02-18.
 */

public class JsonContent {
    private List<Article> articles;

    public static List<Article> getsArticles() {
        return sArticles;
    }

    public static void setsArticles(List<Article> sArticles) {
        JsonContent.sArticles = sArticles;
    }

    public static List<Article> sArticles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
