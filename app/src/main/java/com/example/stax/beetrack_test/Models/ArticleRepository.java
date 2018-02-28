package com.example.stax.beetrack_test.Models;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.stax.beetrack_test.Interfaces.Repository;
import com.example.stax.beetrack_test.R;

import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.OrderedCollectionChangeSet;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmResults;

/**
 * Created by stax on 26-02-18.
 */

public class ArticleRepository implements Repository<Article> {

    private Realm mDB;
    private RealmChangeListener<RealmResults<Article>> mDListener = new RealmChangeListener<RealmResults<Article>>() {
        @Override
        public void onChange(RealmResults<Article> articles) {
            System.out.println("Se eliminó de favoritos: ");
        }
    };
    private RealmChangeListener<RealmResults<Article>> mIListener = new RealmChangeListener<RealmResults<Article>>() {
        @Override
        public void onChange(RealmResults<Article> articles) {
            System.out.println("Se ha añadido a favoritos: " + articles.last().getTitle());
        }
    };

    public ArticleRepository(){
        mDB = Realm.getDefaultInstance();
    }

    @Override
    public void add(Article item) {
        RealmResults<Article> results = mDB.where(Article.class).findAll();
        try {
            results.addChangeListener(mIListener);
            mDB.beginTransaction();
            mDB.insertOrUpdate(item);
            mDB.commitTransaction();
        }finally {
            mDB.close();
        }
    }

    @Override
    public void delete(Article item) {
        RealmResults<Article> results = mDB.where(Article.class).equalTo("title",item.getTitle()).findAll();
        try{
            results.addChangeListener(mDListener);
            mDB.beginTransaction();
            results.deleteAllFromRealm();
            mDB.commitTransaction();
        }finally {
            mDB.close();
        }
    }

    @Override
    public List<Article> getAll() {
        RealmResults<Article> articles = mDB.where(Article.class).findAll();
        articles.load();
        return articles;
    }
}
