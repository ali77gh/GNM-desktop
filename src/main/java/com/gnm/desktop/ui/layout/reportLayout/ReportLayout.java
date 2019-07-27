package com.gnm.desktop.ui.layout.reportLayout;

import com.gnm.desktop.data.repo.Report;
import javafx.geometry.Insets;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ReportLayout extends VBox implements Report.ReportCallback {

    private StackPane lastMonthCard;
    private StackPane lastYearCard;

    public ReportLayout() {

        var layout = new VBox(60);
        layout.setPadding(new Insets(60, 60, 60, 60));

        lastMonthCard = new StackPane();
        lastMonthCard.getStyleClass().add("reportCard");

        lastYearCard = new StackPane();
        lastYearCard.getStyleClass().add("reportCard");


        layout.getChildren().addAll(lastMonthCard, lastYearCard);//add others
        var scrollView = new ScrollPane(layout);
        scrollView.setFitToWidth(true);
        scrollView.getStyleClass().add("cbsCard_scrollPane");
        //scrollView.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        layout.getStyleClass().add("invisible");
        getStyleClass().add("invisible");
        getChildren().add(scrollView);

        Report.setCallback(this);
        Report.RefreshAll();
    }

    @Override
    public void lastMonthSell(StackedAreaChart stack) {
        if (lastMonthCard.getChildren().size() != 0)
            lastMonthCard.getChildren().removeAll();

        lastMonthCard.getChildren().add(stack);
    }

    @Override
    public void lastYearSell(StackedAreaChart stack) {
        if (lastYearCard.getChildren().size() != 0)
            lastYearCard.getChildren().removeAll();

        lastYearCard.getChildren().add(stack);
    }

    @Override
    public void todaySell(int sellSum) {

    }

    @Override
    public void hourSell(int[] hours) {

    }

    @Override
    public void countBaseVSTimeBase(int timeBaseIncome, int countBaseIncome) {

    }

    @Override
    public void timeBaseServices(int[] services) {

    }

    @Override
    public void countBaseServices(int[] services) {

    }

    @Override
    public void top5Games(int[] usersCount) {

    }
}
