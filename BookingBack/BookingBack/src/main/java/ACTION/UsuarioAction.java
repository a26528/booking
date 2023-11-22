package ACTION;

import BEAN.Usuario;
import DAO.UsuarioDAO;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsuarioAction {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "LOGIN":

                System.out.println("He entrado en login");
                cadDestino = login(request, response);
                break;
            case "REGISTER":
                cadDestino = register(request, response, request.getParameter("NOMBRE_USUARIO"), request.getParameter("USERNAME_USUARIO"), request.getParameter("PASS_USUARIO"));
                break;
        }
        return cadDestino;
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String usuario = usuarioDAO.login(request.getParameter("USERNAME_USUARIO"), request.getParameter("PASS_USUARIO"));
        return usuario;
    }

    private String register(HttpServletRequest request, HttpServletResponse response, String nombre, String username, String pass) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int i = usuarioDAO.register(nombre, username, pass);
        return String.valueOf(i);
    }

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String usuario = usuarioDAO.login("Pablo", "Pablo1234");
        System.out.println(usuario);
    }
}