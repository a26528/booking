package BEAN;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Hotel {
    private int id_Hotel, reservas_Hotel;

    private String nombre_Hotel,imagen_Hotel,localizacion_Hotel;
    private double puntuacion_Hotel;
    public Hotel() {
    }
    public Hotel (int id_Hotel, String nombre_Hotel, int reservas_Hotel,String imagen_Hotel, String localizacion_Hotel){
        this.id_Hotel = id_Hotel;
        this.nombre_Hotel = nombre_Hotel;
        this.reservas_Hotel = reservas_Hotel;
        this.imagen_Hotel= imagen_Hotel;
        this.localizacion_Hotel = localizacion_Hotel;

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
    public void setImagen_Hotel(String imagen_Hotel) {
        this.imagen_Hotel = imagen_Hotel;
    }

    public void setLocalizacion_Hotel(String localizacion_Hotel) {
        this.localizacion_Hotel = localizacion_Hotel;
    }

    public String getImagen_Hotel() {
        return imagen_Hotel;
    }

    public String getLocalizacion_Hotel() {
        return localizacion_Hotel;
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

    public void setPuntuacion_Hotel(double puntuacionHotel) {
        this.puntuacion_Hotel = puntuacionHotel;
    }

    public double getPuntuacion_Hotel() {
        return puntuacion_Hotel;
    }
}
