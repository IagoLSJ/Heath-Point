package com.example.app.DAO;

import android.app.Activity;
import android.content.Context;

import com.example.app.EditCard;
import com.example.app.Homeuser;
import com.example.app.model.Remedio;
import com.example.app.ui.AddDroug;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RemedioDAO implements RemedioDAOInterface{
    private static RemedioDAO dao = null;
    private static Homeuser home;
    private FirebaseFirestore db;

    ArrayList<Remedio> lista;

    private RemedioDAO(Homeuser home){
        RemedioDAO.home = home;
        lista = new ArrayList<>();
    }

    public static RemedioDAO getInstance(Homeuser home) {

        if( dao == null ){
            dao = new RemedioDAO(home);
        }
        return dao;
    }

    @Override
    public boolean addRemedio(Remedio r) {

        Map<String, Object> remedio = new HashMap<>();
        remedio.put("userId", r.getUserId());
        remedio.put("nome", r.getNome() );
        remedio.put("descricao", r.getDescricao() );
        remedio.put("horario", r.getHorario() );
        remedio.put("dia", r.getDia() );

        db.collection("medicine")
                .add(remedio)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    public void onSuccess(DocumentReference documentReference) {
                        r.setUUID( documentReference.getId());
                        lista.add( r );
                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    public void onFailure( Exception e) {
                        System.out.println("Erro:" + e.toString());
                    }
                });

        return true;
    }

    @Override
    public boolean editRemedio(Remedio r) {
        DocumentReference newRemedio = db.collection("medicine")
                .document(r.getUUID());
        newRemedio.update("nome", r.getNome(),
                        "descricao", r.getDescricao(),
                        "horario", r.getHorario(),
                        "dia", r.getDia(),
                        "userId", r.getUserId() )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess( Void aVoid  ) {

                        for( Remedio remedio : lista ){

                            if( remedio.getUUID().equals( r.getUUID() ) ){
                                remedio.setNome( r.getNome() );
                                remedio.setDescricao( r.getDescricao() );
                                remedio.setHorario( r.getHorario());
                                remedio.setDia( r.getDia() );
                                remedio.setUserId( r.getUserId() );

                                AddDroug.notifyAdapter();

                                break;

                            }
                        }

                        AddDroug.notifyAdapter();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        AddDroug.notifyAdapter();
                    }
                });
        return false;
    }

    @Override
    public boolean deleteRemedio(String remedioId) {

        Remedio r = null;

        for( Remedio remedio : lista ){
            if( remedio.getUUID().equals(remedioId)){
                r = remedio;
                break;
            }
        }

        if( r != null ){

            final Remedio deletar = r;

            DocumentReference deletarRemedio = db.collection("medicine").document( r.getUUID() );
            deletarRemedio.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Remedio remedioDeletar = null;
                            for( Remedio remedio : lista ){

                                if( remedio.getUUID().equals( deletar.getUUID() ) ){
                                    remedioDeletar = remedio;
                                    break;
                                }

                            }

                            if( remedioDeletar != null ) lista.remove( remedioDeletar );
                            EditCard.notifyAdapter();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure( Exception e) {


                        }
                    });

        }

        return false;
    }

    @Override
    public Remedio getRemedio(String remedioId) {
        return null;
    }

    @Override
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

                                remedios.add(r);
                            }
                        }
                        home.notifyAdapter();
                    }
                });
        return remedios;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean init() {
        if( db == null ) db = FirebaseFirestore.getInstance();
        return true;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
