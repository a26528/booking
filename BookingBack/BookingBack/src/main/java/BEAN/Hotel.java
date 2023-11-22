package BEAN;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Hotel {
    private int id_Hotel, reservas_Hotel;
    private String nombre_Hotel;

    public Hotel() {
    }
    public Hotel (int id_Hotel, String nombre_Hotel, int reservas_Hotel){
        this.id_Hotel = id_Hotel;
        this.nombre_Hotel = nombre_Hotel;
        this.reservas_Hotel = reservas_Hotel;
    }

    public int getId_Hotel() {
        return id_Hotel;
    }

    public void setId_Hotel(int id_Hotel) {
        this.id_Hotel = id_Hotel;
    }

    public int getReservas_Hotel() {
        return reservas_Hotel;
    }

    public void setReservas_Hotel(int reservas_Hotel) {
        this.reservas_Hotel = reservas_Hotel;
    }

    public String getNombre_Hotel() {
        return nombre_Hotel;
    }

    public void setNombre_Hotel(String nombre_Hotel) {
        this.nombre_Hotel = nombre_Hotel;
    }

    public static String toJsonData(MyDataVH10 hoteles) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.toJson(hoteles);
    }
    public static String toJson(ArrayList<Hotel> hoteles) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.toJson(hoteles);
    }
}
