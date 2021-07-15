package com.gnm.desktop.ui;

import com.gnm.desktop.ui.view.HoveredThresholdNode;
import javafx.geometry.NodeOrientation;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ChartGen {

    private static final String EMPTY_ERROR = "(داده ی کافی یافت نشد)";

    public static StackedAreaChart generateStack(String title, String xLabel, String yLabel, String dataLable, List<XYChart.Data> data) {

        var max = 0;
        if (data == null) {
            data = new ArrayList<>();
            title += " " + EMPTY_ERROR;
        }
        for (var i : data) {
            if (((int) i.getYValue()) > max)
                max = (int) i.getYValue();
        }
        max *= 1.2; // ماکزیمم نچسبه به بالا

        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(0, upRound1000(max), upRound1000(max) / 6);
        xaxis.setLabel(xLabel);
        yaxis.setLabel(yLabel);

        StackedAreaChart bar = new StackedAreaChart<>(xaxis, yaxis);
        bar.setTitle(title);

        XYChart.Series dataSet = new XYChart.Series<>();
        for (var i : data) {
            i.setNode(getLabel(i.getYValue()));
            dataSet.getData().add(i);
        }


        //adding series1 to the stackedareachart
        bar.getData().add(dataSet);
        dataSet.setName(dataLable);

        bar.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        return bar;
    }

    public static BarChart generateBar(String title, String xLabel, String yLabel, String dataLable, List<XYChart.Data> data) {

        var max = 0;
        if (data == null) {
            data = new ArrayList<>();
            title += " " + EMPTY_ERROR;
        }
        for (var i : data) {
            if (((int) i.getYValue()) > max)
                max = (int) i.getYValue();
        }
        max += 1;

        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(0, max, max / 5);
        xaxis.setLabel(xLabel);
        yaxis.setLabel(yLabel);

        BarChart bar = new BarChart<>(xaxis, yaxis);
        bar.setTitle(title);

        XYChart.Series dataSet = new XYChart.Series<>();
        for (var i : data) {
            i.setNode(getLabel(i.getYValue()));
            dataSet.getData().add(i);
        }


        //adding series1 to the stackedareachart
        bar.getData().add(dataSet);
        dataSet.setName(dataLable);
        bar.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        return bar;
    }

    public static PieChart generatePie(String title, List<PieChart.Data> data) {

        var pie = new PieChart();
        if (data == null) {
            data = new ArrayList<>();
            title += " " + EMPTY_ERROR;
        }
        for (var i : data) {
            pie.getData().add(i);
        }
        pie.setTitle(title);
        pie.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        return pie;
    }

    private static int upRound1000(int number) {
        number -= number % 10;
        number -= number % 100;
        number -= number % 1000;
        return number + 1000;
    }

    private static Pane getLabel(Object value) {
        return new HoveredThresholdNode((int) value);
    }

}