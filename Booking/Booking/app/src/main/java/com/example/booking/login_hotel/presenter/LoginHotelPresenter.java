package com.example.booking.login_hotel.presenter;

import android.content.Context;
import android.util.Log;

import com.example.booking.entities.Hotel;
import com.example.booking.login_hotel.ContractLoginHotel;
import com.example.booking.login_hotel.model.LoginHotelModel;

public class LoginHotelPresenter implements ContractLoginHotel.Presenter, ContractLoginHotel.Model.OnLoginHotelListener {

    private ContractLoginHotel.View view;
    private ContractLoginHotel.Model model;

    public LoginHotelPresenter(ContractLoginHotel.View view, Context context){
        this.view = view;
        model = new LoginHotelModel(this, context);
    }

    @Override
    public void loginHotel(int id , String nombre) {
        Log.e("LoginHotelPresenter", "login");
        model.loginAPI(id, nombre, this );
    }

    @Override
    public void onFinished(Hotel hotel) {

    }

    @Override
    public void onFailure(String err) {

    }
}
