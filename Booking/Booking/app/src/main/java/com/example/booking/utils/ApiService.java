package com.example.booking.utils;

import com.example.booking.create_habitacion.data.MyDataHC;
import com.example.booking.login_user.data.MyDataLU;
import com.example.booking.ver_hotel10.data.MyDataVH10;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @GET("Controller")
    Call<MyDataLU> loginUser(
            @Query("ACTION") String action,
            @Query("USERNAME_USUARIO") String username,
            @Query("PASS_USUARIO") String password
    );

    @GET("Controller")
    Call loginHotel(
            @Query("ACTION") String action,
            @Query("ID_HOTEL") String idhotel,
            @Query("NOMBRE_HOTEL") String nombrehotel
    );
    @GET("Controller")
    Call<MyDataHC> createHabitacion(
            @Query("ACTION") String action,
            @Query("ID_HABITACION_HOTEL") int idhabitacionhotel,
            @Query("DESCRIPCIONHOTEL") String descripcionhotel,
            @Query("PRECIO_HABITACION") double preciohabitacion,
            @Query("IMAGEN_HABITACION") String imagenhabitacion,
            @Query("ESTADO_HABITACION") String estadohabitacion
    );
    @GET("Controller")
    Call<MyDataVH10> ver10hoteles(
            @Query("ACTION") String action
    );

}
