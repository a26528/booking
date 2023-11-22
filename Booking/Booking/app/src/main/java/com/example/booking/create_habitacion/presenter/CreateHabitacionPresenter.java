package com.example.booking.create_habitacion.presenter;

import android.content.Context;
import android.util.Log;

import com.example.booking.create_habitacion.ContractCreateHabitacion;
import com.example.booking.create_habitacion.ContractCreateHabitacion.Model.OnCreateHabitacionListener;
import com.example.booking.create_habitacion.model.CreateHabitacionModel;
import com.example.booking.entities.Habitacion;

public class CreateHabitacionPresenter implements ContractCreateHabitacion.Presenter, OnCreateHabitacionListener{

    private ContractCreateHabitacion.View view;
    private ContractCreateHabitacion.Model model;

    public CreateHabitacionPresenter(ContractCreateHabitacion.View view, Context context){
        this.view = view;
        model = new CreateHabitacionModel(this, context);
    }
    @Override
    public void create(Habitacion habitacion) {
        Log.d("CreateHabitacionPresenter", "Creando habitación: " + habitacion.getId_Habitacion());
        model.createAPI(habitacion, this);
    }

    @Override
    public void onFinished(Habitacion habitacion) {

    }

    @Override
    public void onFailure(String err) {

    }
}
