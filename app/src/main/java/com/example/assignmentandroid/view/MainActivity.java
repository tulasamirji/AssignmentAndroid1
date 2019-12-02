package com.example.assignmentandroid.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.assignmentandroid.adpater.FactAdapter;
import com.example.assignmentandroid.model.Fact;
import com.example.assignmentandroid.R;
import com.example.assignmentandroid.viewmodel.FactViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FactAdapter factAdapter;
    private SwipeRefreshLayout swipeRefreshView;
    private ActionBar toolbar;
    private FactViewModel factViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View Initializations
        initializationViews();

        factViewModel = ViewModelProviders.of(this).get(FactViewModel.class);

        //Get data from remote server
        factViewModel.getFacts();
        //Get data from Room database
        getFacts_Offline();

        //call to refresh the data on swipe
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFacts_Offline();
            }
        });


    }

    private void initializationViews() {
        recyclerView = findViewById(R.id.recyclerview);
        swipeRefreshView = findViewById(R.id.swiperefresh);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar = getSupportActionBar();
        toolbar.setTitle("About Canada");
    }

   //call to get data from room database
    private void getFacts_Offline() {
        swipeRefreshView.setRefreshing(true);
        factViewModel.getAllQuestions().observe(this, new Observer<List<Fact>>() {
            @Override
            public void onChanged(List<Fact> factList) {
                swipeRefreshView.setRefreshing(false);
                factAdapter = new FactAdapter(MainActivity.this, factList);
                recyclerView.setAdapter(factAdapter);
            }
        });
    }
}