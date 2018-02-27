package com.example.stax.beetrack_test.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.stax.beetrack_test.Models.Article;
import com.example.stax.beetrack_test.Models.ArticleRepository;
import com.example.stax.beetrack_test.Models.JsonContent;
import com.example.stax.beetrack_test.R;

public class WebViewActivity extends AppCompatActivity {
    private static final String KEY_TAB = "KEY_TAB";
    private static final String KEY_INDEX = "KEY_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        WebView webView = (WebView)findViewById(R.id.article_webview);
        if(getIntent().getIntExtra(KEY_TAB,0)== 0){
            for (Article aux : JsonContent.getsArticles()) {
                if (aux.getTitle().equals(getIntent().getStringExtra(KEY_INDEX))) {
                    webView.setWebViewClient(new WebViewClient());
                    webView.loadUrl(aux.getUrl());
                }
            }
        }else {
            ArticleRepository db = new ArticleRepository();
            for (Article aux : db.getAll()) {
                if (aux.getTitle().equals(getIntent().getStringExtra(KEY_INDEX))) {
                    webView.setWebViewClient(new WebViewClient());
                    webView.loadUrl(aux.getUrl());
                }
            }
        }
    }

    public static void initWebView(Context context,String title, int index){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(KEY_INDEX,title);
        intent.putExtra(KEY_TAB,index);
        context.startActivity(intent);
    }
}
