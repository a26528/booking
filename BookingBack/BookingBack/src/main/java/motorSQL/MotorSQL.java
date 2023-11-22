package motorSQL;

import BEAN.Usuario;
import DAO.UsuarioDAO;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;

public class MotorSQL {
    private static final String url = "jdbc:postgresql://booking.cakx2yrcohzg.us-east-1.rds.amazonaws.com/Booking";
    private static final String name = "Leyre";
    private static final String pass = "12345678";
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    public static void main(String[] args) {
        try {
            MotorSQL m = new MotorSQL();
            m.connect();
            m.disconnect();
            Gson gson = new Gson();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ArrayList<Usuario> Usuarios = usuarioDAO.findAll();
            String listaUsuarios = gson.toJson(Usuarios);
            System.out.println(listaUsuarios);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public Connection connect() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, name, pass);
                st = conn.createStatement();
                System.out.println("Conexión a la base de datos establecida.");
            }
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public int execute(String sql) {
        int resp = 0;
        try {
            resp = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resp;
    }

    public ResultSet executeQuery(String sql) {
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }

    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();
                System.out.println("rs closed");
            }
            if (st != null) {
                st.close();
                System.out.println("st closed");
            }
            if (conn != null) {
                conn.close();
                System.out.println("conn closed");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}