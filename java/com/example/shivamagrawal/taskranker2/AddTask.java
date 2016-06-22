package com.example.shivamagrawal.taskranker2;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Button;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.app.DatePickerDialog.OnDateSetListener;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
// import android.util.Log;

public class AddTask extends AppCompatActivity implements OnTimeSetListener, OnDateSetListener {

    private Toolbar toolbar;
    private Button setDate;
    private Button setTime;

    private int[] time;
    private int[] date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        setDate = (Button) findViewById(R.id.pickDate);
        setTime = (Button) findViewById(R.id.pickTime);
    }

    /* Main functions */

    private void done() {

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
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String formattedDate = dateInstance.format(calendar.getTime());
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        DateFormat timeInstance = SimpleDateFormat.getTimeInstance(DateFormat.SHORT);
        String formattedTime = timeInstance.format(calendar.getTime());
        setTime.setText(formattedTime);
    }

    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
