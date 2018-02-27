package com.example.stax.beetrack_test.Holders;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stax.beetrack_test.R;

/**
 * Created by stax on 26-02-18.
 */

public class MainViewHolder extends RecyclerView.ViewHolder{

    private ImageView newImgToUrl;
    private TextView newsDate;
    private TextView newsTitle;
    private TextView newsDes;

    public CheckBox getNewsCheckbox() {
        return newsCheckbox;
    }

    public void setNewsCheckbox(Boolean newsCheckbox) {
        this.newsCheckbox.setChecked(newsCheckbox);
    }

    private CheckBox newsCheckbox;

    public ImageView getNewImg() {
        return newImgToUrl;
    }

    public void setNewImg(String newImg) {
        Glide.with(this.newImgToUrl.getContext()).load(newImg)
                .centerCrop()
                .into(this.newImgToUrl);
    }

    public TextView getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate.setText(newsDate);
    }

    public TextView getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle.setText(newsTitle);
    }

    public TextView getNewsDes() {
        return newsDes;
    }

    public void setNewsDes(String newsDes) {
        this.newsDes.setText(newsDes);
    }

    public MainViewHolder(View itemView) {
        super(itemView);
        newImgToUrl = (ImageView)itemView.findViewById(R.id.news_image);
        newsDate = (TextView)itemView.findViewById(R.id.news_date);
        newsTitle = (TextView)itemView.findViewById(R.id.news_title);
        newsDes = (TextView)itemView.findViewById(R.id.news_description);
        newsCheckbox = (CheckBox) itemView.findViewById(R.id.news_checkbox);
    }
}
