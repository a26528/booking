package com.example.booking.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Habitacion {
    private int id_Habitacion, id_Habitacion_Hotel;
    private double precio_Habitacion;
    private String descripcion_Habitacion, imagen_Habitacion, estado_Habitacion;

    public Habitacion() {
    }

    public Habitacion(int id_Habitacion, int id_Habitacion_Hotel, double precio_Habitacion, String descripcion_Habitacion, String imagen_Habitacion, String estado_Habitacion) {
        this.id_Habitacion = id_Habitacion;
        this.id_Habitacion_Hotel = id_Habitacion_Hotel;
        this.precio_Habitacion = precio_Habitacion;
        this.descripcion_Habitacion = descripcion_Habitacion;
        this.imagen_Habitacion = imagen_Habitacion;
        this.estado_Habitacion = estado_Habitacion;
    }

    public int getId_Habitacion() {
        return id_Habitacion;
    }

    public void setId_Habitacion(int id_Habitacion) {
        this.id_Habitacion = id_Habitacion;
    }

    public int getId_Habitacion_Hotel() {
        return id_Habitacion_Hotel;
    }

    public void setId_Habitacion_Hotel(int id_Habitacion_Hotel) {
        this.id_Habitacion_Hotel = id_Habitacion_Hotel;
    }

    public double getPrecio_Habitacion() {
        return precio_Habitacion;
    }

    public void setPrecio_Habitacion(double precio_Habitacion) {
        this.precio_Habitacion = precio_Habitacion;
    }

    public String getDescripcion_Habitacion() {
        return descripcion_Habitacion;
    }

    public void setDescripcion_Habitacion(String descripcion_Habitacion) {
        this.descripcion_Habitacion = descripcion_Habitacion;
    }

    public String getImagen_Habitacion() {
        return imagen_Habitacion;
    }

    public void setImagen_Habitacion(String imagen_Habitacion) {
        this.imagen_Habitacion = imagen_Habitacion;
    }

    public String getEstado_Habitacion() {
        return estado_Habitacion;
    }

    public void setEstado_Habitacion(String estado_Habitacion) {
        this.estado_Habitacion = estado_Habitacion;
    }

    public static String toJson(ArrayList<Habitacion> habitaciones) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.toJson(habitaciones);
    }
}
