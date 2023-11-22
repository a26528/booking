package com.example.booking.login_user.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.R;
import com.example.booking.entities.Usuario;
import com.example.booking.login_user.ContractLoginUser;
import com.example.booking.login_user.presenter.LoginUserPresenter;

public class LoginUserM extends AppCompatActivity implements ContractLoginUser.View {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private LoginUserPresenter presenter;  // No lo inicialices aquí

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        presenter = new LoginUserPresenter(this, this);  // Inicializa el presentador aquí y pasa el contexto
        initComponents();
    }

    private void initComponents() {
        emailEditText = findViewById(R.id.editUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("LoginUserM", "Botón de inicio de sesión clicado");

                Usuario user = new Usuario();
                user.setUsername_Usuario(emailEditText.getText().toString());
                user.setPass_Usuario(passwordEditText.getText().toString());

                // Llamar al método de inicio de sesión del presentador
                presenter.login(user);
            }
        });
    }

    @Override
    public void successLogin(Usuario user) {
        Toast.makeText(this, "Inicio de sesión exitoso Usuario: " + user.getNombre_Usuario(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void failureLogin(String err) {
        Toast.makeText(this, "Error de inicio de sesión: " + err, Toast.LENGTH_SHORT).show();

    }
}
