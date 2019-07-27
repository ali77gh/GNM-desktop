package com.gnm.desktop.ui.layout.mainpanel;

import com.gnm.desktop.core.ThreadHelper;
import com.gnm.desktop.ui.layout.AboutUsLayout;
import com.gnm.desktop.ui.layout.HomeLayout;
import com.gnm.desktop.ui.layout.PreLoader;
import com.gnm.desktop.ui.layout.SettingsLayout;
import com.gnm.desktop.ui.layout.customerlayout.CustomerLayout;
import com.gnm.desktop.ui.layout.priceslayout.PricesLayout;
import com.gnm.desktop.ui.layout.reportLayout.ReportLayout;
import com.gnm.desktop.ui.layout.rightMenu.Items;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


//mainpanel that stores toolbar and main layouts which changes by selecting rightmenu items
public class MainPanel extends AnchorPane {

    private AnchorPane mainPane;
    private Toolbar toolbar;

    private HomeLayout homeLayout;
    private ReportLayout reportLayout;
    private PricesLayout pricesLayout;
    private CustomerLayout customerLayout;
    private SettingsLayout settingsLayout;
    private AboutUsLayout aboutUsLayout;

    public MainPanel() {

        setPrefHeight(Double.MAX_VALUE);


        toolbar = new Toolbar();
        AnchorPane.setTopAnchor(toolbar, 0.0);
        AnchorPane.setLeftAnchor(toolbar, 0.0);
        AnchorPane.setRightAnchor(toolbar, 0.0);


        mainPane = new AnchorPane();
        AnchorPane.setTopAnchor(mainPane, 40.0);
        AnchorPane.setLeftAnchor(mainPane, 0.0);
        AnchorPane.setRightAnchor(mainPane, 240.0);
        AnchorPane.setBottomAnchor(mainPane, 0.0);
        //mainPane style
        mainPane.getStyleClass().add("mainPanel_mainPane");


        getChildren().add(toolbar);
        getChildren().add(mainPane);

        //defining layouts and put them in list for adding position by setAnchor method
        List<Pane> layouts = new ArrayList<>();
        homeLayout = new HomeLayout();
        layouts.add(homeLayout);
        reportLayout = new ReportLayout();
        layouts.add(reportLayout);
        pricesLayout = new PricesLayout();
        layouts.add(pricesLayout);
        customerLayout = new CustomerLayout();
        layouts.add(customerLayout);
        settingsLayout = new SettingsLayout();
        layouts.add(settingsLayout);
        aboutUsLayout = new AboutUsLayout();
        layouts.add(aboutUsLayout);

        setAnchor(layouts);
        RenderAll(layouts);
    }

    private void RenderAll(List<Pane> layouts) {
        new Thread(() -> {
            for (int i = layouts.size() - 1; i >= 0; i--) {
                Pane p = layouts.get(i);

                Platform.runLater(() -> {
                    try {
                        mainPane.getChildren().remove(0);
                    } catch (IndexOutOfBoundsException e) { }

                    mainPane.getChildren().add(p);
                    PreLoader.ImReady();
                });
                ThreadHelper.sleep(500);
            }
        }).start();
    }

    public void setLayout(int menu) {

        //clear current layout
        mainPane.getChildren().remove(0);

        //load new layout
        switch (menu) {
            case Items.HOME:
                mainPane.getChildren().add(homeLayout);
                break;
            case Items.MONITOR:
                mainPane.getChildren().add(reportLayout);
                break;
            case Items.PRICES:
                mainPane.getChildren().add(pricesLayout);
                break;
            case Items.CUSTOMER:
                mainPane.getChildren().add(customerLayout);
                break;
            case Items.SETTINGS:
                mainPane.getChildren().add(settingsLayout);
                break;
            case Items.ABOUT_US:
                mainPane.getChildren().add(aboutUsLayout);
                break;
            default:
                throw new RuntimeException("invalid menu:" + menu);
        }

        fadeId(mainPane.getChildren().get(0));
    }

    private void setAnchor(List<Pane> name) {

        for (Pane p : name) {
            AnchorPane.setTopAnchor(p, 0.0);
            AnchorPane.setLeftAnchor(p, 0.0);
            AnchorPane.setRightAnchor(p, 0.0);
            AnchorPane.setBottomAnchor(p, 0.0);
        }
    }

    private void fadeId(Node node) {

        var fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(node);
        fade.play();
    }
}
