package com.example.booking.ver_hotel10.presenter;

import android.content.Context;
import android.util.Log;

import com.example.booking.entities.Hotel;
import com.example.booking.entities.Usuario;
import com.example.booking.login_user.ContractLoginUser;
import com.example.booking.login_user.model.LoginUserModel;
import com.example.booking.ver_hotel10.ContractVerHotel10;
import com.example.booking.ver_hotel10.model.VerHotel10Model;

import java.util.ArrayList;

public class VerHotel10Presenter implements ContractVerHotel10.Presenter, ContractVerHotel10.Model.OnVerHotel10Listener {

    private ContractVerHotel10.View view;
    private ContractVerHotel10.Model model;

    public VerHotel10Presenter(ContractVerHotel10.View view, Context context){  // Agrega el parámetro Context
        this.view = view;
        model = new VerHotel10Model(this, context);  // Pasa el contexto al constructor del modelo
    }


    @Override
    public void ver() {
        Log.d("VerHotel10Presenter", "ver()");
        model.verAPI( this);
    }

    @Override
    public void onFinished(ArrayList<Hotel> hotelList) {
        view.successVer(hotelList);
    }

    @Override
    public void onFailure(String err) {

    }
}
