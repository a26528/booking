package com.example.booking.login_user.model;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.booking.DirectorM;
import com.example.booking.entities.Usuario;
import com.example.booking.login_user.ContractLoginUser;
import com.example.booking.login_user.data.MyDataLU;
import com.example.booking.login_user.presenter.LoginUserPresenter;
import com.example.booking.utils.ApiService;
import com.example.booking.utils.RetrofitCliente;
import com.google.gson.Gson;

public class LoginUserModel implements ContractLoginUser.Model {
    private static final String IP_BASE = "192.168.104.56:8080";
    private LoginUserPresenter presenter;
    private Context context;

    public LoginUserModel(LoginUserPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void loginAPI(Usuario user, OnLoginUserListener onLoginUserListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/BookingBack/").create(ApiService.class);

        Call<MyDataLU> call = apiService.loginUser("USUARIOS.LOGIN", user.getUsername_Usuario(), user.getPass_Usuario());
        call.enqueue(new Callback<MyDataLU>() {
            @Override
            public void onResponse(Call<MyDataLU> call, Response<MyDataLU> response) {
                if (response.isSuccessful()) {
                    MyDataLU myData = response.body();
                    Log.d("LoginUserModel", "Respuesta1: " + new Gson().toJson(myData));
                    Log.d("LoginUserModel", "Respuesta2: " + myData.getJsonResponse());
                    Log.d("LoginUserModel", "Respuesta3: " + myData.getMessage());
                    Log.d("LoginUserModel", "Respuesta4: " + myData.getLstUsers());
                    Log.d("LoginUserModel", "Respuesta5: " + myData.getLstUsers().get(0).getId_Usuario());
                    Log.d("LoginUserModel", "Respuesta6: " + myData.getLstUsers().get(0).getNombre_Usuario());
                    Log.d("LoginUserModel", "Respuesta7: " + myData.getLstUsers().get(0).getUsername_Usuario());
                    Log.d("LoginUserModel", "Respuesta8: " + myData.getLstUsers().get(0).getPass_Usuario());

                    if (myData.getLstUsers().get(0).getId_Usuario() != 0) {
                        myData.setMessage("Usuario encontrado");

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if ("Usuario encontrado".equals(myData.getMessage())) {
                                    // Inicio de sesión exitoso si la respuesta es "Usuario encontrado"
                                    Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                    Intent DirectorIntent = new Intent(context, DirectorM.class);
                                    context.startActivity(DirectorIntent);
                                } else {
                                    // Manejar si la respuesta no es "Usuario encontrado"
                                    Toast.makeText(context, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                                    onLoginUserListener.onFailure("Inicio de sesión fallido");
                                }
                            }
                        });
                    } else {
                        // No se ha encontrado ningún usuario
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                                onLoginUserListener.onFailure("Usuario no encontrado");
                            }
                        });
                    }
                } else {
                    // Manejo de errores si la respuesta no es exitosa
                    Log.e(response.message(), response.errorBody().toString());
                    Log.e("LoginUserModel", "Error en la respuesta. Código de estado HTTP: " + response.code());
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("LoginUserModel", "Cuerpo de error: " + errorBody);

                        // Mostrar el cuerpo del error en la consola
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyDataLU> call, Throwable t) {
                Log.e("LoginUserModel", "Error en la respuesta. Código de estado HTTP: " + t.getMessage());
                Toast.makeText(context, "Error en la respuesta. Código de estado HTTP: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                onLoginUserListener.onFailure(t.getMessage());
            }
        });
    }
}


