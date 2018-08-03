package com.example.student.mytasks.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.student.mytasks.R;
import com.example.student.mytasks.adapter.AdapterTasks;
import com.example.student.mytasks.provaider.UserProvaider;

public class TasksListActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;
    private int progress;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private AdapterTasks adapterTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rec_tasks);
        adapterTasks = new AdapterTasks(this, UserProvaider.getListTasks());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterTasks);
        progressBar = findViewById(R.id.progress);
        textView = findViewById(R.id.text_counter);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.INVISIBLE);
        incrementProgressBar();
        addTasks();
        removeItem();
    }


    private void incrementProgressBar() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < 100) {
                    progress++;
                    progressBar.setProgress(progress);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                               if (progress == 100 ) {
                                   textView.setText(String.valueOf(progress));
                                   textView.setVisibility(View.INVISIBLE);
                                   progressBar.setVisibility(View.INVISIBLE);
                                   floatingActionButton.setVisibility(View.VISIBLE);
                                   recyclerView.setVisibility(View.VISIBLE);
                               }
                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        Log.d("InterruptedException", e.getMessage());
                    }

                }
            }
        });
        thread.start();
    }

    private void addTasks() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogTasks dialogTasks = new DialogTasks(TasksListActivity.this);
                dialogTasks.show(getFragmentManager().beginTransaction(), "  ");
                recyclerView.setAdapter(new AdapterTasks(TasksListActivity.this, UserProvaider.getListTasks()));
            }
        });
    }


    private void removeItem() {

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                UserProvaider.getListTasks().remove(position);
                adapterTasks.notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


}


