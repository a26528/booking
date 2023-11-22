package DAO;

import BEAN.Habitacion;
import BEAN.Hotel;
import motorSQL.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabitacionDAO {
    private MotorSQL motorSQL;
    private static final String SQL_FINDALL = "SELECT * FROM HABITACION WHERE 1 = 1";

    public HabitacionDAO() {
        this.motorSQL = new MotorSQL();
    }


    //find by id_habitacionhotel, descripcion, precio, imagen, estado
    public String fyindforcreate(int idhabitacionhotel, String descripcion, double precio, String imagen, String estado){
        Habitacion habitacion = new Habitacion();
        String sql = "SELECT * FROM HABITACION WHERE ID_HABITACION_HOTEL = " + idhabitacionhotel + " AND DESCRIPCIONHABITACION = '" + descripcion + "' AND PRECIO_HABITACION = " + precio + " AND IMAGENHABITACION = '" + imagen + "' AND ESTADO_HABITACION = '" + estado + "'";
        try {
            motorSQL.connect();
            System.out.println(sql);
            ResultSet rs = motorSQL.executeQuery(sql);
            if (rs.next()) {

                habitacion.setId_Habitacion(rs.getInt(1));
                habitacion.setId_Habitacion_Hotel(rs.getInt(2));
                habitacion.setDescripcion_Habitacion(rs.getString(3));
                habitacion.setPrecio_Habitacion(rs.getDouble(4));
                habitacion.setImagen_Habitacion(rs.getString(5));
                habitacion.setEstado_Habitacion(rs.getString(6));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        motorSQL.disconnect();
        return Habitacion.objecttoJson(habitacion);
    }
    public ArrayList<Habitacion> findAll(Habitacion bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<Habitacion> lstHabitaciones = null;
        try {
            this.motorSQL.connect();
            if (bean.getId_Habitacion() != 0) {
                sql_filtro += " AND ID_HABITACION = " + bean.getId_Habitacion();
            }
            if (bean.getId_Habitacion_Hotel() != 0) {
                sql_filtro += " AND ID_HABITACION_HOTEL = " + bean.getId_Habitacion_Hotel();
            }
            if (bean.getPrecio_Habitacion() != 0) {
                sql_filtro += " AND PRECIO_HABITACION = " + bean.getPrecio_Habitacion();
            }
            if (bean.getDescripcion_Habitacion() != null) {
                sql_filtro += " AND DESCRIPCION_HABITACION = " + bean.getDescripcion_Habitacion();
            }
            if (bean.getImagen_Habitacion() != null) {
                sql_filtro += " AND IMAGEN_HABITACION = " + bean.getImagen_Habitacion();
            }
            if (bean.getEstado_Habitacion() != null) {
                sql_filtro += " AND ESTADO_HABITACION = " + bean.getEstado_Habitacion();
            }
            sql_final = SQL_FINDALL + sql_filtro;
            ResultSet resultset = this.motorSQL.executeQuery(sql_final);
            if (resultset != null) {
                lstHabitaciones = new ArrayList();
                while (resultset.next()) {
                    Habitacion entidad = new Habitacion();
                    entidad.setId_Habitacion(resultset.getInt(1));
                    entidad.setId_Habitacion_Hotel(resultset.getInt(2));
                    entidad.setPrecio_Habitacion(resultset.getDouble(3));
                    entidad.setDescripcion_Habitacion(resultset.getString(4));
                    entidad.setImagen_Habitacion(resultset.getString(5));
                    entidad.setEstado_Habitacion(resultset.getString(6));
                    lstHabitaciones.add(entidad);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.motorSQL.disconnect();
        }
        return lstHabitaciones;
    }
    // INSERT
    public String insert(int id_hotel, String descripcion, double precio,  String imagen, String estado) {
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        String resultadostring = null;
        int resultado = 0;
        String sql_final = "";
        try {
            this.motorSQL.connect();
            sql_final = "INSERT INTO HABITACION (ID_HABITACION_HOTEL, DESCRIPCIONHABITACION, PRECIO_HABITACION, IMAGENHABITACION, ESTADO_HABITACION) VALUES ("
                    + id_hotel + ", '"
                    + descripcion + "', "
                    + precio + ", '"
                    + imagen + "', '"
                    + estado + "')";
            resultado = this.motorSQL.execute(sql_final);
            if (resultado == 1){
                resultadostring = habitacionDAO.fyindforcreate(id_hotel, descripcion, precio, imagen, estado);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.motorSQL.disconnect();
        }
        return resultadostring;
    }

    // UPDATE
    public int update(Habitacion bean) {
        int resultado = 0;
        String sql_final = "";
        try {
            this.motorSQL.connect();
            sql_final = "UPDATE HABITACION SET ID_HABITACION_HOTEL = " + bean.getId_Habitacion_Hotel()
                    + ", DESCRIPCIONHABITACION = '" + bean.getDescripcion_Habitacion()
                    + "', PRECIO_HABITACION = " + bean.getPrecio_Habitacion()
                    + ", IMAGENHABITACION = '" + bean.getImagen_Habitacion()
                    + "', ESTADO_HABITACION = '" + bean.getEstado_Habitacion()
                    + "' WHERE ID_HABITACION = " + bean.getId_Habitacion();
            resultado = this.motorSQL.execute(sql_final);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.motorSQL.disconnect();
        }
        return resultado;
    }

    public int delete(Habitacion bean) {
        int resultado = 0;
        String sql_final = "";
        try {
            this.motorSQL.connect();
            sql_final = "DELETE FROM HABITACION WHERE ID_HABITACION = " + bean.getId_Habitacion();
            resultado = this.motorSQL.execute(sql_final);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.motorSQL.disconnect();
        }
        return resultado;
    }
    public ArrayList<Habitacion> findHabitacionesLibres(int id_hotel) {
        ArrayList<Habitacion> lstHabitaciones = null;
        String sql = "SELECT * FROM HABITACION WHERE ID_HABITACION_HOTEL = " + id_hotel + " AND ESTADO_HABITACION = 'Libre'";
        try {
            //1º)
            motorSQL.connect();
            ResultSet resultset = motorSQL.
                    executeQuery(sql);
            if (resultset != null) {
                lstHabitaciones = new ArrayList();
                while (resultset.next()) {
                    Habitacion entidad = new Habitacion();
                    entidad.setId_Habitacion(resultset.getInt(1));
                    entidad.setId_Habitacion_Hotel(resultset.getInt(2));
                    entidad.setPrecio_Habitacion(resultset.getDouble(3));
                    entidad.setDescripcion_Habitacion(resultset.getString(4));
                    entidad.setImagen_Habitacion(resultset.getString(5));
                    entidad.setEstado_Habitacion(resultset.getString(6));
                    lstHabitaciones.add(entidad);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            motorSQL.disconnect();
        }
        return lstHabitaciones;
    }


    public static void main(String[] args) {
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        String a = habitacionDAO.fyindforcreate(4, "Habitacion 1", 100, "imagen", "Libre");
        System.out.println(a);
    }
}
