package com.example.app;

import static com.google.android.gms.tasks.Tasks.await;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.os.AsyncTask;
import android.widget.Spinner;

import com.example.app.DAO.RemedioDAO;
import com.example.app.DAO.RemedioDAOInterface;
import com.example.app.model.Card;
import com.example.app.model.Remedio;
import com.example.app.model.RemedioAdpter;
import com.example.app.model.User;
import com.example.app.ui.AddDroug;
import com.example.app.ui.Chat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Homeuser extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
     RecyclerView seg, ter,qua,qui,sex,sab,dom;
     RemedioAdpter adpter;
     Button btnCuidador;
     RemedioDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeuser);
        getSupportActionBar().hide();
        dao = RemedioDAO.getInstance();
        ArrayList<Remedio> remedios = new ArrayList<Remedio>();
        dao.init();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        User user = new User();
        FirebaseFirestore.getInstance().collection("medicine")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    public void onComplete(Task<QuerySnapshot> task ) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String nome = document.getString( "nome");
                                String descricao = document.getString( "descricao" );
                                String hora = document.getString( "hora" );
                                String dia = document.getString( "dia" );
                                String userId = document.getString( "userId" );

                                Remedio r = new Remedio( userId, nome, descricao, hora, dia );
                                r.setUUID(document.getId());
                                remedios.add(r);

                            }
                        } else {
                            System.out.println("Erro na consulta");
                        }
                    }
                });

        remedios.add(new Remedio("fnoiewnfoiwnfoenfowneonfownf", "Teste", "remedio pra rato", "12","segunda"));

        seg = (RecyclerView) findViewById(R.id.seg);
        ter = (RecyclerView) findViewById(R.id.ter);
        qua = (RecyclerView) findViewById(R.id.qua);
        qui = (RecyclerView) findViewById(R.id.qui);
        sex = (RecyclerView) findViewById(R.id.sex);
        sab = (RecyclerView) findViewById(R.id.sab);
        dom = (RecyclerView) findViewById(R.id.dom);
        btnCuidador = findViewById(R.id.btnCuidador);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        seg.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ter.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        qua.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        qui.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        sex.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        sab.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dom.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        menu();
        adpter = new RemedioAdpter(remedios);
        seg.setAdapter(adpter);
        ter.setAdapter(adpter);
        qua.setAdapter(adpter);
        qui.setAdapter(adpter);
        sex.setAdapter(adpter);
        sab.setAdapter(adpter);
        dom.setAdapter(adpter);

        btnCuidador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Chat.class));
            }
        });
    }

    public void Idosos(String userId){
        final String[] cpfCuidador = new String[1];
       FirebaseFirestore.getInstance().collection("users").whereEqualTo("userId", userId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               for (QueryDocumentSnapshot document : task.getResult()) {
                   if (document.getBoolean("isCaregiver")){
                       cpfCuidador[0] = document.getString("cpf");
                   }
               }
           }
       });
//        final String[] cpfIdosos = new String[50];
//        FirebaseFirestore.getInstance().collection("takeCare").whereEqualTo("cpfCuidador", cpfCuidador[0])
//                        .get()
//                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                        if (task.isSuccessful()) {
//                                            int i =0;
//                                            for ( for (QueryDocumentSnapshot document : task.getResult()){
//                                                cpfIdosos[i] = document.getString("cpfIdoso");
//                                                i++;
//                                            }
//                                        }
//                                    }
//                                });
//        nomes.setOnItemSelectedListener(this);
//        String[] items;
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, items);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        nomes.setAdapter(adapter);
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
                    case R.id.addId:
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


    public ArrayList<Remedio> getRemedios(String userId){
        ArrayList<Remedio> remedios = new ArrayList<>();

        FirebaseFirestore.getInstance().collection("users").whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    public void onComplete( Task<QuerySnapshot> task ) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String userId = document.getString( "userId" );
                                String nome = document.getString( "nome");
                                String descricao = document.getString( "descricao" );
                                String horario = document.getString( "horario" );
                                String dia = document.getString( "dia" );
                                Remedio r = new Remedio( userId, nome, descricao, horario, dia );
                                remedios.add( r );
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

        return remedios;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}