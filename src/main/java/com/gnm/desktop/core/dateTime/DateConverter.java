package com.gnm.desktop.core.dateTime;

import java.util.Date;

/**
 * Created by ali on 9/5/18.
 */

public class DateConverter {

    public static boolean IsJalaliLeap(int year) {
        if (year >= 1 && year <= 474) {
            year += 2346;
        } else if (year > 474) {
            year -= 474;
        }

        year = year % 2820;

        year = year % 128;

        year -= year >= 29 ? 29 : 0;

        year = year % 33;

        year -= year >= 5 ? 5 : 0;

        year = year % 4;

        return year == 0;
    }

    public static boolean IsGregorianLeap(int year) {
        return year % 400 == 0 || (year % 100 != 0 & year % 4 == 0);
    }


    public static int JalaliToDays(int year, int month, int day) {
        return day +
                (month - 1 <= 6 ? (month - 1) * 31 : (month - 1) * 30 + 6) +
                (year - 1) * 365 +
                LeapsJalaliBehind(year - 1);
    }


    static DateModel DaysToJalali(int days) {

        DateModel jalali = new DateModel();

        if (days >= 1 & days <= 173125) {
            jalali.year -= 2346;
            days += 856858;
        } else if (days > 173125) {
            jalali.year += 474;
            days -= 173125;
        }

        jalali.year += (days / 1029983) * 2820;

        days = days % 1029983;

        jalali.year += (days / 46751) * 128;

        days = days % 46751;

        if (days >= 10592) {
            jalali.year += 29;

            days -= 10592;

            jalali.year += (days / 12053) * 33;

            days = days % 12053;

            if (days >= 1826) {
                jalali.year += 5;

                days -= 1826;

                jalali.year += (days / 1461) * 4;

                days = days % 1461;
            }
        } else if (days >= 1826) {
            jalali.year += 5;

            days -= 1826;

            jalali.year += (days / 1461) * 4;

            days = days % 1461;
        }

        if (days == 0 & IsJalaliLeap(jalali.year)) {
            jalali.year -= 1;
            days = 366;
        } else if (days == 1460) {
            jalali.year += 3;
            days = 365;
        } else {
            jalali.year += days / 365;
            days = days % 365;
            if (days == 0) {
                jalali.year -= 1;
                days = 365;
            }
        }

        if (days > 336) {
            jalali.month = 11;
            jalali.day = days - 336;
        } else if (days > 306) {
            jalali.month = 10;
            jalali.day = days - 306;
        } else if (days > 276) {
            jalali.month = 9;
            jalali.day = days - 276;
        } else if (days > 246) {
            jalali.month = 8;
            jalali.day = days - 246;
        } else if (days > 216) {
            jalali.month = 7;
            jalali.day = days - 216;
        } else if (days > 186) {
            jalali.month = 6;
            jalali.day = days - 186;
        } else if (days > 155) {
            jalali.month = 5;
            jalali.day = days - 155;
        } else if (days > 124) {
            jalali.month = 4;
            jalali.day = days - 124;
        } else if (days > 93) {
            jalali.month = 3;
            jalali.day = days - 93;
        } else if (days > 62) {
            jalali.month = 2;
            jalali.day = days - 62;
        } else if (days > 31) {
            jalali.month = 1;
            jalali.day = days - 31;
        } else {
            jalali.day = days;
        }

        jalali.year += 1;

        jalali.month += 1;

        return jalali;
    }

    static DateModel DaysToGregorian(int days) {

        DateModel miladi = new DateModel();

        int quadricent = days / 146097;

        days = days % 146097;

        int cent = days / 36524;

        days = days % 36524;

        int quad = days / 1461;

        days = days % 1461;

        miladi.year = (quadricent * 400 + cent * 100 + quad * 4);

        int one = 0;

        if (days == 0 & IsGregorianLeap(miladi.year)) {
            one -= 1;
            days = 366;
        } else if (days == 1460) {
            one = 3;
            days = 365;
        } else {
            one = days / 365;
            days = days % 365;
            if (days == 0) {
                one -= 1;
                days = 365;
            }
        }

        miladi.year += one;

        if (IsGregorianLeap(miladi.year + 1)) {
            days--;
        }
        if (days > 334) {
            miladi.month = 11;
            miladi.day = days - 334;
        } else if (days > 304) {
            miladi.month = 10;
            miladi.day = days - 304;
        } else if (days > 273) {
            miladi.month = 9;
            miladi.day = days - 273;
        } else if (days > 243) {
            miladi.month = 8;
            miladi.day = days - 243;
        } else if (days > 212) {
            miladi.month = 7;
            miladi.day = days - 212;
        } else if (days > 181) {
            miladi.month = 6;
            miladi.day = days - 181;
        } else if (days > 151) {
            miladi.month = 5;
            miladi.day = days - 151;
        } else if (days > 120) {
            miladi.month = 4;
            miladi.day = days - 120;
        } else if (days > 90) {
            miladi.month = 3;
            miladi.day = days - 90;
        } else if (days > 59) {
            miladi.month = 2;
            miladi.day = days - 59;
        } else if (days > 31) {
            miladi.month = 1;
            miladi.day = days - 31;

            if (IsGregorianLeap(miladi.year + 1)) {
                miladi.day++;
            }
        } else if (IsGregorianLeap(miladi.year + 1) & days == 31) {
            miladi.month = 1;
            miladi.day = 1;
        } else {
            miladi.day = days;

            if (IsGregorianLeap(miladi.year + 1)) {
                miladi.day++;
            }
        }

        miladi.year += 1;
        miladi.month += 1;

        return miladi;
    }

    private static int LeapsJalaliBehind(int year) {
        int leaps = 0;

        if (year >= 1 & year <= 474) {
            year += 2346;

            leaps -= 568;
        } else if (year > 474) {
            year -= 474;

            leaps += 115;
        }

        leaps += year / 2820 * 683;

        year = year % 2820;

        leaps += year / 128 * 31;

        year = year % 128;

        if (year >= 29) {
            year -= 29;

            leaps += 7;

            leaps += year / 33 * 8;

            year = year % 33;

            if (year < 5) {
                return leaps;
            }

            year -= 5;

            leaps += 1;

            leaps += year / 4;
        } else {
            if (year < 5) {
                return leaps;
            }

            year -= 5;

            leaps += 1;

            leaps += year / 4;
        }

        return leaps;
    }


    public static DateModel JalaliToGregorian(int year, int month, int day) {
        return DaysToGregorian(JalaliToDays(year, month, day) + 226895);
    }


    //unix time

    public static DateModel UnixToJalali(int sec) {
        DateModel date = DaysToJalali((sec / 86400) + 719163 - 226895);

        Date d = new Date((long) sec * 1000);
        date.hour = d.getHours();
        date.min = d.getMinutes();
        date.sec = d.getSeconds();
        return date;
    }

    public static int JalaliToUnix(int year, int month, int day, int hour, int min, int sec) {

        int from = 719163; // const : DateConverter.GregorianToDays(1970, 1, 1)

        from -= 226895; // jalali and gregorian deference in days

        int days = JalaliToDays(year, month, day);

        int lastDays = ((days - from) * 86400);

        int today = sec + (min * 60) + (hour * 3600);

        return today + lastDays - 16200;//I don't know why
    }


}
