package com.gnm.desktop.ui.layout.reportLayout;

import com.gnm.desktop.core.AppRefresh;
import com.gnm.desktop.data.repo.Report;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static com.gnm.desktop.ui.layout.rightMenu.Items.MONITOR;

public class ReportLayout extends VBox implements Report.ReportCallback {

    private StackPane lastMonthCard;
    private StackPane lastYearCard;
    private StackPane hourCard;
    private HBox firstPieSet;
    private HBox secondPieSet;

    public ReportLayout() {

        var layout = new VBox(60);
        layout.setPadding(new Insets(60, 60, 60, 60));

        lastMonthCard = new StackPane();
        lastMonthCard.getStyleClass().add("reportCard");

        lastYearCard = new StackPane();
        lastYearCard.getStyleClass().add("reportCard");

        hourCard = new StackPane();
        hourCard.getStyleClass().add("reportCard");

        firstPieSet = new HBox();
        firstPieSet.setAlignment(Pos.CENTER);
        firstPieSet.getStyleClass().add("reportCard");

        secondPieSet = new HBox();
        secondPieSet.setAlignment(Pos.CENTER);
        secondPieSet.getStyleClass().add("reportCard");

        layout.getChildren().addAll(lastMonthCard, lastYearCard, hourCard, firstPieSet, secondPieSet);//add others
        var scrollView = new ScrollPane(layout);
        scrollView.setFitToWidth(true);
        scrollView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        scrollView.getStyleClass().add("report_scrollPane");

        layout.getStyleClass().add("invisible");
        getChildren().add(scrollView);

        Report.Init(this);
        Report.RefreshAll();

        AppRefresh.registerLayout(MONITOR, () -> {
            Report.RefreshAll();
        });
    }

    @Override
    public void lastMonthSell(StackedAreaChart stack) {

        lastMonthCard.getChildren().clear();
        lastMonthCard.getChildren().add(stack);
    }

    @Override
    public void lastYearSell(StackedAreaChart stack) {
        lastYearCard.getChildren().clear();
        lastYearCard.getChildren().add(stack);
    }

    @Override
    public void hourSell(BarChart barChart) {
        hourCard.getChildren().clear();
        hourCard.getChildren().add(barChart);
    }

    @Override
    public void firstPieSet(PieChart countBaseVSTimeBase, PieChart topGames) {
        firstPieSet.getChildren().clear();
        firstPieSet.getChildren().addAll(countBaseVSTimeBase, topGames);
    }

    @Override
    public void secondPieSet(PieChart timeBaseServices, PieChart countBaseServices) {
        secondPieSet.getChildren().clear();
        secondPieSet.getChildren().addAll(timeBaseServices, countBaseServices);
    }
}
