package com.example.user.todolist.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.todolist.Model.Task;
import com.example.user.todolist.R;
import com.example.user.todolist.TaskArrayAdapter;
import com.example.user.todolist.db.TaskDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TaskDB taskDB;
    private ListView taskListView;
    ArrayList<Task> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskDB = new TaskDB(this);
        taskListView = findViewById(R.id.listView);
        updateUI();

    }

    public boolean onAddTaskButtonClicked(View button) {
        switch(button.getId()){
            case R.id.floatingActionButton:
            final EditText taskEditText = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("New Task");
                builder.setView(taskEditText);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Task task = new Task (taskEditText.getText().toString(), 0);
                        if (taskEditText.getText().toString() == null || taskEditText.getText().toString().equals("")){
                            taskEditText.requestFocus();
                        } else {
                            taskDB.addTask(task);
                        }
                        updateUI();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected((MenuItem) button);
        }

    }

    public void onListItemClick(View listItem) {
        Task selectedTask = (Task) listItem.getTag();

        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.putExtra("task", selectedTask);

        startActivity(intent);
    }
    
    public void onClickTaskComplete(View button){
        Task task = (Task) button.getTag();
        taskDB.deleteTask(task);
        updateUI();
    }

    public void updateUI(){
        taskList = taskDB.getAllTasks();
        TaskArrayAdapter arrayAdapter = new TaskArrayAdapter(this, taskList);
        taskListView.setAdapter(arrayAdapter);
    }

}