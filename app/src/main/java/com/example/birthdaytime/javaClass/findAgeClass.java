package com.example.birthdaytime.javaClass;

import java.util.Calendar;

/**
 * Created by admin on 2/15/2017.
 */

public class findAgeClass {


    public String findAge(String giveYear, String givenMonth, String givenDay) {
        Calendar calDOB = Calendar.getInstance();
        calDOB.set(Integer.parseInt(giveYear), Integer.parseInt(givenMonth), Integer.parseInt(givenDay));
//setup calNow as today.
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new java.util.Date());
//calculate age in years.
        int ageYr = (calNow.get(Calendar.YEAR) - calDOB.get(Calendar.YEAR));
// calculate additional age in months, possibly adjust years.
        int ageMo = (calNow.get(Calendar.MONTH) - calDOB.get(Calendar.MONTH));
        if (ageMo < 0) {
//adjust years by subtracting one
            ageYr--;
        }


        return ageYr + "";
    }
}