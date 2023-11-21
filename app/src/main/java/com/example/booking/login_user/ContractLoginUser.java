package com.example.booking.login_user;

import com.example.booking.beans.User;

public interface ContractLoginUser {
    public interface View{
        public void successLogin(User user);
        void failureLogin(String err);
        // void failureLogin(MyException err);
    }
    public interface Presenter{
        // void login(String email, String pass);
        void login(User user);

    }
    public interface Model{
        interface OnLoginUserListener{
            void onFinished(User user);
            void onFailure(String err);
        }
        void loginAPI(User user,
                      OnLoginUserListener onLoginUserListener);
    }
}
