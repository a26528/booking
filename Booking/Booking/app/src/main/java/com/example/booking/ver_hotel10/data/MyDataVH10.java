package com.example.booking.ver_hotel10.data;

import com.example.booking.entities.Hotel;

import java.util.ArrayList;

public class MyDataVH10 {
    private String message;
    private ArrayList<Hotel> listHotel;


    public String getMessage() {
        return message;
    }
    public ArrayList<Hotel> getLstHotel() {
        return listHotel;
    }
    public void setLstHotel(ArrayList<Hotel> listHotel) {
        this.listHotel = listHotel;
    }
}