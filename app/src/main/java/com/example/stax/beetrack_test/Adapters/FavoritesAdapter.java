package com.example.stax.beetrack_test.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stax.beetrack_test.Activities.WebViewActivity;
import com.example.stax.beetrack_test.Holders.FavoritesViewHolder;
import com.example.stax.beetrack_test.Models.Article;
import com.example.stax.beetrack_test.Models.ArticleRepository;
import com.example.stax.beetrack_test.Models.JsonContent;
import com.example.stax.beetrack_test.R;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by stax on 26-02-18.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }


    public FavoritesAdapter(){
        ArticleRepository db = new ArticleRepository();
        articles = db.getAll();
        setArticles(articles);
        notifyDataSetChanged();
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new FavoritesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FavoritesViewHolder holder, final int position) {
        final Article article = articles.get(position);
        holder.setNewImg(article.getUrlToImage());
        holder.setNewsDate(article.getPublishedAt());
        holder.setNewsTitle(article.getTitle());
        holder.setNewsDes(article.getDescription());
        holder.setNewsCheckbox(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.initWebView(view.getContext(), article.getTitle(), 1);
            }
        });
        holder.getNewsCheckbox().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unSetCheckBoxes(JsonContent.getsArticles(),article.getTitle());
                ArticleRepository db = new ArticleRepository();
                db.delete(article);
                List<Article> articles = db.getAll();
                setArticles(articles);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void unSetCheckBoxes(List<Article> articles, String title){
        for(int i = 0; i < articles.size(); i++){
            if(articles.get(i).getTitle().equals(title)){
                articles.get(i).setFavorites(false);
            }
        }
    }
}
