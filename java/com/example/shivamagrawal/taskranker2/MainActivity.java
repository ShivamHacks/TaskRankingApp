package com.example.shivamagrawal.taskranker2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_add:
                Intent intent = new Intent(this, AddTask.class);
                //startActivity(intent);
                startActivityForResult(intent, AddTaskRequestCode);
                return true;
            case R.id.action_main_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private final int AddTaskRequestCode = 10;
    private final int ChangeRankSettingsRequestCode = 11;

    public static String AddTaskDataKey = "newTask";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case AddTaskRequestCode:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    Task t = (Task) data.getParcelableExtra(AddTaskDataKey);
                    Log.d("GOT NEW TASK", t.getTask() + t.getTime()[0]);
                }
            case ChangeRankSettingsRequestCode:
                if (resultCode == Activity.RESULT_OK && data != null) {

                }
            default:

        }
    }

}