package com.example.booking.filtrar_hotel;

import com.example.booking.entities.Hotel;

import java.util.ArrayList;

public interface ContractFiltrarHotel {
    public interface View {
        public void successFiltrar(ArrayList<Hotel> hotelList);

        void failureFiltrar(String err);
    }

    public interface Presenter {
        void filtrarHotel(String nombre, int orden, int puntuacion);

    }

    public interface Model {
        interface OnFiltrarHotelListener {
            void onFinished(ArrayList<Hotel> hotelList);

            void onFailure(String err);
        }

        void filtrarAPI(String nombre, int orden, int puntuacion,
                        OnFiltrarHotelListener onFiltrarHotelListener);
    }
}
