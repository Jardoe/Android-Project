package com.example.user.todolist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.user.todolist.Model.Task;
import com.example.user.todolist.Model.Utility;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by user on 26/03/2018.
 */

public class TaskDB extends dbHelper {
    static final String TABLE_NAME = "ToDoList";
    static final String KEY_ID = "id";
    static final String TASK_NAME = "task";
    static final String PRIORITY = "priority";
    static final String DATE = "date";
    private static final String[] COLUMNS = {KEY_ID,TASK_NAME, PRIORITY, DATE};

    public TaskDB(Context context) {
        super(context);
    }


    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_NAME, task.getTaskName());
        values.put(PRIORITY, String.valueOf(task.getPriority()));
        values.put(DATE, String.valueOf(task.getDate()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Task getTask(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, COLUMNS, " id = ?", null, null, null, null, null);

        if (cursor != null){
            cursor.moveToNext();
        }

        Task task = new Task();
        task.setId(Integer.parseInt(cursor.getString(0)));
        task.setTaskName(cursor.getString(1));

        return task;
    }


    public ArrayList<Task> getAllTasks() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return this.parseResult(cursor);
    }

    public ArrayList<Task> findAllForToday(){
        SQLiteDatabase db = this.getReadableDatabase();

        String whereClaus = DATE + " = ?";
        String[] whereArgs = {new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()).format(new java.util.Date())};

        String orderBy = "date DESC";

        Log.e("TagDerp", whereArgs[0]);

        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                whereClaus,
                whereArgs,
                null,
                null,
                orderBy);
        return this.parseResult(cursor);
    }

    public ArrayList<Task> findAllForTomorrow(){
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClaus = DATE + " = ?";

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);

        java.util.Date dayPlus1 = cal.getTime();

        String[] whereArgs = {new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()).format(dayPlus1)};
        String orderBy = "date DESC";

        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                whereClaus,
                whereArgs,
                null,
                null,
                orderBy);


        return this.parseResult(cursor);
    }

    public ArrayList<Task> findAllForWeek(){
        SQLiteDatabase db = this.getReadableDatabase();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);

        java.util.Date dayPlus7 = cal.getTime();

        String q = ("SELECT * FROM " + TABLE_NAME + " WHERE " + DATE + " BETWEEN "
                + "'" + (new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()).format(new java.util.Date())) + "'"
                + " AND "
                 + "'" + (new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()).format(dayPlus7)))+ "'";
        Cursor cursor = db.rawQuery(q, null);
      return this.parseResult(cursor);

    }

    public int updateTask( Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("task", task.getTaskName());
        values.put("priority", task.getPriority());
        values.put("date", String.valueOf(task.getDate()));

        int i = db.update(TABLE_NAME, values, KEY_ID+" = ?", new String[]{String.valueOf(task.getId())});
        db.close();
        return i;
    }

    public Boolean deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = { task.getId().toString() };
        db.delete(TABLE_NAME, KEY_ID+" = ?", args);
        db.close();
        return true;
    }

    private ArrayList<Task> parseResult(Cursor cursor){
        ArrayList<Task> tasks = new ArrayList<>();
        Task task;
        while (cursor.moveToNext()){
            task = new Task();
            task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)));
            task.setTaskName(cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME)));
            task.setPriority(cursor.getInt(cursor.getColumnIndexOrThrow(PRIORITY)));
            task.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));

            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }

}


