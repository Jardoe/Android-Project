package com.example.user.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 23/03/2018.
 */

public class dbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "ToDoList.db";
    public static final int DB_VERSION = 3;


    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + TaskDB.TABLE_NAME + " ( " +
                                                TaskDB.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                TaskDB.TASK_NAME + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskDB.TABLE_NAME);
        onCreate(db);
    }

}
