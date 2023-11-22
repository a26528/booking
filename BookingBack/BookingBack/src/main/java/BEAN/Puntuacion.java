package BEAN;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Puntuacion {
    private int idPuntuacion, idPuntuacionHotel, puntuacion;

    public Puntuacion(int idPuntuacion, int idPuntuacionHotel, int puntuacion) {
        this.idPuntuacion = idPuntuacion;
        this.idPuntuacionHotel = idPuntuacionHotel;
        this.puntuacion = puntuacion;
    }

    public Puntuacion() {
    }

    public int getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(int idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public int getIdPuntuacionHotel() {
        return idPuntuacionHotel;
    }

    public void setIdPuntuacionHotel(int idPuntuacionHotel) {
        this.idPuntuacionHotel = idPuntuacionHotel;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Puntuacion{" +
                "idPuntuacion=" + idPuntuacion +
                ", idPuntuacionHotel=" + idPuntuacionHotel +
                ", puntuacion=" + puntuacion +
                '}';
    }

    public static String toJson(ArrayList<Puntuacion> puntuaciones) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.toJson(puntuaciones);
    }
}
