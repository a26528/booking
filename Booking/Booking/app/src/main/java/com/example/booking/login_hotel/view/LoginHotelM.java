package com.example.booking.login_hotel.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.R;
import com.example.booking.entities.Hotel;
import com.example.booking.login_hotel.ContractLoginHotel;
import com.example.booking.login_hotel.presenter.LoginHotelPresenter;

public class LoginHotelM extends AppCompatActivity implements ContractLoginHotel.View{
    private EditText idEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private LoginHotelPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginhotel);
        presenter = new LoginHotelPresenter(this, this);
        initComponents();
    }

    private void initComponents() {
        idEditText = findViewById(R.id.edithotelid);
        passwordEditText = findViewById(R.id.edithotelnombre);
        loginButton = findViewById(R.id.buttonLoginHotel);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = idEditText.getText().toString().isEmpty() ? 0 : Integer.parseInt(idEditText.getText().toString());
                String password = passwordEditText.getText().toString();
                presenter.loginHotel(id, password);
            }
        });
    }

    @Override
    public void successLogin(Hotel hotel) {

    }

    @Override
    public void failureLogin(String err) {

    }
}
