package com.example.user.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 27/03/2018.
 */

public class priorityDB extends dbHelper {
    static final String TABLE_NAME = "Priorities";
    static final String KEY_ID = "id";
    static final String PRIORITY = "priority";

    public priorityDB(Context context) {
        super(context);
    }


}
