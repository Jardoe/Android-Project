package com.example.user.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 23/03/2018.
 */

public class dbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "ToDoList.db";
    public static final int DB_VERSION = 5;
//    DB version 4 = adding priority.


    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createPrioritiesTable = "create table " + PriorityDB.TABLE_NAME + " ( " +
                PriorityDB.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PriorityDB.PRIORITY + " STRING );";

        db.execSQL(createPrioritiesTable);

        db.execSQL("INSERT INTO " + PriorityDB.TABLE_NAME + " ( " + PriorityDB.PRIORITY + " ) " + " VALUES ('none')");
        db.execSQL("INSERT INTO " + PriorityDB.TABLE_NAME + " ( " + PriorityDB.PRIORITY + " ) " + " VALUES ('low')");
        db.execSQL("INSERT INTO " + PriorityDB.TABLE_NAME + " ( " + PriorityDB.PRIORITY + " ) " + " VALUES ('medium')");
        db.execSQL("INSERT INTO " + PriorityDB.TABLE_NAME + " ( " + PriorityDB.PRIORITY + " ) " + " VALUES ('high')");

        String createTaskTable = "create table "
                + TaskDB.TABLE_NAME + " ("
                + TaskDB.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskDB.TASK_NAME + " TEXT NOT NULL, "
                + TaskDB.PRIORITY + " INTEGER, "
                + " FOREIGN KEY ("+TaskDB.PRIORITY+") REFERENCES "+ PriorityDB.TABLE_NAME+"("+ PriorityDB.KEY_ID+")); ";
        db.execSQL(createTaskTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskDB.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PriorityDB.TABLE_NAME);
        onCreate(db);
    }

}
