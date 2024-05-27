package com.github.stephenwanjala.postfx;

import com.github.stephenwanjala.postfx.domain.model.Post;
import com.github.stephenwanjala.postfx.util.PdfExporter;
import com.github.stephenwanjala.postfx.util.PrinterUtil;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import com.github.stephenwanjala.postfx.viewmodel.PostViewModel;

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

    private PostViewModel viewModel;

    public void initialize() {
        viewModel = new PostViewModel();

        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        bodyCol.setCellValueFactory(cellData -> cellData.getValue().bodyProperty());

        filterField.textProperty().bindBidirectional(viewModel.filterTextProperty());
        filterField.textProperty().addListener((obs, oldText, newText) -> updatePagination());
        viewModel.getPosts().addListener((ListChangeListener<Post>) change -> {
            updatePagination();
            tableView.getItems().setAll(viewModel.getPagedPosts());
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        });

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> updateTableView(newIndex.intValue()));

        updatePagination();
    }

    private void updatePagination() {
        int pageCount = (viewModel.getPosts().size() / 10) + 1;
        System.out.println("Page count: " + pageCount);
        System.out.println("Filtered posts: " + viewModel.updateFilteredPosts().size());
        pagination.setPageCount(pageCount);
        updateTableView(0);
    }

    private void updateTableView(int pageIndex) {
        viewModel.currentPageProperty().set(pageIndex);
        tableView.getItems().setAll(viewModel.getPagedPosts());
    }

    @FXML
    private void handleExportPdf() {
        try {
            PdfExporter.exportToPdf(viewModel.getPosts(), "posts.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePrint() {
        PrinterUtil.print(tableView);
    }
}