package com.example.user.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 23/03/2018.
 */

public class dbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "ToDoList.db";
    public static final String TABLE_NAME = "ToDoList";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TASK";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "PRIORITY";
    public static final String COL_5 = "COMPLETED";




    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table IF NOT EXISTS " + TABLE_NAME + "\n" +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "Task TEXT NOT NULL, \n" +
                        "Date datetime, \n" +
                        "Priotity INTEGER, \n" +
                        "Completed BOOLEAN \n" +
                        ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
