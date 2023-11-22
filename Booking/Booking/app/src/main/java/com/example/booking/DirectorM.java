package com.example.booking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.create_habitacion.view.CreateHabitacionM;
import com.example.booking.entities.Habitacion;
import com.example.booking.entities.Usuario;
import com.example.booking.login_hotel.view.LoginHotelM;
import com.example.booking.ver_hotel10.view.VerHotel10M;

public class DirectorM extends AppCompatActivity {
    private Button btnCrearHabitacion;
    private Button btnVerHabitaciones;
    private Button btnVerHoteles;
    Context context;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.director);
        initComponents();
    }

    private void initComponents() {
        btnCrearHabitacion = findViewById(R.id.btnDarAltaHabitacion);
        btnVerHabitaciones = findViewById(R.id.btnVerHabitaciones);
        btnVerHoteles = findViewById(R.id.btnVerHoteles);


        btnCrearHabitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HabitacionCrearIntent = new Intent(DirectorM.this, CreateHabitacionM.class);
                startActivity(HabitacionCrearIntent);
            }
        });
        btnVerHoteles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VerHotelesIntent = new Intent(DirectorM.this, VerHotel10M.class);
                startActivity(VerHotelesIntent);
            }
        });
        btnVerHabitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VerHabitacionesIntent = new Intent(DirectorM.this, LoginHotelM.class);
                startActivity(VerHabitacionesIntent);
            }
        });
    }
}

