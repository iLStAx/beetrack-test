package com.example.stax.beetrack_test.Models;

import com.example.stax.beetrack_test.Adapters.FavoritesAdapter;
import com.example.stax.beetrack_test.Interfaces.Repository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by stax on 26-02-18.
 */

public class ArticleRepository implements Repository<Article> {
    private static Realm db;

    public ArticleRepository(){
        db = Realm.getDefaultInstance();
    }

    @Override
    public void add(Article item) {
        try {
            db.beginTransaction();
            db.insertOrUpdate(item);
            db.commitTransaction();
        }finally {
            db.close();
        }
    }

    @Override
    public void delete(Article item) {
        RealmResults<Article> results = db.where(Article.class).equalTo("title",item.getTitle()).findAll();
        try{
            db.beginTransaction();
            results.deleteAllFromRealm();
            db.commitTransaction();
        }finally {
            db.close();
        }
    }

    @Override
    public List<Article> getAll() {
        RealmResults<Article> articles = db.where(Article.class).findAll();
        articles.load();
        return articles;
    }
}
