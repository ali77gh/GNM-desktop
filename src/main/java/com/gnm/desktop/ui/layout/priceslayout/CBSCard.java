package com.gnm.desktop.ui.layout.priceslayout;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.CountBaseAutoComplete;
import com.gnm.desktop.ui.dialog.AddServiceDialog;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class CBSCard extends AnchorPane {

    private static AnchorPane cbsToolbar;
    private static VBox cbsList;
    private ScrollPane scrollPane;
    private static TextField txtSearch;
    private static Label notFound;

    private static final int width = 450;
    private static final int height = 346;
    private static final int toolbar_height = 35;

    CBSCard() {

        setPadding(new Insets(15, 10, 20, 15));
        setPrefSize(width, height);
        getStyleClass().add("cbsCard");

        cbsToolbar = new AnchorPane();
        cbsToolbar.setPrefHeight(toolbar_height);
        cbsToolbar.getStyleClass().add("cbsCard_cbsToolbar");
        AnchorPane.setTopAnchor(cbsToolbar, 0.0);
        AnchorPane.setLeftAnchor(cbsToolbar, 0.0);
        AnchorPane.setRightAnchor(cbsToolbar, 0.0);

        HBox searchBar = new HBox(2);
        searchBar.getStyleClass().add("cbsCard_searchBar");
        searchBar.setPadding(new Insets(0, 10, 0, 10));
        searchBar.setPrefSize(200, 30);
        searchBar.setAlignment(Pos.CENTER);
        searchBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        AnchorPane.setLeftAnchor(searchBar, 50.0);

        Label searchIcon = new Label();
        searchIcon.getStyleClass().add("searchIcon");
        searchIcon.setPrefSize(18, 20);


        txtSearch = new TextField();
        txtSearch.getStyleClass().add("cbsCard_txtSearch");
        txtSearch.setPrefWidth(182);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> Refresh());


        searchBar.getChildren().addAll(searchIcon, txtSearch);

        Label plusIcon = new Label();
        plusIcon.getStyleClass().add("plusIcon");
        plusIcon.setPrefWidth(19);
        AnchorPane.setTopAnchor(plusIcon, 6.0);
        AnchorPane.setRightAnchor(plusIcon, 18.0);
        AnchorPane.setBottomAnchor(plusIcon, 10.0);
        plusIcon.setOnMouseClicked(event -> new AddServiceDialog(DB.CountBaseAutoComplete));

        cbsToolbar.getChildren().addAll(searchBar, plusIcon);

        cbsList = new VBox(1);


        scrollPane = new ScrollPane(cbsList);
        scrollPane.setPadding(new Insets(10, 0, 0, 0));
        scrollPane.getStyleClass().add("cbsCard_scrollPane");
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(height - toolbar_height);
        AnchorPane.setTopAnchor(scrollPane, toolbar_height + 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        // not found view
        notFound = new Label("چیزی یافت نشد :(");
        notFound.setAlignment(Pos.CENTER);
        notFound.getStyleClass().add("dialogText");
        AnchorPane.setTopAnchor(notFound, 30.0);
        AnchorPane.setLeftAnchor(notFound, 0.0);
        AnchorPane.setRightAnchor(notFound, 0.0);
        AnchorPane.setBottomAnchor(notFound, 0.0);

        Refresh();
        getChildren().addAll(cbsToolbar, scrollPane, notFound);
    }

    public static void Refresh() {

        new Thread(() -> {

            List<CountBaseAutoComplete> list;
            if (txtSearch.getText().isEmpty()) {
                list = DB.CountBaseAutoComplete.getAll();
            } else {
                list = DB.CountBaseAutoComplete.getWithPrefix(txtSearch.getText());
            }

            Platform.runLater(() -> {
                cbsList.getChildren().clear();
                if (!list.isEmpty()) {
                    notFound.setVisible(false);
                    for (int i = 0; i < list.size(); i++) {
                        if (i % 2 == 0) {
                            cbsList.getChildren().add(new CBSListItem(i + 1, list.get(i), true));
                        } else {
                            cbsList.getChildren().add(new CBSListItem(i + 1, list.get(i), false));
                        }
                    }
                } else {
                    notFound.setVisible(true);
                }
            });

        }).start();

    }

}
