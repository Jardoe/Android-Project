package com.example.user.todolist.Model;

import java.io.Serializable;

/**
 * Created by user on 24/03/2018.
 */

public class Task implements Serializable {
    private Integer id;
    private String taskName;
    private int priority;

    public Task(){}

    public Task(String taskName, int priority){
        this.taskName = taskName;
        this.priority = priority;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
