package com.example.booking.create_habitacion.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.R;
import com.example.booking.create_habitacion.ContractCreateHabitacion;
import com.example.booking.create_habitacion.presenter.CreateHabitacionPresenter;
import com.example.booking.entities.Habitacion;

public class CreateHabitacionM extends AppCompatActivity implements ContractCreateHabitacion.View {

    private EditText idhabitacionEditText;
    private EditText descripcionHabitacionEditText;
    private EditText precioHabitacionEditText;
    private EditText rutaimagenHabitacionEditText;
    private EditText estadoHabitacionEditText;
    private Button createHabitacionButton;

    private CreateHabitacionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createhabitacion);
        presenter = new CreateHabitacionPresenter(this, this);
        initComponents();
    }

    public void initComponents() {
        idhabitacionEditText = findViewById(R.id.editTextRoomId);
        descripcionHabitacionEditText = findViewById(R.id.editTextRoomDescription);
        precioHabitacionEditText = findViewById(R.id.editTextRoomPrice);
        rutaimagenHabitacionEditText = findViewById(R.id.editTextImagePath);
        estadoHabitacionEditText = findViewById(R.id.editTextRoomStatus);
        createHabitacionButton = findViewById(R.id.buttonRegisterRoom);

        createHabitacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CreateHabitacionM", "Botón de registro de habitación clicado");
                Habitacion habitacion = new Habitacion();
                habitacion.setId_Habitacion_Hotel(Integer.parseInt(idhabitacionEditText.getText().toString()));
                habitacion.setDescripcion_Habitacion(descripcionHabitacionEditText.getText().toString());
                habitacion.setPrecio_Habitacion(Double.parseDouble(precioHabitacionEditText.getText().toString()));
                habitacion.setImagen_Habitacion(rutaimagenHabitacionEditText.getText().toString());
                habitacion.setEstado_Habitacion(estadoHabitacionEditText.getText().toString());

                presenter.create(habitacion);
            }
        });
    }

    @Override
    public void successCreate(Habitacion habitacion) {
        Toast.makeText(this, "¡Registro de habitación exitoso! Habitación: " + habitacion.getDescripcion_Habitacion(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureCreate(String err) {
        Toast.makeText(this, "Error de registro de habitación: " + err, Toast.LENGTH_SHORT).show();
    }
}
