package com.example.booking.login_user.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.R;
import com.example.booking.beans.User;
import com.example.booking.login_user.ContractLoginUser;
import com.example.booking.login_user.presenter.LoginUserPresenter;

public class LoginUserM extends AppCompatActivity implements ContractLoginUser.View{

    private EditText edtUser;
    private EditText  edtPassword;
    private Button btnLogin;

    private LoginUserPresenter presenter =
            new LoginUserPresenter(this);

    /* PATRÓN SINGLETON*/
    private static LoginUserM mainActivity = null;
    public static LoginUserM getInstance(){
        return mainActivity; //0x457845AF
    }
    /* FIN PATRÓN SINGLETON*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        mainActivity = this;
        initComponents();

    }
    private void initComponents(){
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show();
                //sPeliculas.getDatosPeliculas();
                User user = new User();
                user.setUsername("akkarihdez@gmail.com");
                user.setToken("1234");
                presenter.login(user);
            }
        });
    }


    @Override
    public void successLogin(User user) {
        Toast.makeText(mainActivity, user.getUsername(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureLogin(String err) {

    }
}