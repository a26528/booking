package BEAN;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Usuario {
    private int id_Usuario;
    private String nombre_Usuario, username_Usuario, pass_Usuario;

    public Usuario(int id_Usuario, String nombre_Usuario, String username_Usuario, String pass_Usuario) {
        this.id_Usuario = id_Usuario;
        this.nombre_Usuario = nombre_Usuario;
        this.username_Usuario = username_Usuario;
        this.pass_Usuario = pass_Usuario;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_Usuario=" + id_Usuario +
                ", nombre_Usuario='" + nombre_Usuario + '\'' +
                ", username_Usuario='" + username_Usuario + '\'' +
                ", pass_Usuario='" + pass_Usuario + '\'' +
                '}';
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre_Usuario() {
        return nombre_Usuario;
    }

    public void setNombre_Usuario(String nombre_Usuario) {
        this.nombre_Usuario = nombre_Usuario;
    }

    public String getUsername_Usuario() {
        return username_Usuario;
    }

    public void setUsername_Usuario(String username_Usuario) {
        this.username_Usuario = username_Usuario;
    }

    public String getPass_Usuario() {
        return pass_Usuario;
    }

    public void setPass_Usuario(String pass_Usuario) {
        this.pass_Usuario = pass_Usuario;
    }

    public static String toJson(ArrayList<Usuario> usuarios) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.toJson(usuarios);
    }
    public static String objecttoJson(Usuario usuario) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.toJson(usuario);
    }
}
