package com.example.booking.login_user.data;

import com.example.booking.entities.Usuario;

import java.util.ArrayList;

public class MyDataLU {

    private String message;
    private ArrayList<Usuario> lstUsers;

    private String jsonResponse; // Agregar este campo para almacenar la cadena JSON de la respuesta

    public ArrayList<Usuario> getLstUsers() {
        return lstUsers;
    }
    public void setLstUsers(ArrayList<Usuario> lstUsers) {
        this.lstUsers = lstUsers;
    }
    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}