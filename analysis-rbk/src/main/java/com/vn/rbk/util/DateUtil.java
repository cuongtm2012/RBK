package com.vn.rbk.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateUtil {
    public static String convertDateDDMMYYYY(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat convert = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = new Date();
        try {
            myDate = dateFormat.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return convert.format(myDate);
    }

    public static String convertDateYYYYMMDD(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        try {
            myDate = dateFormat.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return dateFormat.format(myDate);
    }


    public static String newDateYYYYMMDD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        return dateFormat.format(myDate);
    }

    public static String newDateYYYYMMDD(LocalDate mydate) {
        return mydate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String dateFormatYYYYMMDD(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
