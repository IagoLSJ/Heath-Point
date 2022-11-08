package com.example.app.DAO;

import androidx.annotation.NonNull;

import com.example.app.model.Remedio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RemedioDAO {
    private ArrayList<Remedio> remedios = new ArrayList<>();
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
     remedios.add(r);
    }

    public void remove(Remedio r){
        for(int i = 0; i< remedios.size(); i++){
            if(remedios.get(i) == r){
                remedios.remove(i);
            };
        }
    }

    public ArrayList<Remedio> list(){
        return remedios;
    }

}
