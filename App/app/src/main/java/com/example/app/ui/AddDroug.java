package com.example.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app.Homeuser;
import com.example.app.PerfilannyUser;
import com.example.app.R;
import com.example.app.model.Remedio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.common.subtyping.qual.Bottom;

public class AddDroug extends AppCompatActivity {
    private String nome;
    private String descricao;
    private String horario;
    private String dia;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);
        getSupportActionBar().hide();
        menu();
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = ((EditText) findViewById(R.id.input_name)).getText().toString();
                descricao = ((EditText) findViewById(R.id.input_descricao)).getText().toString();
                horario = ((EditText) findViewById(R.id.input_horario)).getText().toString();
                dia = ((EditText) findViewById(R.id.input_dia)).getText().toString();

                Remedio remedio = new Remedio(FirebaseAuth.getInstance().getCurrentUser().getUid(), nome,descricao,horario,dia);

                FirebaseFirestore.getInstance().collection("medicine").add(remedio).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), Homeuser.class));
                        }
                    }
                });
            }
        });
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