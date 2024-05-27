package com.github.stephenwanjala.postfx.domain;

import com.github.stephenwanjala.postfx.domain.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface PostsApi {
    @GET("/posts")
    Call<List<Post>> getPosts();
}
