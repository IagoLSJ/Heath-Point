package com.example.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app.R;
import com.example.app.model.RemedioAdpter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class listMedicine extends AppCompatActivity {
    private RecyclerView list;
    private RemedioAdpter adpter;

    private FloatingActionButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medicine);
        getSupportActionBar().hide();
        list = (RecyclerView) findViewById(R.id.seg);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        list.setAdapter(adpter);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(listMedicine.this, CreateMedicine.class));
            }
        });
    }
}