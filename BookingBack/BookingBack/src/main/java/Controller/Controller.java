package Controller;


import ACTION.HabitacionAction;
import ACTION.HotelAction;
import ACTION.UsuarioAction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("ACTION");

        if (action != null) {
            String arrayAction[] = action.split("\\.");

            switch (arrayAction[0]) {
                case "HOTELES":
                    out.print(new HotelAction().execute(request, response));
                    break;
                case "HABITACIONES":
                    out.print(new HabitacionAction().execute(request, response));
                    break;
                case "USUARIOS":
                    System.out.println("he entrado ne usuarios");
                    out.print(new UsuarioAction().execute(request, response));
                    break;
                default:
                    break;
            }
        } else {
            out.print("ACTION parameter is missing or null");
        }
    }
}
