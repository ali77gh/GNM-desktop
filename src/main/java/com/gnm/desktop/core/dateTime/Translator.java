package com.gnm.desktop.core.dateTime;

public class Translator {

    public static String toPersian(String englishNum) {
        englishNum = englishNum.replace("0", "۰");
        englishNum = englishNum.replace("1", "۱");
        englishNum = englishNum.replace("2", "۲");
        englishNum = englishNum.replace("3", "۳");
        englishNum = englishNum.replace("4", "۴");
        englishNum = englishNum.replace("5", "۵");
        englishNum = englishNum.replace("6", "۶");
        englishNum = englishNum.replace("7", "۷");
        englishNum = englishNum.replace("8", "۸");
        englishNum = englishNum.replace("9", "۹");
        return englishNum;
    }

    /**
     * index starts from 1
     */
    public static String MonthToPersian(int index) {
        switch (index) {
            case 1:
                return "فروردین";
            case 2:
                return "اردیبهشت";
            case 3:
                return "خرداد";
            case 4:
                return "تیر";
            case 5:
                return "مرداد";
            case 6:
                return "شهریور";
            case 7:
                return "مهر";
            case 8:
                return "آبان";
            case 9:
                return "آذر";
            case 10:
                return "دی";
            case 11:
                return "بهمن";
            case 12:
                return "اسفند";
            default:
                throw new RuntimeException("invalid index");
        }
    }

    /**
     * index starts from 1
     */
    public static String SeasonToPersian(int index) {
        switch (index) {
            case 1:
                return "بهار";
            case 2:
                return "تابستان";
            case 3:
                return "پاییز";
            case 4:
                return "زمستان";
            default:
                throw new RuntimeException("invalid index");
        }
    }

    /**
     * index starts from 1
     */
    public static String DayOfWeekToPersian(int index) {
        switch (index) {
            case 0:
                return "شنبه";
            case 1:
                return "یکشنبه";
            case 2:
                return "دوشنبه";
            case 3:
                return "سه شنبه";
            case 4:
                return "چهارشنبه";
            case 5:
                return "پنجشنبه";
            case 6:
                return "جمعه";

            default:
                throw new RuntimeException("invalid index");
        }
    }
}
