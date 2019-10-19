package com.gnm.desktop;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.SQLiteDatabase;
import com.gnm.desktop.ui.AppCSS;
import com.gnm.desktop.ui.layout.PreLoader;
import com.gnm.desktop.ui.layout.mainpanel.MainPanel;
import com.gnm.desktop.ui.layout.rightMenu.Items;
import com.gnm.desktop.ui.layout.rightMenu.RightMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application implements Items.RightMenuCallback {

    private static final int minWindowWidth = 1280;//in pixels
    private static final int minWindowHeight = 720;//in pixels

    private MainPanel mainPanel;

    //this scene's layout
    public static AnchorPane root;
    private static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        DB.Init();

        Scene scene = new Scene(getRoot(), minWindowWidth, minWindowHeight);

        primaryStage.setTitle("GNM");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            SQLiteDatabase.close();
        });

        AppCSS.load(root);

        mainStage = primaryStage;
    }

    private Pane getRoot() {

        //main scene nodes
        root = new AnchorPane();

        RightMenu rightMenu = new RightMenu(this);
        AnchorPane.setTopAnchor(rightMenu, 40.0);
        AnchorPane.setRightAnchor(rightMenu, 0.0);
        AnchorPane.setBottomAnchor(rightMenu, 0.0);

        rightMenu.getStyleClass().add("right-menu");

        mainPanel = new MainPanel();
        AnchorPane.setTopAnchor(mainPanel, 0.0);
        AnchorPane.setRightAnchor(mainPanel, 0.0);
        AnchorPane.setBottomAnchor(mainPanel, 0.0);
        AnchorPane.setLeftAnchor(mainPanel, 0.0);

        //preLoader
        var preLoader = new PreLoader();
        AnchorPane.setTopAnchor(preLoader, 0.0);
        AnchorPane.setRightAnchor(preLoader, 0.0);
        AnchorPane.setBottomAnchor(preLoader, 0.0);
        AnchorPane.setLeftAnchor(preLoader, 0.0);


        //adding nodes to main scene
        root.getChildren().add(mainPanel);
        root.getChildren().add(rightMenu);
        root.getChildren().add(preLoader);

        return root;
    }

    @Override
    public void onMenuSelect(int menu) {
        mainPanel.setLayout(menu);
    }

    public static Stage getStage(){
        return mainStage;
    }
}
