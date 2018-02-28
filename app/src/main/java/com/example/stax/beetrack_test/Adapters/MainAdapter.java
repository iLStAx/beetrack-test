package com.example.stax.beetrack_test.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stax.beetrack_test.Activities.WebViewActivity;
import com.example.stax.beetrack_test.Holders.MainViewHolder;
import com.example.stax.beetrack_test.Interfaces.ApiService;
import com.example.stax.beetrack_test.Models.Article;
import com.example.stax.beetrack_test.Models.ArticleRepository;
import com.example.stax.beetrack_test.Models.JsonContent;
import com.example.stax.beetrack_test.R;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stax on 26-02-18.
 */

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{

    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


    public MainAdapter(){
        articles = Collections.emptyList();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://newsapi.org/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ApiService api = retrofit.create(ApiService.class);
        Call<JsonContent> call = api.getData();

        call.enqueue(new Callback<JsonContent>() {
            @Override
            public void onResponse(Call<JsonContent> call, Response<JsonContent> response) {
                List<Article> articles = response.body().getArticles();
                JsonContent.setsArticles(articles);
                setArticles(articles);
                setCheckBoxes(articles);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JsonContent> call, Throwable t) {

            }
        });
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        final Article article = articles.get(position);
        holder.setNewImg(article.getUrlToImage());
        holder.setNewsDate(article.getPublishedAt());
        holder.setNewsTitle(article.getTitle());
        holder.setNewsDes(article.getDescription());
        holder.setNewsCheckbox(article.getFavorites());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.initWebView(view.getContext(), article.getTitle(), 0);
            }
        });
        holder.getNewsCheckbox().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArticleRepository db = new ArticleRepository();
                if(holder.getNewsCheckbox().isChecked()){
                    article.setFavorites(true);
                    db.add(article);
                }else{
                    db.delete(article);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setCheckBoxes(List<Article> articles){
        Realm db = Realm.getDefaultInstance();
        RealmResults<Article> aux = db.where(Article.class).findAll();
        for(int i = 0; i < articles.size(); i++){
            for(int j = 0;j < aux.size(); j++){
                if(aux.get(j).getTitle().equals(articles.get(i).getTitle()))
                {
                    articles.get(i).setFavorites(true);
                }
            }
        }
    }
}
