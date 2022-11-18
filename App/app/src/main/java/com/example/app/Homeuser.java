package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.app.model.Card;
import com.example.app.model.Remedio;
import com.example.app.model.RemedioAdpter;
import com.example.app.ui.AddDroug;
import com.example.app.ui.Chat;
import com.example.app.ui.TelaInicial;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Homeuser extends AppCompatActivity {
    private RecyclerView list;
    private RemedioAdpter adpter;
    public ArrayList<Remedio> remedios = new ArrayList<Remedio>();
    private Button btnCuidador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeuser);
        getSupportActionBar().hide();
        list = (RecyclerView) findViewById(R.id.ListRemedios);
        btnCuidador = findViewById(R.id.btnCuidador);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list.setLayoutManager(layoutManager);
        fetchMedicine(userId);
        menu();
//        Segunda
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
//        Terça
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        remedios.add(new Remedio(userId, "Teste", "qualquer coisa", "8:00", "10"));
        Card card = new Card("Segunda", remedios);
        ArrayList<Card> cards = new ArrayList<>();
        adpter = new RemedioAdpter(cards);
        list.setAdapter(adpter);

        btnCuidador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Chat.class));
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

    private void fetchMedicine(String userId) {
        FirebaseFirestore.getInstance().collection("medicine").whereEqualTo("userId", userId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot doc : task.getResult()){
                        if(!doc.getData().isEmpty()){
                            Log.e("nome", String.valueOf(doc.getData()));
                            Remedio r = new Remedio(userId, (String) doc.get("nome"), (String) doc.get("descricao"), (String) doc.get("horario"), (String) doc.get("dia"));
                            Log.e("Remedio", r.toString());
                            remedios.add(r);
                        }

                    }
                }
            }
        });
    }
}