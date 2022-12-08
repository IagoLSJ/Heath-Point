package com.example.app.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private  String uuid;
    private  String nome;
    private  String cpf;
    private  String email;
    private  String password;
    private String documentId;

    public User(String uuid, String nome, String cpf, String email, String password) {
        this.uuid = uuid;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }
}
