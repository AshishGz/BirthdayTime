package com.example.birthdaytime.javaClass;

import com.example.birthdaytime.DatabaseHelper;

import java.util.Calendar;

/**
 * Created by admin on 2/17/2017.
 */

public class RemainingDays {
    DatabaseHelper databaseHelper;


    public String calculateRemaingDays(String userYear, String userMonth, String UserDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        int month = calendar.get(Calendar.MONTH) + 1;
        int monthdiff = month - Integer.parseInt(userMonth);
        return monthdiff + "";


    }

}
