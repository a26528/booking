package ACTION;

import BEAN.Habitacion;
import DAO.HabitacionDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class HabitacionAction {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FIND_HabitacionesLibres":
                cadDestino = findHabitacionesLibres(request, response, Integer.parseInt(request.getParameter("ID_HOTEL")));
                break;
            case "CREATE_HABITACION":
                cadDestino = createHabitacion(request, response, Integer.parseInt(request.getParameter("ID_HABITACION_HOTEL")), request.getParameter("DESCRIPCIONHOTEL"), Double.parseDouble(request.getParameter("PRECIO_HABITACION")), request.getParameter("IMAGEN_HABITACION"), request.getParameter("ESTADO_HABITACION"));
                break;
        }
        return cadDestino;
    }
    //HABITACION.FIND_ALL
    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        ArrayList<Habitacion> lstHabitaciones = habitacionDAO.findAll(null);
        return Habitacion.toJson(lstHabitaciones);
    }
    //HABITACION.findHabitacionesLibres
    private String findHabitacionesLibres(HttpServletRequest request, HttpServletResponse response, int id_hotel) {
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        ArrayList<Habitacion> lstHabitaciones = habitacionDAO.findHabitacionesLibres(id_hotel);
        return Habitacion.toJson(lstHabitaciones);
    }
    //HABITACION.createHabitacion
    private String createHabitacion(HttpServletRequest request, HttpServletResponse response, int id_hotel, String descripcion, double precio, String imagen, String estado) {
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        String i = habitacionDAO.insert(id_hotel, descripcion, precio, imagen, estado);
        return i;
    }
}
