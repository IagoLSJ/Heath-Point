package com.example.app.model;

import java.util.ArrayList;

public class Cuidador extends User{
    private ArrayList<Idoso> idosos;

    public Cuidador(String uuid, String nome, String cpf, String email, String password) {
        super(uuid, nome, cpf, email, password);
        this.idosos = new ArrayList<>();
    }
}
