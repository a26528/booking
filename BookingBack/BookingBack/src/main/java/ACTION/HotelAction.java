package ACTION;

import BEAN.Hotel;
import DAO.HotelDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class HotelAction {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "INSERT":
                cadDestino = String.valueOf(insert(request, response, request.getParameter("NOMBRE"), Integer.parseInt(request.getParameter("RESERVAS"))));
                break;
            case "VER10":
                cadDestino = findwithmorereservations(request, response);
                break;
            case "LOGIN":
                cadDestino = login(request, response);
                break;
        }
        return cadDestino;
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        HotelDAO hotelDAO = new HotelDAO();
        String hotel = hotelDAO.login(Integer.parseInt(request.getParameter("ID")), request.getParameter("NOMBRE"));
        return hotel;
    }

    //HABITACION.FIND_ALL
    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        HotelDAO hotelDAO = new HotelDAO();
        ArrayList<Hotel> listHoteles = hotelDAO.findAll(null);
        return Hotel.toJson(listHoteles);
    }
    //HABITACION.INSERT
    private int insert(HttpServletRequest request, HttpServletResponse response, String nombre, int reservas) {
        HotelDAO hotelDAO = new HotelDAO();
        int i = hotelDAO.insert(nombre, reservas);
        return i;
    }
    private String findwithmorereservations(HttpServletRequest request, HttpServletResponse response) {
        HotelDAO hotelDAO = new HotelDAO();
        String resultado = hotelDAO.findwithmorereservations();
        return resultado;
    }
}
