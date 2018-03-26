package com.example.user.todolist.db;

import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * Created by user on 24/03/2018.
 */

public class Task implements Serializable {
    private Integer id;
    private String taskName;

    public Task(){}

    public Task(String taskName){
        super();
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String toString(){
        return "Task [id =" + id + ", taskName =" + taskName + "]";
    }

    public Integer getId() {
        return id;
    }
}
