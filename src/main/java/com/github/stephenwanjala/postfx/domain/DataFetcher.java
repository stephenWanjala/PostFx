package com.github.stephenwanjala.postfx.domain;

import com.github.stephenwanjala.postfx.domain.model.Post;
import com.github.stephenwanjala.postfx.util.PostTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class DataFetcher {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static final PostsApi postsApi;

    static {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Post.class, new PostTypeAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        postsApi = retrofit.create(PostsApi.class);
    }

    public static void fetchPosts(Callback<List<Post>> callback) {
        Call<List<Post>> call = postsApi.getPosts();
        System.out.printf("Fetching posts from %s%n", BASE_URL + "posts");
        System.out.println(call);
        call.enqueue(callback);
    }
}