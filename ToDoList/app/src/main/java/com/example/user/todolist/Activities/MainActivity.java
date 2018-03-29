package com.example.user.todolist.Activities;

import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.user.todolist.Model.Task;
import com.example.user.todolist.R;
import com.example.user.todolist.TaskArrayAdapter;
import com.example.user.todolist.db.TaskDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TaskDB taskDB;
    private ListView taskListView;
    ArrayList<Task> taskList1, taskList2, taskList;
    private DrawerLayout mDrawerLayout;
    UIUpdater UI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskDB = new TaskDB(this);
        taskListView = findViewById(R.id.listView);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        updateUI();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        mDrawerLayout.closeDrawers();

                        if (item.getItemId() == R.id.today){
                            updateUIForToday();
                        } else if (item.getItemId() == R.id.all_tasks) {
                            updateUI();
                        } else if (item.getItemId() == R.id.tomorrow){
                            updateUIForTomorrow();
                        } else if (item.getItemId() == R.id.next_7_days){
                            updateUIForWeek();
                        }

                        item.setChecked(true);

                        return true;
                    }
                }
        );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.all_tasks:
                updateUI();
                item.setChecked(true);
            case R.id.today:
                updateUIForToday();
                item.setChecked(true);
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

                        String date = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()).format(new Date());

                        Task task = new Task (taskEditText.getText().toString(), 1, date);
                        if (taskEditText.getText().toString().equals("")){
                            taskEditText.requestFocus();
                        } else {
                            taskDB.addTask(task);
                            updateUIForToday();
                        }
                        Log.d("date", String.valueOf(task.getDate()));
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


    public void updateUIForToday(){
        taskList1 = taskDB.findAllForToday();
        TaskArrayAdapter arrayAdapter = new TaskArrayAdapter(this, taskList1);
        taskListView.setAdapter(arrayAdapter);
    }

    public void updateUI(){
        taskList2 = taskDB.getAllTasks();
        TaskArrayAdapter arrayAdapter = new TaskArrayAdapter(this, taskList2);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        LayoutInflater inflater = getLayoutInflater();
//        LinearLayout container = (LinearLayout) findViewById(R.id.content_frame);
//        inflater.inflate(R.layout.activity_main, container);
//        getMenuInflater().inflate(R.menu.drawer_view, menu);
//        return true;
//    }

}