package com.example.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.app.Homeuser;
import com.example.app.PerfilannyUser;
import com.example.app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AddDroug extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);
        menu();

    }

    public void menu() {
        BottomNavigationView menu;
        menu = findViewById(R.id.menu);
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeId:
                        startActivity(new Intent(getApplicationContext(), Homeuser.class));
                        break;
                    case R.id.addId :
                        startActivity(new Intent(getApplicationContext(), AddDroug.class));
                        break;
                    case R.id.searchId:
                        startActivity(new Intent(getApplicationContext(), Homeuser.class));
                        break;
                    case R.id.profileId:
                        startActivity(new Intent(getApplicationContext(), PerfilannyUser.class));
                    default:
                        return false;
                }
                return false;
            }
        });
    }
}