package DAO;

import BEAN.Hotel;
import BEAN.MyDataVH10;
import BEAN.Usuario;
import motorSQL.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDAO {
    private MotorSQL motorSQL;
    private static final String SQL_FINDALL = "SELECT * FROM HOTEL WHERE 1 = 1";
    public HotelDAO(){
        this.motorSQL = new MotorSQL();
    }

    public ArrayList<Hotel> findAll(Hotel bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<Hotel> lstHoteles = null;
        try {
            this.motorSQL.connect();
            if (bean.getId_Hotel() != 0) {
                sql_filtro += " AND ID_HOTEL = " + bean.getId_Hotel();
            }
            if (bean.getNombre_Hotel() != null) {
                sql_filtro += " AND NOMBRE_HOTEL = " + bean.getNombre_Hotel();
            }
            if (bean.getReservas_Hotel() != 0) {
                sql_filtro += " AND RESERVAS_HOTEL = " + bean.getReservas_Hotel();
            }if (bean.getImagen_Hotel() != null) {
                sql_filtro += " AND IMAGEN_HOTEL = " + bean.getImagen_Hotel();
            }
            if (bean.getLocalizacion_Hotel() != null) {
                sql_filtro += " AND LOCALIZACION_HOTEL = " + bean.getLocalizacion_Hotel();
            }
            sql_final = SQL_FINDALL + sql_filtro;
            ResultSet resultset = this.motorSQL.executeQuery(sql_final);
            if (resultset != null) {
                lstHoteles = new ArrayList();
                while (resultset.next()) {
                    Hotel entidad = new Hotel();
                    entidad.setId_Hotel(resultset.getInt(1));
                    entidad.setNombre_Hotel(resultset.getString(2));
                    entidad.setReservas_Hotel(resultset.getInt(3));
                    entidad.setImagen_Hotel(resultset.getString(4));
                    entidad.setLocalizacion_Hotel(resultset.getString(5));
                    lstHoteles.add(entidad);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.motorSQL.disconnect();
        }
        return lstHoteles;
    }
    //login.id_hotel, nombre_hotel, reservas_hotel
    public String login(int id, String nombre) {
        String sql_final = "";
        ArrayList<Hotel> lstHoteles = null;
        try {
            this.motorSQL.connect();
            sql_final = "SELECT * FROM public.hotel WHERE id_hotel = " + id + " AND nombre_hotel = '" + nombre + "'";
            System.out.println(sql_final);
            ResultSet resultset = this.motorSQL.executeQuery(sql_final);
            if (resultset != null) {
                lstHoteles = new ArrayList();
                while (resultset.next()) {
                    Hotel entidad = new Hotel();
                    entidad.setId_Hotel(resultset.getInt(1));
                    entidad.setNombre_Hotel(resultset.getString(2));
                    entidad.setReservas_Hotel(resultset.getInt(3));
                    lstHoteles.add(entidad);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.motorSQL.disconnect();
        }
        return Hotel.toJson(lstHoteles);
    }
    public int insert(String nombre, int reservas) {
        String sql_insert = "";
        int i = 0;
        try {
            this.motorSQL.connect();
            sql_insert = "INSERT INTO HOTEL(NOMBRE_HOTEL, RESERVAS_HOTEL) VALUES ('" + nombre + "', " + reservas + ")";
            i = this.motorSQL.execute(sql_insert);
        } finally {
            this.motorSQL.disconnect();
        }
        return i;
    }
    public int update(String nombre, int reservas, int id) {
        String sql_update = "";
        int i = 0;
        try {
            this.motorSQL.connect();
            sql_update = "UPDATE HOTEL SET NOMBRE_HOTEL = '" + nombre + "', RESERVAS_HOTEL = " + reservas + " WHERE ID_HOTEL = " + id;
            i = this.motorSQL.execute(sql_update);
        } finally {
            this.motorSQL.disconnect();
        }
        return i;
    }

    public String findwithmorereservations(){
        String resultado = null;
        ArrayList<Hotel> lstHoteles = new ArrayList<>(); // Inicializa la lista

        try{
            this.motorSQL.connect();
            String sql = "SELECT id_hotel, nombre_hotel, reservas_hotel FROM public.hotel ORDER BY reservas_hotel DESC LIMIT 10";
            ResultSet resultset = this.motorSQL.executeQuery(sql);

            while (resultset.next()) { // Mueve el cursor al primer registro y sigue avanzando
                Hotel hotel = new Hotel();
                hotel.setId_Hotel(resultset.getInt(1));
                hotel.setNombre_Hotel(resultset.getString(2));
                hotel.setReservas_Hotel(resultset.getInt(3));
                lstHoteles.add(hotel); // Agrega cada hotel a la lista
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.motorSQL.disconnect();
        }

        MyDataVH10 data = new MyDataVH10();
        data.setLstHotel(lstHoteles);
        resultado = Hotel.toJsonData(data); // Convierte la lista de hoteles a formato JSON

        return resultado;
    }


    public String findplaya(){
        String resultado = null;
        ArrayList<Hotel> lstHoteles = new ArrayList<>(); // Inicializa la lista

        try{
            this.motorSQL.connect();
            String sql = "SELECT id_hotel, nombre_hotel, reservas_hotel FROM public.hotel WHERE localizacion_hotel= 'playa'";
            ResultSet resultset = this.motorSQL.executeQuery(sql);

            while (resultset.next()) { // Mueve el cursor al primer registro y sigue avanzando
                Hotel hotel = new Hotel();
                hotel.setId_Hotel(resultset.getInt(1));
                hotel.setNombre_Hotel(resultset.getString(2));
                hotel.setReservas_Hotel(resultset.getInt(3));
                lstHoteles.add(hotel); // Agrega cada hotel a la lista
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.motorSQL.disconnect();
        }

        MyDataVH10 data = new MyDataVH10();
        data.setLstHotel(lstHoteles);
        resultado = Hotel.toJsonData(data); // Convierte la lista de hoteles a formato JSON

        return resultado;
    }
    public int delete(int id) {
        String sql_delete = "";
        int i = 0;
        try {
            this.motorSQL.connect();
            sql_delete = "DELETE FROM HOTEL WHERE ID_HOTEL = " + id;
            i = this.motorSQL.execute(sql_delete);
        } finally {
            this.motorSQL.disconnect();
        }
        return i;
    }

    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();
        Hotel hotel = new Hotel();
        System.out.println(hotelDAO.findwithmorereservations());
        System.out.println(hotelDAO.login(8, "Hotel The Peninsula"));

        /*if (hotelDAO.insert("Hotel 1", 10)==1) {
            System.out.println("Insertado correctamente");
        } else {
            System.out.println("No se ha podido insertar");
        }
        if (hotelDAO.insert("Hotel 2", 20)==1) {
            System.out.println("Insertado correctamente");
        } else {
            System.out.println("No se ha podido insertar");
        }*/
        /*hotelDAO.delete(1);
        hotelDAO.delete(2);*/
        /*hotelDAO.update("Hotel 3", 30, 1);*/
    }
}
