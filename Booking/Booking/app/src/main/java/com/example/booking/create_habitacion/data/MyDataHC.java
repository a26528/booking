package com.example.booking.create_habitacion.data;

import com.example.booking.entities.Habitacion;

import java.util.ArrayList;

public class MyDataHC {
    private String message;
    private ArrayList<Habitacion> lstHabitaciones;

    public String getMessage() {
        return message;
    }
    public ArrayList<Habitacion> getLstHabitaciones() {
        return lstHabitaciones;
    }
    public void setLstHabitaciones(ArrayList<Habitacion> lstHabitaciones) {
        this.lstHabitaciones = lstHabitaciones;
    }
}
