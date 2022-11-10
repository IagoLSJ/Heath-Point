package com.example.app.model;

import java.util.ArrayList;

public class Remedio {
    private String id;
    private String nome;
    private String descricao;
    private String entervaloEmHoras;
    public Remedio(String nome, String descricao, String entervaloEmHoras) {
        this.nome = nome;
        this.descricao = descricao;
        this.entervaloEmHoras = entervaloEmHoras;
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

    public String getEntervaloEmHoras() {
        return entervaloEmHoras;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setEntervaloEmHoras(String entervaloEmHoras) {
        this.entervaloEmHoras = entervaloEmHoras;
    }
}
