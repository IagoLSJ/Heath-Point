package com.example.app.DAO;

import androidx.annotation.NonNull;

import com.example.app.model.Remedio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemedioDAO {
    private ArrayList<Remedio> remedios = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static RemedioDAO remedioDAO;
    private List<Remedio> remedioList;
    private RemedioDAO(){
        remedioList = new ArrayList<>();
    }
    public static RemedioDAO getInstance(){
        if (remedioDAO == null){
            remedioDAO = new RemedioDAO();
        }
        return remedioDAO;
    }

    public void insert(Remedio r){
        try {
            db.collection("medicine").add(r);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void remove(Remedio r){
        for(int i = 0; i< remedios.size(); i++){
            if(remedios.get(i).getId() == r.getId()){
                remedios.remove(i);
            };
        }
    }

    public List<Remedio> list(){
        db.collection("medicine").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Remedio remedio = new Remedio((String) document.get("nome"), (String) document.get("descricao"), (String) document.get("entervaloEmHoras"));
                        remedio.setId((String) document.getId());
                        remedioList.add(remedio);
                    }
                }
            }
        });

        return remedioList;
    }

}
