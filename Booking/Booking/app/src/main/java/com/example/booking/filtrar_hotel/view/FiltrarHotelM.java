package com.example.booking.filtrar_hotel.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking.R;
import com.example.booking.entities.Hotel;
import com.example.booking.filtrar_hotel.ContractFiltrarHotel;
import com.example.booking.filtrar_hotel.presenter.FiltrarHotelPresenter;
import com.example.booking.ver_hotel10.view.HotelAdapter;


import java.util.ArrayList;

public class FiltrarHotelM extends AppCompatActivity implements ContractFiltrarHotel.View {

    private FiltrarHotelPresenter presenter;
    private RecyclerView recyclerView;
    private HotelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtemplatehotel10);
        initComponents();

        presenter = new FiltrarHotelPresenter(this, this);

        EditText editTextSearch = findViewById(R.id.editTextSearch);
        Spinner spinnerOrder = findViewById(R.id.spinnerOrder);
        SeekBar seekBarRating = findViewById(R.id.seekBarRating);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                filtrarHoteles();
            }
        });

        spinnerOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                filtrarHoteles();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        seekBarRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                filtrarHoteles();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        filtrarHoteles();
    }

    private void initComponents() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void filtrarHoteles() {
        String nombre = obtenerNombreFiltrado();
        int orden = obtenerOrdenFiltrado();
        int puntuacion = obtenerPuntuacionFiltrada();

        presenter.filtrarHotel(nombre, orden, puntuacion);
    }

    private String obtenerNombreFiltrado() {
        EditText editTextSearch = findViewById(R.id.editTextSearch);
        return editTextSearch.getText().toString();
    }

    private int obtenerOrdenFiltrado() {
        Spinner spinnerOrder = findViewById(R.id.spinnerOrder);
        return spinnerOrder.getSelectedItemPosition();
    }

    private int obtenerPuntuacionFiltrada() {
        SeekBar seekBarRating = findViewById(R.id.seekBarRating);
        return seekBarRating.getProgress() + 1;
    }

    @Override
    public void successFiltrar(ArrayList<Hotel> hotelList) {
        Toast.makeText(this, "¡Ver exitoso! Hotel: " + hotelList.get(0).getNombre_Hotel(), Toast.LENGTH_SHORT).show();
        adapter = new HotelAdapter(hotelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void failureFiltrar(String err) {
        // Manejar el error al filtrar los hoteles
    }
}