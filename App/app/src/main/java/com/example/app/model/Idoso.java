package com.example.app.model;

public class Idoso extends User {
    private Cuidador cuidador;

    public Idoso(String uuid, String nome, String cpf, String email, String password) {
        super(uuid, nome, cpf, email, password);
        this.cuidador = cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    @Override
    public String toString() {
        return "Idoso{" +
                "cuidador=" + cuidador +
                '}';
    }
}

