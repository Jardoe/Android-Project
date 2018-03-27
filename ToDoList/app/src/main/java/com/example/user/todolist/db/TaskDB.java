package com.example.user.todolist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.todolist.Model.Task;

import java.util.ArrayList;

/**
 * Created by user on 26/03/2018.
 */

public class TaskDB extends dbHelper {
    static final String TABLE_NAME = "ToDoList";
    static final String KEY_ID = "id";
    static final String TASK_NAME = "task";
    static final String PRIORITY = " priority";
    private static final String[] COLUMNS = {KEY_ID,TASK_NAME, PRIORITY};

    public TaskDB(Context context) {
        super(context);
    }


    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_NAME, task.getTaskName());
        values.put(PRIORITY, String.valueOf(task.getPriority()));

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
        ArrayList<Task> tasks = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Task task;
        while (cursor.moveToNext()){
            task = new Task();
            task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)));
            task.setTaskName(cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME)));

            tasks.add(task);
        }
        return tasks;
    }

    public int updateTask( Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("task", task.getTaskName());
        values.put("priority", task.getPriority());

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
}
