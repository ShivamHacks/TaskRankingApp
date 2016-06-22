package com.example.shivamagrawal.taskranker2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {

    public static String formatDate(int[] date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date[0], date[1], date[2]);
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String formattedDate = dateInstance.format(calendar.getTime());
        return formattedDate;
    }

    public static String formatTime(int[] time) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, time[0]);
        calendar.set(Calendar.MINUTE, time[1]);
        DateFormat timeInstance = SimpleDateFormat.getTimeInstance(DateFormat.SHORT);
        String formattedTime = timeInstance.format(calendar.getTime());
        return formattedTime;
    }

}
