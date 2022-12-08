package com.example.app;

import static com.google.android.gms.tasks.Tasks.await;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
     ImageView btnEditSeg, btnEditTer, btnEditQua,btnEditQui, btnEditSex,btnEditSab, btnEditDom;
     Button btnCuidador;
     RemedioDAOInterface dao;
     ArrayList<Remedio> remediosSeg, remediosTer, remediosQua, remediosQui, remediosSex, remediosSab, remediosDom = new ArrayList<>();
    static RemedioAdpter adapter, adapterSeg, adapterTer, adapterQua, adapterQui, adapterSex, adapterSab, adapterDom;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeuser);
        getSupportActionBar().hide();

        dao = RemedioDAO.getInstance(this);
        dao.init();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        seg = findViewById(R.id.seg);
        ter = findViewById(R.id.ter);
        qua = findViewById(R.id.qua);
        qui = findViewById(R.id.qui);
        sex =  findViewById(R.id.sex);
        sab =  findViewById(R.id.sab);
        dom =  findViewById(R.id.dom);

        btnCuidador = findViewById(R.id.btnCuidador);

        btnEditSeg = findViewById(R.id.btn_edit_seg);
        btnEditTer = findViewById(R.id.btn_edit_ter);
        btnEditQua = findViewById(R.id.btn_edit_qua);
        btnEditQui = findViewById(R.id.btn_edit_qui);
        btnEditSex = findViewById(R.id.btn_edit_sex);
        btnEditSab = findViewById(R.id.btn_edit_sab);
        btnEditDom = findViewById(R.id.btn_edit_dom);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        seg.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ter.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        qua.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        qui.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        sex.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        sab.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dom.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        menu();

        remediosSeg = dao.getListaRemedios(userId, "Segunda");
        remediosTer = dao.getListaRemedios(userId, "Terça");
        remediosQua = dao.getListaRemedios(userId, "Quarta");
        remediosQui = dao.getListaRemedios(userId, "Quinta");
        remediosSex = dao.getListaRemedios(userId, "Sexta");
        remediosSab = dao.getListaRemedios(userId, "Sabado");
        remediosDom = dao.getListaRemedios(userId, "Domingo");

        adapterSeg = new RemedioAdpter(remediosSeg);
        adapterTer = new RemedioAdpter(remediosTer);
        adapterQua = new RemedioAdpter(remediosQua);
        adapterQui = new RemedioAdpter(remediosQui);
        adapterSex = new RemedioAdpter(remediosSex);
        adapterSab = new RemedioAdpter(remediosSab);
        adapterDom = new RemedioAdpter(remediosDom);

        seg.setAdapter(adapterSeg);
        ter.setAdapter(adapterTer);
        qua.setAdapter(adapterQua);
        qui.setAdapter(adapterQui);
        sex.setAdapter(adapterSex);
        sab.setAdapter(adapterSab);
        dom.setAdapter(adapterDom);

        btnCuidador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), .class));
            }
        });

        btnEditSeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditCard.class);
                intent.putExtra("dia", "Segunda");
                startActivity(intent);
            }
        });

        btnEditTer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditCard.class);
                intent.putExtra("dia", "Terça");
                startActivity(intent);
            }
        });

        btnEditQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditCard.class);
                intent.putExtra("dia", "Quart");
                startActivity(intent);
            }
        });

        btnEditQui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditCard.class);
                intent.putExtra("dia", "Quinta");
                startActivity(intent);
            }
        });

        btnEditSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditCard.class);
                intent.putExtra("dia", "Sexta");
                startActivity(intent);
            }
        });

        btnEditSab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditCard.class);
                intent.putExtra("dia", "Sábado");
                startActivity(intent);
            }
        });

        btnEditDom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditCard.class);
                intent.putExtra("dia", "Domingo");
                startActivity(intent);
            }
        });

    }

    public static void notifyAdapter(){
        adapterSeg.notifyDataSetChanged();
    }

    public void menu() {
        BottomNavigationView menu;
        menu = findViewById(R.id.menu);
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeId:
                    case R.id.searchId:
                        startActivity(new Intent(getApplicationContext(), Homeuser.class));
                        break;
                    case R.id.addId:
                        startActivity(new Intent(getApplicationContext(), AddDroug.class));
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