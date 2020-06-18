package com.example.newform.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilDate {

    public static final long ONE_MINUTE_IN_MILLIS = 60000;

    public static DateFormat getDateFormat(String format) {
        Locale localeBr = new Locale("pt", "BR");

        //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return new SimpleDateFormat(format, localeBr);
    }

    public static String getDateFormatPtBr(Date date){

        if (date == null) return null;

        return getDateFormat("dd/MM/yyyy").format(date);
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Date addMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar calendarAux = Calendar.getInstance();

        int diaParcela = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, 1);

        calendarAux.setTime(calendar.getTime());

        calendarAux.add(Calendar.MONTH, 1);
        calendarAux.set(Calendar.DAY_OF_MONTH, 1);
        calendarAux.add(Calendar.DATE, -1);

        int maxDay = calendarAux.getActualMaximum(Calendar.DAY_OF_MONTH);

        if (diaParcela >= maxDay) {
            calendar.set(Calendar.DAY_OF_MONTH, maxDay);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, diaParcela);
        }

        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, month);

        return calendar.getTime();
    }

    public static int getDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date addDay(Date date, long days) {

        return addDay(date, (int) days);
    }

    public static Date addDay(Date date, int days) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, days);

        return calendar.getTime();
    }

    public static Date lastDayOfMonth(int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.set(Calendar.MONTH, month);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    public static Date lastDayOfMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    public static boolean betweenTwoDates(Date date, Date date1, Date date2) {
        return date.after(date1) && date.before(date2);
    }

    public static Date getDayOfWeek(int week, int day) {
        Calendar calendar = Calendar.getInstance();


        Calendar calendarGetDayOfWeek = Calendar.getInstance();
        calendarGetDayOfWeek.set(Calendar.WEEK_OF_MONTH, week + 1);
        calendarGetDayOfWeek.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + day);
        calendarGetDayOfWeek.set(Calendar.HOUR, 0);
        calendarGetDayOfWeek.set(Calendar.MINUTE, 0);
        calendarGetDayOfWeek.set(Calendar.SECOND, 0);
        calendarGetDayOfWeek.set(Calendar.MILLISECOND, 0);

        int monthDayOfWeek = calendarGetDayOfWeek.get(Calendar.MONTH);
        int monthCalendar = calendar.get(Calendar.MONTH);

        if (monthDayOfWeek < monthCalendar) {
            calendarGetDayOfWeek.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        }

        if (monthDayOfWeek > monthCalendar) {
            calendarGetDayOfWeek.setTime(lastDayOfMonth(calendar.getTime()));
        }

        return calendarGetDayOfWeek.getTime();

    }


    public static Date getDateYesterday(Calendar cal) {
        cal.add(Calendar.DATE, -1);
        return (cal.getTime());
    }

    public static int getWeekOfMonth(Date date) {
        Calendar weekOfMonth = Calendar.getInstance();
        weekOfMonth.setTime(date);

        return weekOfMonth.get(Calendar.WEEK_OF_MONTH);

    }


    public static boolean isDateInCurrentWeek(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    public static boolean isDateSystemEquals(Date dateServidor) {

        Calendar dateServidorAdiantado = Calendar.getInstance();
        dateServidorAdiantado.setTime(dateServidor);
        dateServidorAdiantado.add(Calendar.MINUTE, 5);

        Calendar dateServidorAtrasado = Calendar.getInstance();
        dateServidorAtrasado.setTime(dateServidor);
        dateServidorAtrasado.add(Calendar.MINUTE, -5);

        Calendar dateLocalle = Calendar.getInstance();

        return UtilDate.betweenTwoDates(dateLocalle.getTime(), dateServidorAtrasado.getTime(), dateServidorAdiantado.getTime());
    }

    public static Integer getDiffDateInDays(Date date1, Date date2) {
        long diffMillis = date2.getTime() - date1.getTime();
        double diffDays = diffMillis / (24.0 * 3600.0 * 1000.0);
        BigDecimal bd = new BigDecimal(diffDays);
        bd = bd.setScale(0, RoundingMode.HALF_EVEN);
        return (int) bd.doubleValue();
    }

    public static Date getTodayDateNoTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDateNoTime(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDateFromString(String date){

        if (date == null || date.trim().isEmpty()){
            return null;
        }

        try {
           return getDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getYearFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonthFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getDayFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Date getDateFromValues(int day, int month, int year) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.YEAR, year);
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

}
