package com.example.app.DAO;

import android.content.Context;

import com.example.app.model.Remedio;

import java.util.ArrayList;

public interface RemedioDAOInterface {
    static RemedioDAOInterface getInstance(Context context) {
        return null;
    }

    boolean addRemedio( Remedio r );
    boolean editRemedio( Remedio r );
    boolean deleteRemedio( int remedioId );

    Remedio getRemedio( String remedioId);

    ArrayList<Remedio> getListaRemedios(String userId, String dia);

    boolean deleteAll();

    boolean init();
    boolean close();
    boolean isStarted();
}
