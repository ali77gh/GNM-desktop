package com.gnm.desktop.ui.layout.mainpanel;

import com.gnm.desktop.core.ThreadHelper;
import com.gnm.desktop.core.dateTime.JalaliDateTime;
import com.gnm.desktop.core.dateTime.Translator;
import com.gnm.desktop.data.DB;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

class Toolbar extends AnchorPane {

    Toolbar() {

        setPrefHeight(40);
        getStyleClass().add("toolbar");

        getChildren().addAll(getClock(), ToolbarDate.getDate(), ToolbarTodayIncome.getTodaySell());
    }

    private Label getClock() {
        //clock
        Label lbl = new Label();
        lbl.setPrefWidth(240);
        lbl.setTextFill(Paint.valueOf("#fff"));
        lbl.setAlignment(Pos.CENTER);
        lbl.getStyleClass().add("toolbar_miniToolbar");
        AnchorPane.setTopAnchor(lbl, 0.0);
        AnchorPane.setRightAnchor(lbl, 0.0);
        AnchorPane.setBottomAnchor(lbl, 0.0);


        //second added just for debuging todo remove second in release
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            JalaliDateTime jalaliDateTime = JalaliDateTime.Now();
            String time = jalaliDateTime.getTimePersianString(true);
            lbl.setText(time);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return lbl;
    }

    private static class ToolbarDate {
        private static Label dateLabel;

        static Label getDate() {
            //clock
            dateLabel = new Label();
            dateLabel.setTextFill(Paint.valueOf("#fff"));
            dateLabel.setAlignment(Pos.CENTER);
            AnchorPane.setTopAnchor(dateLabel, 0.0);
            AnchorPane.setLeftAnchor(dateLabel, 16.0);
            AnchorPane.setBottomAnchor(dateLabel, 0.0);


            //second added just for debuging todo remove second in release
            Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(10), event -> refreshDate()));
            refreshDate();

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            return dateLabel;
        }

        private static void refreshDate() {
            JalaliDateTime jdt = JalaliDateTime.Now();

            String dateInPersian = "";
            dateInPersian += jdt.getPersianDayOfWeek() + " ";
            dateInPersian += Translator.toPersian(jdt.getDay()) + " ";
            dateInPersian += jdt.getPersianMonth();
            dateLabel.setText(dateInPersian);
        }
    }

    public static class ToolbarTodayIncome {

        private static Label todayIncome;
        private static int income;

        private static Label getTodaySell() {

            income = DB.SellLogs.getTodayIncome();

            //clock
            todayIncome = new Label();
            todayIncome.setTextFill(Paint.valueOf("#fff"));
            todayIncome.setAlignment(Pos.CENTER);
            AnchorPane.setTopAnchor(todayIncome, 0.0);
            AnchorPane.setRightAnchor(todayIncome, 256.0);
            AnchorPane.setBottomAnchor(todayIncome, 0.0);

            refresh();

            return todayIncome;
        }

        public static void Increase(int howMatch) {
            income += howMatch;
            refresh();
        }

        public static void Decrease(int howMatch) {
            income -= howMatch;
            refresh();
        }

        private static void refresh() {
            var fadeOut = new FadeTransition();
            fadeOut.setNode(todayIncome);
            fadeOut.setDuration(Duration.millis(200));
            fadeOut.setToValue(0);
            fadeOut.play();

            ThreadHelper.delayInUI(250, () -> {
                todayIncome.setText(" درآمد امروز: " + income + "T");
                var fadeIn = new FadeTransition();
                fadeIn.setNode(todayIncome);
                fadeIn.setDuration(Duration.millis(200));
                fadeIn.setToValue(1);
                fadeIn.play();
            });
        }

    }

}
