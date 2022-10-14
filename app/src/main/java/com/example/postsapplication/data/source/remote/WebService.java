package com.example.postsapplication.data.source.remote;

import com.example.postsapplication.data.model.PostResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("posts")
    Call<List<PostResponseItem>> getposts();

    @GET("posts/{id}")
    Call<PostResponseItem> getPost(@Path("id") int id);

}
