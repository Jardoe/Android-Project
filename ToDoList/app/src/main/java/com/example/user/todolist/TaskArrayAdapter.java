package com.example.user.todolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.todolist.Model.Task;

import java.util.ArrayList;

/**
 * Created by user on 26/03/2018.
 */

public class TaskArrayAdapter extends ArrayAdapter<Task> {

    public TaskArrayAdapter(@NonNull Context context, ArrayList<Task> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        Task currentTask = getItem(position);

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        TextView task = listItemView.findViewById(R.id.task_name);
        task.setText(currentTask.getTaskName());

        ImageButton priorityButton = listItemView.findViewById(R.id.priorityButton);
        priorityButton.setTag(currentTask);
        if (currentTask.getPriority() == 1){
            priorityButton.setVisibility(View.INVISIBLE);
        }


        Button doneButton =listItemView.findViewById(R.id.task_complete);
        doneButton.setTag(currentTask);
        
        listItemView.setTag(currentTask);
        return listItemView;
    }
}


