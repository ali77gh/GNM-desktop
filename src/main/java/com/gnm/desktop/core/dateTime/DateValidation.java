package com.gnm.desktop.core.dateTime;

/**
 * Created by ali on 10/2/18.
 */

public class DateValidation {


    public static void JalaliValidate(int year, int month, int day, int hour, int min, int sec) {
        BaseValidate(year, month, hour, min, sec);
        JalaliDayValidate(year, month, day);
    }

    private static void JalaliDayValidate(int year, int month, int day) {

        //day
        if (DateConverter.IsJalaliLeap(year) & month == 12) {
            if (day < 1 | day > JalaliDateTime.DaysInMonth[month] + 1)
                NotValid("day", day);
        } else {
            if (day < 1 | day > JalaliDateTime.DaysInMonth[month])
                NotValid("day", day);
        }

    }

    private static void BaseValidate(int year, int month, int hour, int min, int sec) {

        //year
        if (year < 0) NotValid("year", year);

        //month
        if (month < 1 | month > 12) NotValid("month", month);

        //time
        if (hour > 23 | hour < 0) NotValid("hour", hour);
        if (min > 59 | min < 0) NotValid("minute", min);
        if (sec > 59 | sec < 0) NotValid("second", sec);
    }


    private static void NotValid(String what, int value) {
        throw new IllegalArgumentException("invalid " + what + " : " + value);
    }
}
