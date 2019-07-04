package com.gnm.desktop;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.ui.layout.MainPanel;
import com.gnm.desktop.ui.layout.RightMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application implements RightMenu.RightMenuCallback {

    private static final int minWindowWidth = 900;//in pixels
    private static final int minWindowHeight = 500;//in pixels

    private MainPanel mainPanel;
    private RightMenu rightMenu;

    @Override
    public void start(Stage primaryStage) {

        DB.Init();

        primaryStage.setTitle("GNM");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(getRoot(), minWindowWidth, minWindowHeight));
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private Pane getRoot(){

        BorderPane root = new BorderPane();

        mainPanel = new MainPanel();
        rightMenu = new RightMenu(this);
        root.setRight(rightMenu);
        root.setCenter(mainPanel);

        return root;
    }

    @Override
    public void onMenuSelect(int menu) {
        mainPanel.setLayout(menu);
    }
}
