package com.gnm.desktop.data.repo;

import com.gnm.desktop.core.dateTime.JalaliDateTime;
import com.gnm.desktop.core.dateTime.Translator;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.DBPager;
import com.gnm.desktop.data.model.Customer;
import com.gnm.desktop.data.model.SellLog;
import com.gnm.desktop.ui.ChartGen;
import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Report {

    private static ReportCallback cb;
    private static JalaliDateTime now = JalaliDateTime.Now();

    private static List<XYChart.Data> lastMonthData;
    private static List<XYChart.Data> lastYearData;
    private static List<XYChart.Data> hourSellData;
    private static List<PieChart.Data> countBaseVSTimeBaseData;
    private static List<PieChart.Data> topGamesData;
    private static List<PieChart.Data> timeBaseServicesData;
    private static List<PieChart.Data> countBaseServicesData;

    //callback setter and data init
    public static void Init(ReportCallback callback) {
        Report.cb = callback;
    }

    //main work
    public static void RefreshAll() {

        //remove current
        if (lastYearData != null) {
            lastMonthData = null;
            lastYearData = null;
            hourSellData = null;
            countBaseVSTimeBaseData = null;
            topGamesData = null;
            timeBaseServicesData = null;
            countBaseServicesData = null;
        }


        var pager = new DBPager(SellLog.class);

        calculateTopGames();//inside main thread (because this should finish before other query)

        pager.setCallBack(new DBPager.PagerCallBack() {
            @Override
            public void callback(Object o) {
                var selllog = ((SellLog) o);
                addToLastMonth(selllog);
                addToLastYear(selllog);
                addToHourSell(selllog);
                addToCountVsTime(selllog);
                addToTime(selllog);
                addToCount(selllog);
            }

            @Override
            public void onEnd() {
                Platform.runLater(() -> {
                    cb.lastMonthSell(ChartGen.generateStack(
                            "فروش این ماه",
                            "روز",
                            "مبلغ(تومان)",
                            JalaliDateTime.Now().getMonthLetters(),
                            lastMonthData
                    ));
                    cb.lastYearSell(ChartGen.generateStack(
                            "فروش این سال",
                            "ماه",
                            "ملبغ(تومان)",
                            JalaliDateTime.Now().getYearLetters(),
                            lastYearData

                    ));
                    cb.hourSell(ChartGen.generateBar(
                            "ساعت هایی که شلوغ میشه",
                            "بازه های زمانی",
                            "تعداد مشتری",
                            "تعداد مشتری", // todo
                            hourSellData

                    ));
                    cb.firstPieSet(
                            ChartGen.generatePie(
                                    "فروش بر پایه تعداد vs فروش بر پایه زمان",
                                    countBaseVSTimeBaseData
                            ), ChartGen.generatePie(
                                    "پر طرفدار ترین بازی ها",
                                    topGamesData

                            )
                    );
                    cb.secondPieSet(ChartGen.generatePie(
                            "پر طرفدار ترین سرویس های بر پایه زمان",
                            timeBaseServicesData
                            ), ChartGen.generatePie(
                            "پر طرفدار ترین سرویس های بر پایه تعداد",
                            countBaseServicesData
                            )
                    );
                });
            }
        });

        DB.SellLogs.getWithPager(pager);
        pager.start();

    }

    //inside paging methods
    private static void addToLastMonth(SellLog sellLog) {

        //first time (just happend ones)
        if (lastMonthData == null) {
            lastMonthData = new ArrayList<>();
            for (int i = 1; i <= 31; i++) {
                lastMonthData.add(new XYChart.Data<>(Translator.toPersian(i), 0));
            }
        }
        //this happens many time

        var sellTime = JalaliDateTime.withUnixTime((int) sellLog.time);

        // year and month filter
        if (sellTime.getYear() != now.getYear()) return;
        if (sellTime.getMonth() != now.getMonth()) return;

        var day = sellTime.getDay() - 1;
        var current = lastMonthData.get(day);
        var currentVal = (int) current.getYValue();
        current.setYValue(currentVal + sellLog.income);
        lastMonthData.set(day, current);
    }

    private static void addToLastYear(SellLog sellLog) {

        //first time just happend ones
        if (lastYearData == null) {
            lastYearData = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                lastYearData.add(new XYChart.Data<>(Translator.MonthToPersian(i), 0));
            }
        }
        //this happens many times

        var sellTime = JalaliDateTime.withUnixTime((int) sellLog.time);

        // year filter
        if (sellTime.getYear() != now.getYear()) return;

        var month = sellTime.getMonth() - 1;
        var current = lastYearData.get(month);
        var currentVal = (int) current.getYValue();
        current.setYValue(currentVal + sellLog.income);
        lastYearData.set(month, current);

    }

    private static void addToHourSell(SellLog sellLog) {

        //first time just happend ones
        if (hourSellData == null) {
            hourSellData = new ArrayList<>();

            hourSellData.add(new XYChart.Data<>("۶تا۹", 0));
            hourSellData.add(new XYChart.Data<>("۹تا۱۲", 0));
            hourSellData.add(new XYChart.Data<>("۱۲تا۱۵", 0));
            hourSellData.add(new XYChart.Data<>("۱۵تا۱۸", 0));
            hourSellData.add(new XYChart.Data<>("۱۸تا۲۱", 0));
            hourSellData.add(new XYChart.Data<>("۲۱تا۲۴", 0));

        }
        //this happens many times

        var sellTime = JalaliDateTime.withUnixTime((int) sellLog.time);

        int caseIndex;
        if (sellTime.getHour() > 6 && sellTime.getHour() < 9) caseIndex = 0;
        else if (sellTime.getHour() > 9 && sellTime.getHour() < 12) caseIndex = 1;
        else if (sellTime.getHour() > 12 && sellTime.getHour() < 15) caseIndex = 2;
        else if (sellTime.getHour() > 15 && sellTime.getHour() < 18) caseIndex = 3;
        else if (sellTime.getHour() > 18 && sellTime.getHour() < 21) caseIndex = 4;
        else if (sellTime.getHour() > 21 && sellTime.getHour() < 24) caseIndex = 5;
        else return; // بیخیال فروش هایی میشیم که از ۱۲ شب به بعده

        var current = hourSellData.get(caseIndex);
        var currentVal = (int) hourSellData.get(caseIndex).getYValue();
        current.setYValue(currentVal + 1);
        hourSellData.set(caseIndex, current);
    }

    private static void addToCountVsTime(SellLog sellLog) {
        //first time just happend ones
        if (countBaseVSTimeBaseData == null) {
            countBaseVSTimeBaseData = new ArrayList<>();

            countBaseVSTimeBaseData.add(new PieChart.Data("بر پایه تعداد", 0));
            countBaseVSTimeBaseData.add(new PieChart.Data("بر پایه زمان", 0));
        }

        var caseIndex = sellLog.serviceTime == -1 ? 0 : 1; // اینجا تشخیص میده که برپایه زمانه یا بر پایه نعداد
        var current = countBaseVSTimeBaseData.get(caseIndex);
        var currentVal = current.getPieValue();
        current.setPieValue(currentVal + 1);
        countBaseVSTimeBaseData.set(caseIndex, current);
    }

    private static void addToTime(SellLog sellLog) {

        if (timeBaseServicesData == null) {
            timeBaseServicesData = new ArrayList<>();
        }

        //filter time base services
        if (sellLog.serviceTime == -1) return;

        var index = indexOfTimeBase(sellLog.serviceName);
        if (index == -1) {
            //not exist
            timeBaseServicesData.add(new PieChart.Data(sellLog.serviceName, 1));
        } else {
            //exist
            var current = timeBaseServicesData.get(index);
            var currentVal = current.getPieValue();
            current.setPieValue(currentVal + 1);
            timeBaseServicesData.set(index, current);
        }

    }

    private static int indexOfTimeBase(String name) {
        for (var i : timeBaseServicesData) {
            if (i.getName().equals(name)) return timeBaseServicesData.indexOf(i);
        }
        return -1;
    }

    private static void addToCount(SellLog sellLog) {

        if (countBaseServicesData == null) {
            countBaseServicesData = new ArrayList<>();
        }

        //filter count base services
        if (sellLog.serviceTime != -1) return;

        var index = indexOfCountBase(sellLog.serviceName);
        if (index == -1) {
            //not exist
            countBaseServicesData.add(new PieChart.Data(sellLog.serviceName, 1));
        } else {
            //exist
            var current = countBaseServicesData.get(index);
            var currentVal = current.getPieValue();
            current.setPieValue(currentVal + 1);
            countBaseServicesData.set(index, current);
        }
    }

    private static int indexOfCountBase(String name) {
        for (var i : countBaseServicesData) {
            if (i.getName().equals(name)) return countBaseServicesData.indexOf(i);
        }
        return -1;
    }

    private static void calculateTopGames() {

        var result = new HashMap<String, Integer>();
        DB.Customers.getWithCondition(object -> {
            var customer = (Customer) object;
            for (var game : customer.games) {
                if (result.containsKey(game)) {
                    var count = result.get(game);
                    result.replace(game, count, count + 1);
                } else {
                    result.put(game, 1);
                }
            }
            return false;
        });


        if (result.size() != 0) {
            topGamesData = new ArrayList<>();
            result.forEach((s, integer) ->
                    topGamesData.add(new PieChart.Data(s, integer))
            );
        }

    }

    public interface ReportCallback {

        //card1
        void lastMonthSell(StackedAreaChart stack); // size 30 (toman)

        //card2
        void lastYearSell(StackedAreaChart stack);// size 12 (toman)

        //card3
        void hourSell(BarChart bar); // size 6 -> (6:00to9:00) , (9:00to12:00) , (12:00to15:00) , (15:00to18:00) , (18:00to21:00), (21:00to24:00)

        //card4
        void firstPieSet(PieChart countBaseVSTimeBase, PieChart topGames);// پر طرفدار ترین بازی ها بر اساس تعداد یوزر

        //card5
        void secondPieSet(PieChart timeBaseServices, PieChart countBaseServices);//limit to top 10
    }
}
