package com.example.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.app.Homeuser;
import com.example.app.R;
import com.example.app.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;


public class Logincadastro extends AppCompatActivity {
    private EditText ETnome, ETcpf, ETemail, ETsenha;
    private Button btnCriarConta;
    private Switch caregiver;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincadastro);
        getSupportActionBar().hide();
        ETnome = findViewById(R.id.editTextTextPersonName);
        ETcpf = findViewById(R.id.editTextNumber);
        ETemail = findViewById(R.id.editTextTextEmailAddress);
        ETsenha = findViewById(R.id.editTextTextPassword);
        btnCriarConta = findViewById(R.id.buttocadastrar);
        caregiver = findViewById(R.id.caregiver);
        mAuth = FirebaseAuth.getInstance();

        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarConta();
            }
        });

    }

    public void criarConta(){

        String nome = ETnome.getText().toString();
        String cpf =  ETcpf.getText().toString();
        String email = ETemail.getText().toString();
        String password = ETsenha.getText().toString();

        if(nome.isEmpty()){
            this.ETnome.setError("Campo obrigatorio");
        }
        if(cpf.isEmpty()){
            this.ETcpf.setError("Campo obrigatorio");
        }if(email.isEmpty()){
            this.ETemail.setError("Campo obrigatorio");
        }
        if(password.isEmpty()){
            this.ETsenha.setError("Campo obrigatorio");
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    User user = new User(uuid, nome, cpf, email.toLowerCase(Locale.ROOT), password, caregiver.isChecked());
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("users").add(user);

                    startActivity(new Intent(getApplicationContext(), Homeuser.class));
                }
            }
        });
    }
}