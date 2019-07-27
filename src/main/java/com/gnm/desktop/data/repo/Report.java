package com.gnm.desktop.data.repo;

import com.gnm.desktop.core.ThreadHelper;
import com.gnm.desktop.res.css.CSSStyler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

public class Report {

    private static ReportCallback cb;

    public static void setCallback(ReportCallback callback) {
        Report.cb = callback;
    }

    public static void RefreshAll() {

//        var pager = new DBPager();
//
//        pager.setCallBack(new DBPager.PagerCallBack() {
//            @Override
//            public void callback(Object o) {
//                var selllog = ((SellLog)o);
//
//            }
//
//            @Override
//            public void onEnd() {
//
//            }
//        });
//
//        DB.SellLogs.getWithPager(pager);
//        pager.start();
        ThreadHelper.delayInUI(1000, () -> {
            cb.lastMonthSell(generateDays());
            cb.lastYearSell(generateDays());
        });

    }

    public static void RefreshTodaySell() {

    }

    private static StackedAreaChart generateDays() {

        // TODO Auto-generated method stub
        // Configuring Xaxis and Yaxis
        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(0, 700, 100);
        xaxis.setLabel("Month");
        yaxis.setLabel("Sales Value (lacs)");

        //Creating StackedAreaChart
        StackedAreaChart stack = new StackedAreaChart(xaxis, yaxis);
        stack.setTitle("Sales Comparison between the year 2016 and 2017");

        //Configuring Series 1
        XYChart.Series year1 = new XYChart.Series<>();
        year1.getData().add(new XYChart.Data("1", 140));
        year1.getData().add(new XYChart.Data("2", 110));
        year1.getData().add(new XYChart.Data("3", 125));
        year1.getData().add(new XYChart.Data("4", 130));
        year1.getData().add(new XYChart.Data("5", 180));
        year1.getData().add(new XYChart.Data("6", 120));
        year1.getData().add(new XYChart.Data("7", 133));
        year1.getData().add(new XYChart.Data("8", 200));
        year1.getData().add(new XYChart.Data("9", 230));
        year1.getData().add(new XYChart.Data("10", 250));
        year1.getData().add(new XYChart.Data("11", 140));
        year1.getData().add(new XYChart.Data("12", 110));
        year1.getData().add(new XYChart.Data("13", 125));
        year1.getData().add(new XYChart.Data("14", 0));
        year1.getData().add(new XYChart.Data("15", 180));
        year1.getData().add(new XYChart.Data("16", 120));
        year1.getData().add(new XYChart.Data("17", 133));
        year1.getData().add(new XYChart.Data("18", 200));
        year1.getData().add(new XYChart.Data("19", 230));
        year1.getData().add(new XYChart.Data("20", 230));
        year1.getData().add(new XYChart.Data("21", 140));
        year1.getData().add(new XYChart.Data("22", 110));
        year1.getData().add(new XYChart.Data("23", 125));
        year1.getData().add(new XYChart.Data("24", 130));
        year1.getData().add(new XYChart.Data("25", 180));
        year1.getData().add(new XYChart.Data("26", 120));
        year1.getData().add(new XYChart.Data("27", 133));
        year1.getData().add(new XYChart.Data("28", 200));
        year1.getData().add(new XYChart.Data("29", 230));
        year1.getData().add(new XYChart.Data("30", 30));

        //adding series1 to the stackedareachart
        stack.getData().add(year1);
        year1.setName("2017");

        stack.getStylesheets().add(CSSStyler.get("chart.css"));
        return stack;
    }

    public interface ReportCallback {

        void lastMonthSell(StackedAreaChart stack); // size 30 (toman)

        void lastYearSell(StackedAreaChart stack);// size 12 (toman)

        void todaySell(int sellSum); //sum of today sells (toman)

        void hourSell(int[] hours); // size 6 -> (6:00to9:00) , (9:00to12:00) , (12:00to15:00) , (15:00to18:00) , (18:00to21:00), (21:00to24:00)

        //                                          0               1                 2               3                 4               5
        void countBaseVSTimeBase(int timeBaseIncome, int countBaseIncome);

        void timeBaseServices(int[] services);

        void countBaseServices(int[] services); //limit to top 10

        void top5Games(int[] usersCount); // پر طرفدار ترین بازی ها بر اساس تعداد یوزر
    }
}
