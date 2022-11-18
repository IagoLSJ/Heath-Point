package com.example.app.model;

import java.util.ArrayList;

public class Remedio {
    private final String userId;
    private final String nome;
    private final String descricao;
    private final String Horario;
    private final String Dia;

    public Remedio(String userId, String nome, String descricao, String horario, String dia) {
        this.userId = userId;
        this.nome = nome;
        this.descricao = descricao;
        Horario = horario;
        Dia = dia;
    }

    public String getUserId() {
        return userId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHorario() {
        return Horario;
    }

    public String getDia() {
        return Dia;
    }

    @Override
    public String toString() {
        return "Remedio{" +
                "userId='" + userId + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", Horario='" + Horario + '\'' +
                ", Dia='" + Dia + '\'' +
                '}';
    }
}
