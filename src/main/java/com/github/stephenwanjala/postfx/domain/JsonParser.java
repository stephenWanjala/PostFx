package com.github.stephenwanjala.postfx.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import com.github.stephenwanjala.postfx.domain.model.Post;

public class JsonParser {
    public static List<Post> parsePosts(String json) {
        Gson gson = new Gson();
        Type postListType = new TypeToken<List<Post>>() {}.getType();
        return gson.fromJson(json, postListType);
    }
}
