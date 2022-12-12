package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.app.DAO.RemedioDAO;
import com.example.app.model.Remedio;
import com.example.app.model.RemedioEditAdpter;
import com.example.app.ui.AddDroug;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EditCard extends AppCompatActivity {
    static RemedioEditAdpter adapter;
     RecyclerView lista;
    RemedioDAO dao;
    private Homeuser home;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Remedio> remedios = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        lista = findViewById(R.id.list);
        menu();
        dao = RemedioDAO.getInstance(home);
        dao.init();
        String dia;
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(getIntent().hasExtra("dia")) {
            Bundle extras = getIntent().getExtras();
            remedios = getListaRemedios(userId, extras.get("dia").toString());
        }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            lista.setLayoutManager(layoutManager);

            adapter = new RemedioEditAdpter(this, remedios);

        lista.setAdapter(adapter);



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
                    case R.id.addId:
                        startActivity(new Intent(getApplicationContext(), AddDroug.class));
                        break;
                    case R.id.searchId:
                        startActivity(new Intent(getApplicationContext(), Maps.class));
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

    public void editar(Remedio r){
        Intent i = new Intent(getApplicationContext(), AddDroug.class);
        i.putExtra("Remedio.nome",r.getNome());
        i.putExtra("Remedio.descricao",r.getDescricao());
        i.putExtra("Remedio.horario",r.getHorario());
        i.putExtra("Remedio.dia",r.getDia());
        i.putExtra("Remedio.uuid", r.getUUID());

        startActivity(i);

    }

    public void delete(Remedio r){
       dao.deleteRemedio(r.getUUID(), remedios);
       notifyAdapter();
        Snackbar.make(findViewById(R.id.menu), "Remedio deletado com sucesso",
                        Snackbar.LENGTH_SHORT)
                .show();
    }


    public ArrayList<Remedio> getListaRemedios(String userId, String dia) {
        ArrayList<Remedio> remedios = new ArrayList<Remedio>();
        db.collection("medicine")
                .whereEqualTo("userId", userId)
                .whereEqualTo("dia", dia)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    public void onComplete( Task<QuerySnapshot> task ) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String nome = document.getString( "nome");
                                String descricao = document.getString( "descricao" );
                                String hora = document.getString( "horario" );
                                String dia = document.getString( "dia" );
                                String userId = document.getString( "userId" );
                                Remedio r = new Remedio( userId, nome, descricao, hora, dia );
                                r.setUUID(document.getId());
                                remedios.add(r);
                            }
                        }
                        notifyAdapter();
                    }
                });
        return remedios;
    }

}