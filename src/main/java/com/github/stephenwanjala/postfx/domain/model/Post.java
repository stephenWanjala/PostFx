package com.github.stephenwanjala.postfx.domain.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Post {
    private final IntegerProperty userId = new SimpleIntegerProperty();
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty body = new SimpleStringProperty();

    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getBody() {
        return body.get();
    }

    public void setBody(String body) {
        this.body.set(body);
    }

    public StringProperty bodyProperty() {
        return body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId.get() +
                ", id=" + id.get() +
                ", title='" + title.get() + '\'' +
                ", body='" + body.get() + '\'' +
                '}';
    }
}