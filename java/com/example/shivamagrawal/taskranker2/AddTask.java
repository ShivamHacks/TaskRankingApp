package com.example.shivamagrawal.taskranker2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Button;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
// import android.util.Log;

public class AddTask extends AppCompatActivity implements OnTimeSetListener, OnDateSetListener {

    private Toolbar toolbar;

    private EditText taskInput;

    private Button setDate;
    private Button setTime;

    private SeekBar importanceBar;
    private SeekBar sizeBar;
    private SeekBar difficultyBar;
    private SeekBar completionBar;

    private int[] time;
    private int[] date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        toolbar = (Toolbar) findViewById(R.id.add_task_tool_bar);
        toolbar.setTitle("Add a Task");
        setSupportActionBar(toolbar);

        taskInput = (EditText) findViewById(R.id.task_desc);

        setDate = (Button) findViewById(R.id.pickDate);
        setTime = (Button) findViewById(R.id.pickTime);

        importanceBar = (SeekBar) findViewById(R.id.importance_slider);
        sizeBar = (SeekBar) findViewById(R.id.size_slider);
        difficultyBar = (SeekBar) findViewById(R.id.difficulty_slider);
        completionBar = (SeekBar) findViewById(R.id.completion_slider);

    }

    /* Main functions */

    public void submitTask(View v) {
        int importance = importanceBar.getProgress();
        int size = sizeBar.getProgress();
        int difficulty = difficultyBar.getProgress();
        int completion = completionBar.getProgress();
        String task = taskInput.getText().toString();
        if (time != null && date != null && task != null && task !="") {
            Task t = new Task(time, date, task, importance, size, difficulty, completion);
            Intent output = new Intent();
            output.putExtra(MainActivity.AddTaskDataKey, t);
            setResult(Activity.RESULT_OK, output);
            finish();
        } else {
            // show error
        }
    }

    /* Menu Functions */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task_back:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Date Picker */

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        date = new int[] { year, month, day };
        String formattedDate = Helper.formatDate(date);
        setDate.setText(formattedDate);
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /* Time Picker */

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time = new int[] { hourOfDay, minute };
        String formattedTime = Helper.formatTime(time);
        setTime.setText(formattedTime);
    }

    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
