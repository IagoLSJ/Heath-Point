package com.example.app.DAO;

import com.example.app.Homeuser;
import com.example.app.model.Remedio;
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

    public static RemedioDAO getInstance() {

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
        remedio.put( "horario", r.getHorario() );
        remedio.put( "dia", r.getDia() );

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
        return false;
    }

    @Override
    public boolean deleteRemedio(int remedioId) {
        return false;
    }

    @Override
    public Remedio getRemedio(String remedioId) {
        return null;
    }

    @Override
    public ArrayList<Remedio> getListaRemedios() {
        ArrayList<Remedio> remedios = new ArrayList<Remedio>();

        db.collection("medicine")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    public void onComplete( Task<QuerySnapshot> task ) {
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
                        Homeuser.notifyAdapter();
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
