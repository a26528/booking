package com.example.booking.login_hotel;

import com.example.booking.entities.Hotel;

public interface ContractLoginHotel {
    public interface View{
        public void successLogin(Hotel hotel);
        void failureLogin(String err);
    }
    public interface Presenter{
        void loginHotel(int id , String nombre);

    }
    public interface Model{
        interface OnLoginHotelListener{
            void onFinished(Hotel hotel);
            void onFailure(String err);
        }
        void loginAPI(int id, String nombre,
                      ContractLoginHotel.Model.OnLoginHotelListener onLoginHotelListener);
    }
}
