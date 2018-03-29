package com.example.user.todolist.Activities;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.ListView;

import com.example.user.todolist.Model.Task;
import com.example.user.todolist.TaskArrayAdapter;
import com.example.user.todolist.db.TaskDB;

import java.util.ArrayList;

/**
 * Created by user on 29/03/2018.
 */

public class UIUpdater extends ContextWrapper{

    TaskDB taskDB;
    private ListView taskListView;
    Context context;

    public UIUpdater(Context base) {
        super(base);
    }

    public void updateUIForToday(){
        ArrayList<Task> taskList;
        taskList = taskDB.findAllForToday();
        TaskArrayAdapter arrayAdapter = new TaskArrayAdapter(this, taskList);
        taskListView.setAdapter(arrayAdapter);
    }

    public void updateUI(){
        ArrayList<Task> taskList;
        taskList = taskDB.getAllTasks();
        TaskArrayAdapter arrayAdapter = new TaskArrayAdapter(this, taskList);
        taskListView.setAdapter(arrayAdapter);
    }

    public void updateUIForTomorrow(){
        ArrayList<Task> taskList;
        taskList = taskDB.findAllForTomorrow();
        TaskArrayAdapter arrayAdapter = new TaskArrayAdapter(this, taskList);
        taskListView.setAdapter(arrayAdapter);
    }

    public void updateUIForWeek(){
        ArrayList<Task> taskList;
        taskList = taskDB.findAllForWeek();
        TaskArrayAdapter arrayAdapter = new TaskArrayAdapter(this, taskList);
        taskListView.setAdapter(arrayAdapter);
    }
}
