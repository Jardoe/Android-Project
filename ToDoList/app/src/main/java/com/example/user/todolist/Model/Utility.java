package com.example.user.todolist.Model;

import android.util.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by user on 28/03/2018.
 */

public class Utility {

    public static String dateToString (Date date){
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM, ''yy");
        return dateFormat.format(date);
    }

    public static Date stringToDate (String date){
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM, ''yy");
        Date newDate = null;
        try {
            newDate = (Date) dateFormat.parse(date);
        } catch (ParseException e) {
            Log.d("string to date", "String to date " +  e.toString());
        }
        return newDate;
    }

}
