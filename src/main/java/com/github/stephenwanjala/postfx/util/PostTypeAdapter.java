package com.github.stephenwanjala.postfx.util;

import com.github.stephenwanjala.postfx.domain.model.Post;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PostTypeAdapter implements JsonSerializer<Post>, JsonDeserializer<Post> {

    @Override
    public JsonElement serialize(Post post, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", post.getUserId());
        jsonObject.addProperty("id", post.getId());
        jsonObject.addProperty("title", post.getTitle());
        jsonObject.addProperty("body", post.getBody());
        return jsonObject;
    }

    @Override
    public Post deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Post post = new Post();
        post.setUserId(jsonObject.get("userId").getAsInt());
        post.setId(jsonObject.get("id").getAsInt());
        post.setTitle(jsonObject.get("title").getAsString());
        post.setBody(jsonObject.get("body").getAsString());
        return post;
    }
}