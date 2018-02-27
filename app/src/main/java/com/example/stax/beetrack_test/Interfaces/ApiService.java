package com.example.stax.beetrack_test.Interfaces;

import com.example.stax.beetrack_test.Models.JsonContent;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by stax on 26-02-18.
 */

public interface ApiService {
    @GET("/v1/articles?source=the-verge&apiKey=0c82a2269efd4a3cadb5d916b533b38b")
    Call<JsonContent> getData();
}
