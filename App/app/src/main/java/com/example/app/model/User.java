package com.example.app.model;

public class User {
    private final String uuid;
    private final String nome;
    private final long cpf;
    private final String email;
    private final String password;
    private final  boolean isCaregiver;

    public User(String uuid, String nome, long cpf, String email, String password, boolean isCaregiver) {
        this.uuid = uuid;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.isCaregiver = isCaregiver;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNome() {
        return nome;
    }

    public long getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsCaregiver() {return isCaregiver; }
}
