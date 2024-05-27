package com.github.stephenwanjala.postfx.viewmodel;

import com.github.stephenwanjala.postfx.domain.DataFetcher;
import com.github.stephenwanjala.postfx.domain.model.Post;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

import java.util.List;
import java.util.stream.Collectors;


public class PostViewModel {
    private final ObservableList<Post> posts = FXCollections.observableArrayList();
    private final ListProperty<Post> filteredPosts = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final StringProperty filterText = new SimpleStringProperty("");
    private final IntegerProperty currentPage = new SimpleIntegerProperty(0);
    private static final int ROWS_PER_PAGE = 10;

    public PostViewModel() {
        DataFetcher.fetchPosts(new Callback<List<Post>>() {
            @Override
            public void onResponse( Call<List<Post>> call,  Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    posts.addAll(response.body());
                    System.out.println("Posts fetched successfully");
                    System.out.println(posts.size() + " posts fetched");
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure( Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public ObservableList<Post> getPosts() {
        return posts;
    }

    public StringProperty filterTextProperty() {
        return filterText;
    }

    public IntegerProperty currentPageProperty() {
        return currentPage;
    }

    public List<Post> getFilteredPosts() {
        String filter = filterText.get().toLowerCase();
        if (filter.isEmpty()) {
            System.out.println("Posts Before filter: " + posts.size());
            return posts;
        }
        return posts.stream()
                .filter(post -> post.getTitle().toLowerCase().contains(filter) || post.getBody().toLowerCase().contains(filter))
                .collect(Collectors.toList());
    }



    public ObservableList<Post> updateFilteredPosts() {
        ObservableList<Post> filteredPosts = FXCollections.observableArrayList();
        filteredPosts.addAll(getFilteredPosts());
        System.out.println("Filtered posts In Vm: " + filteredPosts.size());
        return filteredPosts;
    }


    public ListProperty<Post> filteredPostsProperty() {
        return filteredPosts;
    }

    public List<Post> getPagedPosts() {
        List<Post> filteredPosts = posts;
        int fromIndex = currentPage.get() * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, filteredPosts.size());
        return filteredPosts.subList(fromIndex, toIndex);
    }
}
