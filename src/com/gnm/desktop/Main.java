package com.gnm.desktop;

import com.gnm.desktop.ui.layout.MainPanel;
import com.gnm.desktop.ui.layout.RightMenu;
import com.gnm.desktop.ui.layout.RightPanel;
import com.gnm.desktop.ui.view.HBoxRTL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements RightMenu.RightMenuCallback {

    @Override
    public void start(Stage primaryStage) {

        HBoxRTL root = new HBoxRTL();

        MainPanel mainPanel = new MainPanel();
        RightPanel rightpanel = new RightPanel();
        RightMenu rightMenu = new RightMenu(this);

        root.getChildren().addAll(mainPanel,rightpanel,rightMenu);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onMonitorSelect() {

    }

    @Override
    public void onCostSelect() {

    }

    @Override
    public void onGamesSelect() {

    }

    @Override
    public void onCustomerSelect() {

    }

    @Override
    public void onSettingsSelect() {

    }

    @Override
    public void onAboutSelect() {

    }
}
