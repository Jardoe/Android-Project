package com.example.user.todolist.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 24/03/2018.
 */

public class Task implements Serializable {
    private Integer id;
    private String taskName;
    private Integer priority;
    private String date;

    public Task(){}

    public Task(String taskName, Integer priority, String date){
        this.taskName = taskName;
        this.priority = priority;
        this.date = date;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
