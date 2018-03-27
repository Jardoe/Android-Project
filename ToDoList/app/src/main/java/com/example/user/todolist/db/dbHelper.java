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

        String createPrioritiesTable = "create table " + priorityDB.TABLE_NAME + " ( " +
                priorityDB.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                priorityDB.PRIORITY + " STRING );";

        db.execSQL(createPrioritiesTable);

        db.execSQL("INSERT INTO " + priorityDB.TABLE_NAME + " ( " + priorityDB.PRIORITY + " ) " + " VALUES ('none')");
        db.execSQL("INSERT INTO " + priorityDB.TABLE_NAME + " ( " + priorityDB.PRIORITY + " ) " + " VALUES ('low')");
        db.execSQL("INSERT INTO " + priorityDB.TABLE_NAME + " ( " + priorityDB.PRIORITY + " ) " + " VALUES ('medium')");
        db.execSQL("INSERT INTO " + priorityDB.TABLE_NAME + " ( " + priorityDB.PRIORITY + " ) " + " VALUES ('high')");

        String createTaskTable = "create table "
                + TaskDB.TABLE_NAME + " ("
                + TaskDB.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskDB.TASK_NAME + " TEXT NOT NULL, "
                + TaskDB.PRIORITY + " INTEGER, "
                + " FOREIGN KEY ("+TaskDB.PRIORITY+") REFERENCES "+priorityDB.TABLE_NAME+"("+priorityDB.KEY_ID+")); ";
        db.execSQL(createTaskTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskDB.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + priorityDB.TABLE_NAME);
        onCreate(db);
    }

}
