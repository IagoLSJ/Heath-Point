package com.example.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Loginacess extends AppCompatActivity {
    private EditText ETemail, ETsenha;
    private TextView msgError;
    private Button btn, btnConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginacess);
        ETemail = findViewById(R.id.editTextTextEmailAddress);
        ETsenha = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.buttonentrar);
        btnConta = findViewById(R.id.buttonaindaloginacess);
        msgError = findViewById(R.id.msgError);
        String email = ETemail.getText().toString();
        String senha = ETsenha.getText().toString();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(email, senha);
            }
        });

        btnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Logincadastro.class));
            }
        });


    }

    public void login(String email, String senha) {
        if(email.equals("") || email.equals(" ")){
            this.ETsenha.setError("Este campo é obrigatório");
        }
        if(senha.equals("") || senha.equals(" ")){
            this.ETsenha.setError("Este campo é obrigatório");
        }
        try {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    startActivity(new Intent(getApplicationContext(), listMedicine.class));
                }
            });
        } catch (Exception e) {
            msgError.setVisibility(View.VISIBLE);
        }

    }
}