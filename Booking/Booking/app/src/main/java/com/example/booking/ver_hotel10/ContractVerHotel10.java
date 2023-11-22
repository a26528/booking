package com.example.booking.ver_hotel10;

import com.example.booking.entities.Hotel;

import java.util.ArrayList;

public interface ContractVerHotel10{
    public interface View{
        public void successVer(ArrayList<Hotel> hotelList);
        void failureVer(String err);
    }
    public interface Presenter{
        void ver();

    }
    public interface Model{
        interface OnVerHotel10Listener{
            void onFinished(ArrayList<Hotel> hotelList);
            void onFailure(String err);
        }
        void verAPI(OnVerHotel10Listener OnVerHotel10Listener);
    }
}

