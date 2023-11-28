package com.example.booking.filtrar_hotel.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.booking.entities.Hotel;
import com.example.booking.filtrar_hotel.ContractFiltrarHotel;
import com.example.booking.filtrar_hotel.data.MyDataFH;
import com.example.booking.filtrar_hotel.presenter.FiltrarHotelPresenter;
import com.example.booking.utils.ApiService;
import com.example.booking.utils.RetrofitCliente;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltrarHotelModel implements ContractFiltrarHotel.Model {
    private static final String IP_BASE = "192.168.0.18:8080";
    private FiltrarHotelPresenter presenter;
    private Context context;

    public FiltrarHotelModel(FiltrarHotelPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void filtrarAPI(String nombre, int orden, int puntuacion, OnFiltrarHotelListener onFiltrarHotelListener) {
        Log.e("FiltrarHotelModel", "filtrar");
        Log.e("FiltrarHotelModel", "nombre: " + nombre);
        Log.e("FiltrarHotelModel", "orden: " + orden);
        //Log.e("FiltrarHotelModel", "puntuacion: " + puntuacion); CUANDO LA TENGAS MAQUINA
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/BookingBack/").create(ApiService.class);
        Call<MyDataFH> call = apiService.filtrarHotel("HOTELES.FILTRAR", nombre, orden, puntuacion);
        call.enqueue(new Callback<MyDataFH>() {
            @Override
            public void onResponse(Call<MyDataFH> call, Response<MyDataFH> response) {
                Log.d("FiltrarHotelModel", "Respuesta: " + response.body());
                if (response.isSuccessful()) {
                    MyDataFH myData = response.body();
                    Log.d("FiltrarHotelModel", "Respuesta1: " + new Gson().toJson(myData));
                    Log.d("FiltrarHotelModel", "Respuesta2: " + myData.getJsonResponse());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (context != null) {
                                Log.d("FiltrarHotelModel", "Respuesta3: " + myData.getListHotel());
                                ArrayList<Hotel> hotelList = response.body().getListHotel();
                                presenter.onFinished(hotelList);
                            } else {
                                Log.e("FiltrarHotelModel", "Contexto nulo. No se puede realizar la acción.");
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MyDataFH> call, Throwable t) {
                onFiltrarHotelListener.onFailure(t.getMessage());
            }
        });
    }
}
