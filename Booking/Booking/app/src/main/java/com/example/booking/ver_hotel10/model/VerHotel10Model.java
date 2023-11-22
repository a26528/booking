package com.example.booking.ver_hotel10.model;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.booking.entities.Hotel;
import com.example.booking.utils.ApiService;
import com.example.booking.utils.RetrofitCliente;
import com.example.booking.ver_hotel10.ContractVerHotel10;
import com.example.booking.ver_hotel10.data.MyDataVH10;
import com.example.booking.ver_hotel10.presenter.VerHotel10Presenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VerHotel10Model implements ContractVerHotel10.Model {

    public static final String IP_BASE = " 192.168.0.18";
    private VerHotel10Presenter presenter;
    private Context context;

    public VerHotel10Model(VerHotel10Presenter verHotel10Presenter, Context context) {
        this.presenter = verHotel10Presenter;
        this.context = context;
    }

    @Override
    public void verAPI(OnVerHotel10Listener OnVerHotel10Listener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/BookingBack/").create(ApiService.class);
        Call<MyDataVH10> call = apiService.ver10hoteles("HOTELES.VER10");
        call.enqueue(new Callback<MyDataVH10>() {
            @Override
            public void onResponse(Call<MyDataVH10> call, Response<MyDataVH10> response) {
                if (response.isSuccessful()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (context != null) {
                                Toast.makeText(context, "Viendo hotel", Toast.LENGTH_SHORT).show();
                                // Si la respuesta es exitosa, iniciar la nueva actividad
                                ArrayList<Hotel> hotelList = response.body().getLstHotel();
                                if (hotelList != null && hotelList.size() > 0) {
                                    Toast.makeText(context, "Viendo hotel", Toast.LENGTH_SHORT).show();
                                    presenter.onFinished(hotelList); // Pasar la lista de hoteles al presenter
                                } else {
                                    presenter.onFailure("No se encontraron hoteles.");
                                }
                            } else {
                                Log.e(response.message(), response.errorBody().toString());
                                Log.e("AAAAAAAAAAAAAAAAAAAA", "Error en la respuesta. Código de estado HTTP: " + response.code());
                                try {
                                    String errorBody = response.errorBody().string();
                                    Log.e("AAAAAAAAAAAAAA", "Cuerpo de error: " + errorBody);

                                    // Mostrar el cuerpo del error en la consola
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    } else {
                    Log.e(response.message(), response.errorBody().toString());
                    Log.e("AAAAAAAAAAAAAAAAAAAA", "Error en la respuesta. Código de estado HTTP: " + response.code());
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("AAAAAAAAAAAAAA", "Cuerpo de error: " + errorBody);

                        // Mostrar el cuerpo del error en la consola
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyDataVH10> call, Throwable t) {
                Log.e("AAAAAAAAAAAAAAAAAAAA", "Error en la respuesta. Código de estado HTTP: " + t.getMessage());

            }
        });

    }
}

