package com.example.app.model;

import java.util.ArrayList;

public class Remedio {
    private String UUID;
    private String userId;
    private String nome;
    private String descricao;
    private String Horario;
    private String Dia;

    public Remedio(String userId, String nome, String descricao, String horario, String dia) {
        this.userId = userId;
        this.nome = nome;
        this.descricao = descricao;
        Horario = horario;
        Dia = dia;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String dia) {
        Dia = dia;
    }

}
