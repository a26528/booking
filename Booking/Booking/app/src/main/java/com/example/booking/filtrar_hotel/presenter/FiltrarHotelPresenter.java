package com.example.booking.filtrar_hotel.presenter;

import android.content.Context;

import com.example.booking.entities.Hotel;
import com.example.booking.filtrar_hotel.ContractFiltrarHotel;
import com.example.booking.filtrar_hotel.model.FiltrarHotelModel;

import java.util.ArrayList;

public class FiltrarHotelPresenter implements ContractFiltrarHotel.Presenter, ContractFiltrarHotel.Model.OnFiltrarHotelListener{

    private ContractFiltrarHotel.View view;
    private ContractFiltrarHotel.Model model;

    public FiltrarHotelPresenter(ContractFiltrarHotel.View view, Context context){
        this.view = view;
        model = new FiltrarHotelModel(this, context);
    }
    @Override
    public void filtrarHotel(String nombre, int orden, int puntuacion) {
        model.filtrarAPI(nombre, orden, puntuacion, this);
    }

    @Override
    public void onFinished(ArrayList<Hotel> hotelList) {
        view.successFiltrar(hotelList);
    }

    @Override
    public void onFailure(String err) {

    }
}
