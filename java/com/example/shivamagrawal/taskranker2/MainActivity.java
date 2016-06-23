package com.example.shivamagrawal.taskranker2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private CustomCardViewList.CardViewAdapter mAdapter;
    private List<Task> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_activity_tool_bar);
        setSupportActionBar(toolbar);

        mTasks = new ArrayList<Task>();

        CustomCardViewList.OnItemTouchListener itemTouchListener = new CustomCardViewList.OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                Toast.makeText(MainActivity.this, "Tapped " + mTasks.get(position).getTask(), Toast.LENGTH_SHORT).show();
            }
        };

        CustomCardViewList customCardViewList = new CustomCardViewList(mTasks, itemTouchListener);
        mAdapter = customCardViewList.adapter;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener = new SwipeableRecyclerViewTouchListener(recyclerView, new SwipeableRecyclerViewTouchListener.SwipeListener() {
            @Override
            public boolean canSwipeLeft(int position) {
                return true;
            }

            @Override
            public boolean canSwipeRight(int position) {
                return true;
            }

            @Override
            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    mTasks.remove(position);
                    mAdapter.notifyItemRemoved(position);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    mTasks.remove(position);
                    mAdapter.notifyItemRemoved(position);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.addOnItemTouchListener(swipeTouchListener);

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
                    Task t = data.getParcelableExtra(AddTaskDataKey);
                    Log.d("GOT NEW TASK", t.getTask() + t.getTime()[0]);
                    mTasks.add(t);
                    mAdapter.notifyDataSetChanged();
                }
            case ChangeRankSettingsRequestCode:
                if (resultCode == Activity.RESULT_OK && data != null) {

                }
            default:

        }
    }

}