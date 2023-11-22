package com.example.booking.login_user.data;

import com.example.booking.entities.Usuario;

import java.util.ArrayList;

public class MyDataLU {
    private String message;
    private ArrayList<Usuario> lstUsers;


    public String getMessage() {
        return message;
    }
    public ArrayList<Usuario> getLstUsers() {
        return lstUsers;
    }
    public void setLstUsers(ArrayList<Usuario> lstUsers) {
        this.lstUsers = lstUsers;
    }
}