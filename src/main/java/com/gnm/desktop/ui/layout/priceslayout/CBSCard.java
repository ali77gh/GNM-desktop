package com.gnm.desktop.ui.layout.priceslayout;

import java.util.List;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.CountBaseAutoComplete;
import com.gnm.desktop.res.css.CSSStyler;

import com.gnm.desktop.ui.dialog.AddServiceDialog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CBSCard extends AnchorPane {

    private static AnchorPane cbsToolbar;
    private static VBox cbsList;
    private ScrollPane scrollPane;
    public static TextField txtSearch;

    private final int width = 450;
    private final int height = 400;
    private final int toolbar_height = 35;

    public CBSCard(){
        getStylesheets().add(CSSStyler.get("app.css"));

        setPadding(new Insets(8,10,20,15));
        setPrefSize(width, height);
        getStyleClass().add("cbsCard");

        cbsToolbar=new AnchorPane();
        cbsToolbar.setPrefHeight(toolbar_height);
        cbsToolbar.getStyleClass().add("cbsCard_cbsToolbar");
        AnchorPane.setTopAnchor(cbsToolbar, 0.0);
        AnchorPane.setLeftAnchor(cbsToolbar, 0.0);
        AnchorPane.setRightAnchor(cbsToolbar, 0.0);

        HBox searchBar=new HBox(2);
        searchBar.getStyleClass().add("cbsCard_searchBar");
        searchBar.setPadding(new Insets(0,10,0,10));
        searchBar.setPrefSize(200, 30);
        searchBar.setAlignment(Pos.CENTER);
        //NodeOrientation is Right to Left so all node obey this
        searchBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        AnchorPane.setLeftAnchor(searchBar, 50.0);

        Label searchIcon=new Label();
        searchIcon.getStyleClass().add("searchIcon");
        searchIcon.setPrefSize(18, 20);

        
        txtSearch=new TextField();
        txtSearch.getStyleClass().add("cbsCard_txtSearch");
        txtSearch.setPrefWidth(182);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                makeCBSItems(DB.CountBaseAutoComplete.getAll());
            }else {
                makeCBSItems(DB.CountBaseAutoComplete.getWithPrefix(newValue, 10));
            }
        });


        searchBar.getChildren().addAll(searchIcon,txtSearch);

        Label plusIcon=new Label();
        plusIcon.getStyleClass().add("plusIcon");
        plusIcon.setPrefWidth(19);
        AnchorPane.setTopAnchor(plusIcon,6.0);
        AnchorPane.setRightAnchor(plusIcon,18.0);
        AnchorPane.setBottomAnchor(plusIcon,10.0);
        plusIcon.setOnMouseClicked(event -> new AddServiceDialog(DB.CountBaseAutoComplete));

        cbsToolbar.getChildren().addAll(searchBar,plusIcon);

        cbsList=new VBox(1);


        scrollPane=new ScrollPane(cbsList);
        scrollPane.getStyleClass().add("cbsCard_scrollPane");
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(height-toolbar_height);
        AnchorPane.setTopAnchor(scrollPane, toolbar_height+0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        
        makeCBSItems(DB.CountBaseAutoComplete.getAll());
        getChildren().addAll(cbsToolbar,scrollPane);
    }

    public static void makeCBSItems(List<CountBaseAutoComplete> list){
        cbsList.getChildren().clear();


        if (!list.isEmpty()){
            for (int i=0;i<list.size();i++) {
                if(i%2==0){
                    cbsList.getChildren().add(new CBSListItem(i+1,list.get(i),true));
                }else{
                    cbsList.getChildren().add(new CBSListItem(i+1,list.get(i),false));
                }
            }
        }

    }

}
