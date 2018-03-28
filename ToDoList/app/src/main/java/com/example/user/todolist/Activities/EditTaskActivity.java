package com.example.user.todolist.Activities;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.user.todolist.Model.Task;
import com.example.user.todolist.R;
import com.example.user.todolist.db.PriorityDB;
import com.example.user.todolist.db.TaskDB;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class EditTaskActivity extends AppCompatActivity {

    TaskDB taskDB;
    PriorityDB priorityDB;
    String date;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        taskDB = new TaskDB(this);

        Intent intent = getIntent();

        final Task selectedTask = (Task) intent.getSerializableExtra("task");

        final EditText taskNameEdit = findViewById(R.id.taskNameEdit);
        taskNameEdit.setText(selectedTask.getTaskName());

        final Spinner prioritySetter = findViewById(R.id.priority_setter);
        populateSpinner();


        ImageButton submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Object spinnerOption = prioritySetter.getSelectedItem();

                int pri;

                if(spinnerOption.equals("none")) {
                    pri = 1;
                } else if(spinnerOption.equals("low")) {
                    pri = 2;
                } else if(spinnerOption.equals("medium")){
                    pri = 3;
                } else {
                    pri = 4;}

                selectedTask.setTaskName(taskNameEdit.getText().toString());
                selectedTask.setPriority(pri);
                selectedTask.setDate(getDateFromPicker());
                Log.e("Set Date", selectedTask.getDate());

                taskDB.updateTask(selectedTask);
                Log.d("Priority setter", String.valueOf(selectedTask.getPriority()));

                finish();
                Intent homepage = new Intent(EditTaskActivity.this, MainActivity.class);
                startActivity(homepage);
            }
        });

    }

    private void populateSpinner(){
        priorityDB = new PriorityDB(this);
        String[] array = priorityDB.getAll();
        List<String> priorityList = Arrays.asList(array);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorityList);
        Spinner prioritySetter = findViewById(R.id.priority_setter);
        prioritySetter.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getDateFromPicker(){
        String date1;
        final DatePicker datePicker = findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, d MMM, ''yy");
        Date d = new Date(year, month, day);
        return date1 = dateFormatter.format(d);

    }

}
