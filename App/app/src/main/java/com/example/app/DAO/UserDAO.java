package com.example.app.DAO;

import com.example.app.model.User;

public class UserDAO implements UserDAOInterface{
    @Override
    public boolean addContato(User c) {
        return false;
    }

    @Override
    public boolean editContato(User c) {
        return false;
    }

    @Override
    public User getContato(String contatoId) {
        return null;
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
