package com.gnm.desktop.ui.layout.mainpanel;

import com.gnm.desktop.res.css.CSSStyler;
import com.gnm.desktop.ui.layout.*;
import com.gnm.desktop.ui.layout.priceslayout.PricesLayout;
import com.gnm.desktop.ui.layout.rightMenu.Items;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


//mainpanel that stores toolbar and main layouts which changes by selecting rightmenu items
public class MainPanel extends AnchorPane {

    private AnchorPane mainPane;
    private Toolbar toolbar;

    private HomeLayout homeLayout;
    private MonitorLayout monitorLayout;
    private PricesLayout pricesLayout;
    private GamesLayout gamesLayout;
    private CustomerLayout customerLayout;
    private SettingsLayout settingsLayout;
    private AboutUsLayout aboutUsLayout;

    public MainPanel() {

        //load css files
        getStylesheets().add(CSSStyler.get("BackgroundColor.css"));

        setPrefHeight(Double.MAX_VALUE);


        toolbar=new Toolbar();
        AnchorPane.setTopAnchor(toolbar,0.0);
        AnchorPane.setLeftAnchor(toolbar,0.0);
        AnchorPane.setRightAnchor(toolbar,0.0);


        mainPane=new AnchorPane();
        AnchorPane.setTopAnchor(mainPane,40.0);
        AnchorPane.setLeftAnchor(mainPane,0.0);
        AnchorPane.setRightAnchor(mainPane,240.0);
        AnchorPane.setBottomAnchor(mainPane,0.0);
        //mainPane style
        mainPane.getStyleClass().add("mainPanel");


        getChildren().add(toolbar);
        getChildren().add(mainPane);

        //defining layouts and put them in list for adding position by setAnchor method
        List<Pane> layouts=new ArrayList<>();
        homeLayout=new HomeLayout();
        layouts.add(homeLayout);
        monitorLayout=new MonitorLayout();
        layouts.add(monitorLayout);
        pricesLayout=new PricesLayout();
        layouts.add(pricesLayout);
        gamesLayout=new GamesLayout();
        layouts.add(gamesLayout);
        customerLayout=new CustomerLayout();
        layouts.add(customerLayout);
        settingsLayout=new SettingsLayout();
        layouts.add(settingsLayout);
        aboutUsLayout=new AboutUsLayout();
        layouts.add(aboutUsLayout);

        setAnchor(layouts);


        //default layout
        mainPane.getChildren().add(homeLayout);


    }

    public void setLayout(int menu) {

        //clear current layout
        mainPane.getChildren().remove(0);

        //load new layout
        switch (menu){
            case Items.HOME:
                mainPane.getChildren().add(homeLayout);
                break;
            case Items.MONITOR:
                mainPane.getChildren().add(monitorLayout);
                break;
            case Items.PRICES:
                mainPane.getChildren().add(pricesLayout);
                break;
            case Items.GAMES:
                mainPane.getChildren().add(gamesLayout);
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
    }

    private void setAnchor(List<Pane> name){

        for (Pane p:name) {
            AnchorPane.setTopAnchor(p, 0.0);
            AnchorPane.setLeftAnchor(p, 0.0);
            AnchorPane.setRightAnchor(p, 0.0);
            AnchorPane.setBottomAnchor(p, 0.0);
        }
    }
}
