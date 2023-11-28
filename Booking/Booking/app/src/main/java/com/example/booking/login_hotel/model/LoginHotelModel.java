package com.example.booking.login_hotel.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.booking.DirectorM;
import com.example.booking.entities.Hotel;
import com.example.booking.login_hotel.ContractLoginHotel;
import com.example.booking.login_hotel.data.MyDataLH;
import com.example.booking.login_hotel.presenter.LoginHotelPresenter;
import com.example.booking.utils.ApiService;
import com.example.booking.utils.RetrofitCliente;


public class LoginHotelModel implements ContractLoginHotel.Model{

    private static final String IP_BASE = "192.168.0.18:8080";
    private LoginHotelPresenter presenter;
    private Context context;
    public LoginHotelModel(LoginHotelPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void loginAPI(int id, String nombre, OnLoginHotelListener onLoginHotelListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/BookingBack/").create(ApiService.class);
        Call<MyDataLH> call = apiService.loginHotel("HOTELES.LOGIN", String.valueOf(id), nombre);
        call.enqueue(new Callback<MyDataLH>() {
            @Override
            public void onResponse(Call<MyDataLH> call, Response<MyDataLH> response) {
                if(response.isSuccessful()){
                    if (response.isSuccessful()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (context != null) {
                                    Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                    int userID = response.body().getLstHotel().get(0).getId_Hotel();
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("MiPref", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("HotelID", userID);
                                    editor.apply();
                                    Intent DirectorIntent = new Intent(context, DirectorM.class);
                                    context.startActivity(DirectorIntent);
                                } else {
                                    Log.e("LoginHotelModel", "Contexto nulo. No se puede mostrar el Toast.");
                                }
                            }
                        });
                    } else {
                        Log.e(response.message(), response.errorBody().toString());
                        Log.d("LoginHotelModel", "Error en la respuesta. Código de estado HTTP: " + response.code());

                        try {
                            String errorBody = response.errorBody().string();
                            Log.d("LoginHotelModel", "Error en la respuesta. Código de estado HTTP: " + response.code());

                            // Mostrar el cuerpo del error en la consola
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    onLoginHotelListener.onFailure("Error en la respuesta. Código de estado HTTP: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyDataLH> call, Throwable t) {
                onLoginHotelListener.onFailure(t.getMessage());
            }
        });
    }
}
