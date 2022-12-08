package com.example.app.DAO;

import android.content.Context;

import com.example.app.model.User;

import java.util.ArrayList;

public interface UserDAOInterface {
    static UserDAOInterface getInstance(Context context) {
        return null;
    }

    boolean addContato( User c );
    boolean editContato( User c );
    User getContato( String contatoId );

    boolean init();
    boolean close();
    boolean isStarted();

}
