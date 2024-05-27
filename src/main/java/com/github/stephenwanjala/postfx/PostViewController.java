package com.github.stephenwanjala.postfx;

import com.github.stephenwanjala.postfx.domain.model.Post;
import com.github.stephenwanjala.postfx.util.PrinterUtil;
import com.github.stephenwanjala.postfx.viewmodel.PostViewModel;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PostViewController {
    @FXML
    private TextField filterField;
    @FXML
    private TableView<Post> tableView;
    @FXML
    private TableColumn<Post, Integer> idCol;
    @FXML
    private TableColumn<Post, String> titleCol;
    @FXML
    private TableColumn<Post, String> bodyCol;
    @FXML
    private Pagination pagination;
    @FXML
    private ProgressIndicator progressIndicator;

    private PostViewModel viewModel;

    public void initialize() {
        progressIndicator.setVisible(true);
        viewModel = new PostViewModel();
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        bodyCol.setCellValueFactory(cellData -> cellData.getValue().bodyProperty());
        filterField.textProperty().bindBidirectional(viewModel.filterTextProperty());
        filterField.textProperty().addListener((obs, oldText, newText) -> updatePagination());
        viewModel.getPosts().addListener((ListChangeListener<Post>) change -> {
            updatePagination();
            Platform.runLater(() -> tableView.getItems().setAll(viewModel.getPagedPosts()));
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        });

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> updateTableView(newIndex.intValue()));

        updatePagination();
    }

    private void updatePagination() {
        Platform.runLater(() -> {
            progressIndicator.setVisible(true);
            int pageCount = (viewModel.getPosts().size() / 10) + 1;
            System.out.println("Page count: " + pageCount);
            System.out.println("Filtered posts: " + viewModel.updateFilteredPosts().size());
            pagination.setPageCount(pageCount);
            updateTableView(0);
            progressIndicator.setVisible(false);
        });
    }

    private void updateTableView(int pageIndex) {
        Platform.runLater(() -> {
            progressIndicator.setVisible(true);
            viewModel.currentPageProperty().set(pageIndex);
            tableView.getItems().setAll(viewModel.getPagedPosts());
            progressIndicator.setVisible(false);
        });
    }


    @FXML
    private void handleExportPdf() {
        progressIndicator.setVisible(true);

    }

    @FXML
    private void handlePrint() {
        progressIndicator.setVisible(true);
        PrinterUtil.print(tableView);
        progressIndicator.setVisible(false);
    }
}