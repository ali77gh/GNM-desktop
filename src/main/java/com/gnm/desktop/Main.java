package com.gnm.desktop;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.ui.layout.mainpanel.MainPanel;
import com.gnm.desktop.ui.layout.rightMenu.Items;
import com.gnm.desktop.ui.layout.rightMenu.RightMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application implements Items.RightMenuCallback {

    private static final int minWindowWidth = 900;//in pixels
    private static final int minWindowHeight = 500;//in pixels

    private MainPanel mainPanel;
    private RightMenu rightMenu;

    //this scene's layout
    private AnchorPane root;

    @Override
    public void start(Stage primaryStage) {

        DB.Init();

        Scene scene=new Scene(getRoot(), minWindowWidth, minWindowHeight);


        primaryStage.setTitle("GNM");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private Pane getRoot() {

        //main scene nodes
        root = new AnchorPane();
        mainPanel = new MainPanel();
        rightMenu = new RightMenu(this);


        rightMenu.getStyleClass().add("right-menu");


        AnchorPane.setTopAnchor(rightMenu, 0.0);
        AnchorPane.setRightAnchor(rightMenu, 0.0);
        AnchorPane.setBottomAnchor(rightMenu, 0.0);


        AnchorPane.setTopAnchor(mainPanel, 0.0);
        AnchorPane.setRightAnchor(mainPanel, 240.0);
        AnchorPane.setBottomAnchor(mainPanel, 0.0);
        AnchorPane.setLeftAnchor(mainPanel, 0.0);



        //adding nodes to main scene
        root.getChildren().add(mainPanel);
        root.getChildren().add(rightMenu);

        return root;
    }

    @Override
    public void onMenuSelect(int menu) {
        mainPanel.setLayout(menu);
    }
}
