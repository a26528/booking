package com.example.booking.filtrar_hotel.data;

import com.example.booking.entities.Hotel;

import java.util.ArrayList;

public class MyDataFH {
    private String message;
    private ArrayList<Hotel> listHotel;
    private String jsonResponse;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Hotel> getListHotel() {
        return listHotel;
    }

    public void setListHotel(ArrayList<Hotel> listHotel) {
        this.listHotel = listHotel;
    }
    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
    public String getJsonResponse() {
        return jsonResponse;
    }
}
