package com.example.booking.login_user;

import com.example.booking.entities.Usuario;

public interface ContractLoginUser {
    public interface View{
        public void successLogin(Usuario user);
        void failureLogin(String err);
    }
    public interface Presenter{
        void login(Usuario user);

    }
    public interface Model{
        interface OnLoginUserListener{
            void onFinished(Usuario user);
            void onFailure(String err);
        }
        void loginAPI(Usuario user,
                      OnLoginUserListener onLoginUserListener);
    }
}

