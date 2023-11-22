package com.example.booking.create_habitacion;

import com.example.booking.entities.Habitacion;
import com.example.booking.entities.Usuario;


public interface ContractCreateHabitacion {
    public interface View{
        public void successCreate(Habitacion habitacion);
        void failureCreate(String err);
    }
    public interface Presenter{
        void create(Habitacion habitacion);
    }
    public interface Model{
        interface OnCreateHabitacionListener{
            void onFinished(Habitacion habitacion);
            void onFailure(String err);
        }
        void createAPI(Habitacion habitacion,
                      ContractCreateHabitacion.Model.OnCreateHabitacionListener onCreateHabitacionListener);
    }
}
