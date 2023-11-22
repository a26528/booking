package com.example.booking.create_habitacion.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.booking.create_habitacion.ContractCreateHabitacion;
import com.example.booking.create_habitacion.presenter.CreateHabitacionPresenter;
import com.example.booking.entities.Habitacion;
import com.example.booking.create_habitacion.data.MyDataHC;
import com.example.booking.utils.ApiService;
import com.example.booking.utils.RetrofitCliente;

public class CreateHabitacionModel implements ContractCreateHabitacion.Model {

    private static final String IP_BASE = "10.0.2.2:8080";
    private CreateHabitacionPresenter presenter;
    private Context context;
    public CreateHabitacionModel(CreateHabitacionPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }
    @Override
    public void createAPI(Habitacion habitacion, OnCreateHabitacionListener onCreateHabitacionListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/BookingBack/").create(ApiService.class);

        Log.e("CreateHabitacionModel", "Creando habitación Model: " + habitacion.getId_Habitacion_Hotel());
        Log.e("CreateHabitacionModel", "Creando habitación Des: " + habitacion.getDescripcion_Habitacion());
        Log.e("CreateHabitacionModel", "Creando habitación Pre: " + habitacion.getPrecio_Habitacion());
        Log.e("CreateHabitacionModel", "Creando habitación Img: " + habitacion.getImagen_Habitacion());
        Log.e("CreateHabitacionModel",  "Creando habitación Est: " + habitacion.getEstado_Habitacion());

        Call<MyDataHC> call = apiService.createHabitacion("HABITACIONES.CREATE_HABITACION", habitacion.getId_Habitacion_Hotel(), habitacion.getDescripcion_Habitacion(), habitacion.getPrecio_Habitacion(), habitacion.getImagen_Habitacion(), habitacion.getEstado_Habitacion());;
        if (habitacion.getId_Habitacion_Hotel() != 0) {
            call.enqueue(new Callback<MyDataHC>() {
                @Override
                public void onResponse(Call<MyDataHC> call, Response<MyDataHC> response) {
                    Toast.makeText(context, "Registro de habitación exitoso", Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (context != null) {
                                    Toast.makeText(context, "Registro de habitación exitoso", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("CreateHabitacionModel", "Contexto nulo. No se puede mostrar el Toast.");
                                }
                            }
                        });
                    } else {
                        Log.e("CreateHabitacionModel", "Error en la respuesta. Código de estado HTTP: " + response.code());

                        try {
                            String errorBody = response.errorBody().string();
                            Log.e("CreateHabitacionModel", "Cuerpo de error: " + errorBody);

                            // Mejora: Mostrar el mensaje de error al usuario
                            if (context != null) {
                                showToast("Error en la respuesta del servidor: " + errorBody);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MyDataHC> call, Throwable t) {
                    Log.e("CreateHabitacionModel", "Error en la petición: " + t.getMessage());
                    Log.d("CreateHabitacionModel", "Error en la petición: " + t.getMessage());
                    // Mejora: Mostrar el mensaje de error al usuario
                    if (context != null) {
                        showToast("Error en la petición: " + t.getMessage());
                    }

                    onCreateHabitacionListener.onFailure(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, "Error en la petición", Toast.LENGTH_SHORT).show();
            Log.e("CreateHabitacionModel", "Error en la petición id nulo, o algo malo pasó");
        }

    }

    // Método para mostrar un Toast en el hilo principal
    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}