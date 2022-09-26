package com.harshithajp.catapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class recycler extends AppCompatActivity {

        String []data = {"Basketball", "Cricket", "Tennis", "Carrom", " VolleyBall", "Chess", "Badminton"};
        int count;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recycler);


            //------- adding items
            List<String> items = new LinkedList<>();


            RecyclerView RV = findViewById((R.id.recyclerView));
            RV.setLayoutManager(new LinearLayoutManager(this));
            DemoAdapter adapter = new DemoAdapter(items);
            RV.setAdapter(adapter);


            findViewById(R.id.btnAddid).setOnClickListener(view ->
            {
                            items.add(data[count % 7]);
                            count++;
                            adapter.notifyItemInserted(items.size() - 1);
            });
        }
    }