package com.example.stax.beetrack_test.Models;

import com.example.stax.beetrack_test.Interfaces.Repository;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by stax on 26-02-18.
 */

public class Article extends RealmObject{
    private String author;
    @PrimaryKey
    @Required
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private Boolean favorites = false;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Boolean getFavorites() {return  favorites;}

    public void setFavorites(Boolean favorites) {this.favorites = favorites;}

}
