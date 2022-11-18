package com.example.app.model;

import java.util.ArrayList;

public class Card {
    private String dia;
    private ArrayList<Remedio> remedios;

    public Card(String dia, ArrayList<Remedio> remedios) {
        this.dia = dia;
        this.remedios = remedios;
    }


}
