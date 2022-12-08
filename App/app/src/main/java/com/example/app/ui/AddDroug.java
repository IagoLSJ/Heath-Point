package com.example.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.app.DAO.RemedioDAO;
import com.example.app.DAO.RemedioDAOInterface;
import com.example.app.Homeuser;
import com.example.app.PerfilannyUser;
import com.example.app.R;
import com.example.app.model.Remedio;
import com.example.app.model.RemedioEditAdpter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.common.subtyping.qual.Bottom;

public class AddDroug extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private String nome;
    static RemedioEditAdpter adapter;
    private String descricao;
    private String horario;
    private Button btn;
    private Homeuser home;
    RemedioDAOInterface dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);
        getSupportActionBar().hide();
        menu();
        dao = RemedioDAO.getInstance(home);
        dao.init();
        btn = findViewById(R.id.btn);
        boolean isEdit = false;
        EditText nomeET = findViewById(R.id.input_name);
        EditText descricaoET =  findViewById(R.id.input_descricao);
        EditText horarioET = findViewById(R.id.input_horario);
        final Spinner dia = ((Spinner) findViewById(R.id.input_dia));
        if(getIntent().hasExtra("Remedio.dia")) {
            Bundle extras = getIntent().getExtras();
            nomeET.setText(extras.get("Remedio.nome").toString());
            descricaoET.setText(extras.get("Remedio.descricao").toString());
            horarioET.setText(extras.get("Remedio.horario").toString());
            isEdit = true;
            btn.setText("Editar");
        }


        dia.setOnItemSelectedListener(this);
        String[] items = new String[]{"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dia.setAdapter(adapter);

        boolean finalIsEdit = isEdit;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = ((EditText) findViewById(R.id.input_name)).getText().toString();
                descricao = ((EditText) findViewById(R.id.input_descricao)).getText().toString();
                horario = ((EditText) findViewById(R.id.input_horario)).getText().toString();
                if(nome.isEmpty() || descricao.isEmpty() || horario.isEmpty()){
                    Snackbar.make(findViewById(R.id.menu), "Preencha todos os campos",
                                    Snackbar.LENGTH_SHORT)
                            .show();
                }
                Remedio remedio = new Remedio(FirebaseAuth.getInstance().getCurrentUser().getUid(), nome,descricao,horario,dia.getSelectedItem().toString());
               if (finalIsEdit){
                   dao.editRemedio(remedio);
                   startActivity(new Intent(getApplicationContext(), Homeuser.class));
               }else {
                   dao.addRemedio(remedio);
                   startActivity(new Intent(getApplicationContext(), Homeuser.class));
               }
            }
        });
    }

    public static void notifyAdapter(){
        adapter.notifyDataSetChanged();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}