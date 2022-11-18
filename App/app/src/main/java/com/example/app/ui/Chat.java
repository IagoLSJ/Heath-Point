package com.example.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.app.R;
import com.example.app.model.ChatMessage;
import com.google.firebase.auth.FirebaseAuth;


public class Chat extends AppCompatActivity {
    private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_chat);



    }

}