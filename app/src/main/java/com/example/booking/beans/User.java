package com.example.booking.beans;

public class User {
    private String username;
    private String  pass;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String pass, String email) {
        this.username = username;
        this.pass = pass;
        this.email = email;
    }

    public void setToken(String s) {
    }
    public  User(){}
}
