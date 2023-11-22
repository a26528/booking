package com.example.booking.login_user.presenter;

import android.content.Context;
import android.util.Log;

import com.example.booking.entities.Usuario;
import com.example.booking.login_user.ContractLoginUser;
import com.example.booking.login_user.model.LoginUserModel;

public class LoginUserPresenter implements ContractLoginUser.Presenter, ContractLoginUser.Model.OnLoginUserListener {

    private ContractLoginUser.View view;
    private ContractLoginUser.Model model;

    public LoginUserPresenter(ContractLoginUser.View view, Context context){  // Agrega el parámetro Context
        this.view = view;
        model = new LoginUserModel(this, context);  // Pasa el contexto al constructor del modelo
    }


    @Override
    public void onFinished(Usuario user) {

    }

    @Override
    public void onFailure(String err) {

    }

    @Override
    public void login(Usuario user) {
        Log.d("LoginUserPresenter", "Iniciando sesión para usuario: " + user.getUsername_Usuario());
        model.loginAPI(user, this);
    }
}
