package com.example.app.model;

import android.content.Context;

import java.util.ArrayList;

public interface UserInterface {
    static UserInterface getInstance(Context context) { return null;}

    boolean addUser(User u);
    boolean editUser(User u);

    User getUser(String userId);
    boolean login(String email, String password);
    String getAuthUser();
    boolean init();
    boolean close();
    boolean isStarted();
}
