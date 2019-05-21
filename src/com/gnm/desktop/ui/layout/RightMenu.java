package com.gnm.desktop.ui.layout;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RightMenu extends VBox {

    private List<Button> btns;

    private String style =
            "-fx-background-color:#555;";

    public RightMenu(RightMenuCallback callback) {
        super(10); // spacing

        Button monitor = new Button("دخل و خرج");
        Button costs = new Button("قیمت ها");
        Button games = new Button("بازی ها");
        Button customers = new Button("مشتری ها");
        Button settings = new Button("تنظیمات");
        Button about = new Button("درباره ی ما");

        btns = new ArrayList<>();
        btns.add(monitor);
        btns.add(costs);
        btns.add(games);
        btns.add(customers);
        btns.add(settings);
        btns.add(about);

        setStyle(style);

        setupMaxWith();
        setPadding(new Insets(5, 5, 5, 5));

        monitor.setOnMouseClicked(mouseEvent -> {
            SetSelect(monitor);
            callback.onMonitorSelect();
        });

        costs.setOnMouseClicked(mouseEvent -> {
            SetSelect(costs);
            callback.onCostSelect();
        });

        games.setOnMouseClicked(mouseEvent -> {
            SetSelect(games);
            callback.onGamesSelect();
        });

        customers.setOnMouseClicked(mouseEvent -> {
            SetSelect(customers);
            callback.onCustomerSelect();
        });

        settings.setOnMouseClicked(mouseEvent -> {
            SetSelect(settings);
            callback.onSettingsSelect();
        });

        about.setOnMouseClicked(mouseEvent -> {
            SetSelect(about);
            callback.onAboutSelect();
        });

        getChildren().addAll(monitor, costs, games, customers, settings, about);
    }

    private void setupMaxWith() {
        for (Button button : btns) {
            button.setMaxWidth(Double.MAX_VALUE);
            setVgrow(button, Priority.ALWAYS);
        }
    }

    private void ClearSelect() {
        for (Button button : btns) {
            //todo un select
        }
    }

    private void SetSelect(Button button) {

        ClearSelect();

        //todo select effect
    }

    public interface RightMenuCallback {
        void onMonitorSelect();

        void onCostSelect();

        void onGamesSelect();

        void onCustomerSelect();

        void onSettingsSelect();

        void onAboutSelect();
    }
}
