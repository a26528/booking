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
                cadDestino = login(request, response, request.getParameter("USERNAME_USUARIO"), request.getParameter("PASS_USUARIO"));
            case "REGISTER":
                cadDestino = register(request, response, request.getParameter("NOMBRE_USUARIO"), request.getParameter("USERNAME_USUARIO"), request.getParameter("PASS_USUARIO"));
                break;
        }
        return cadDestino;
    }

    private String login(HttpServletRequest request, HttpServletResponse response,String username, String pass) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String usuario = usuarioDAO.login(username, pass);
        String res = "{";
        res += "\"message\":";
        res += "\"Todo Bien\",";
        res += "\"lstUsers\":";
        res += "[";
        res += usuario;
        res += "]";
        res += "}";
        return res;
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