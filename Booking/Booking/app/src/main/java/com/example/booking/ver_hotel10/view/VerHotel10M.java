package com.example.booking.ver_hotel10.view;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking.R;
import com.example.booking.entities.Hotel;
import com.example.booking.ver_hotel10.ContractVerHotel10;
import com.example.booking.ver_hotel10.presenter.VerHotel10Presenter;

import java.util.ArrayList;

public class VerHotel10M extends AppCompatActivity implements ContractVerHotel10.View {

    private ContractVerHotel10.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtemplatehotel10);
        presenter = new VerHotel10Presenter(this, this);
        presenter.ver();
        initComponents();
    }

    private void initComponents() {

    }


    @Override
    public void successVer(ArrayList<Hotel> hotelList) {
        Toast.makeText(this, "¡Ver exitoso! Hotel: " + hotelList.get(0).getNombre_Hotel(), Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        HotelAdapter adapter = new HotelAdapter(hotelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void failureVer(String err) {
        Toast.makeText(this, "Error al ver: " + err, Toast.LENGTH_SHORT).show();
        Log.d("VerHotel10M", "Error al ver: " + err);
    }
}
