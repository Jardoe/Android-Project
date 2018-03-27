package com.example.user.todolist.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.user.todolist.Model.Task;
import com.example.user.todolist.R;
import com.example.user.todolist.db.TaskDB;

public class EditTaskActivity extends AppCompatActivity {

    TaskDB taskDB;

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
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        prioritySetter.setAdapter(adapter);


        ImageButton submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

//                int spinnerOption = prioritySetter.getSelectedItem();

                selectedTask.setTaskName(taskNameEdit.getText().toString());
//                selectedTask.setPriority(spinnerOption);

                taskDB.updateTask(selectedTask);

                finish();
                Intent homepage = new Intent(EditTaskActivity.this, MainActivity.class);
                startActivity(homepage);
            }
        });

    }

}
