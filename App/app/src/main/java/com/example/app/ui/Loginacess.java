package com.example.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.app.Homeuser;
import com.example.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class Loginacess extends AppCompatActivity {
    private EditText ETemail, ETsenha;
    private TextView msgError;
    private Button btn, btnConta;
    private Switch caregiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginacess);
        getSupportActionBar().hide();
        ETemail = findViewById(R.id.editTextTextEmailAddress);
        ETsenha = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.buttonentrar);
        btnConta = findViewById(R.id.buttonaindaloginacess);
        msgError = findViewById(R.id.msgError);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Logincadastro.class));
            }
        });


    }

    public void login() {
        String email = ETemail.getText().toString();
        String password =((EditText)findViewById(R.id.editTextTextPassword)).getText().toString();
        if(email.isEmpty()){
            this.ETsenha.setError("Este campo é obrigatório");
        }
        if(password.isEmpty()){
            this.ETsenha.setError("Este campo é obrigatório");
        }
        try {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.toLowerCase(Locale.ROOT), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    startActivity(new Intent(getApplicationContext(), Homeuser.class));
                }
            });
        } catch (Exception e) {
            msgError.setVisibility(View.VISIBLE);
        }

    }
}