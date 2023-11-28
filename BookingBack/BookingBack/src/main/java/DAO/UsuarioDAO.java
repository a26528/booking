package DAO;

import BEAN.Usuario;
import motorSQL.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    private MotorSQL motorSQL;
    private static final String SQL_FINDALL = "SELECT * FROM USUARIO WHERE 1 = 1";
    public UsuarioDAO(){
        this.motorSQL = new MotorSQL();
    }
    public ArrayList<Usuario> findAll() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = SQL_FINDALL;
        try {

            motorSQL.connect();
            System.out.println(sql);
            ResultSet rs = motorSQL.
                    executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_Usuario(rs.getInt(1));
                usuario.setNombre_Usuario(rs.getString(2));
                usuario.setUsername_Usuario(rs.getString(3));
                usuario.setPass_Usuario(rs.getString(4));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        motorSQL.disconnect();
        return usuarios;
    }
    public String login(String username, String pass) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM USUARIO WHERE USERNAME_USUARIO = '" + username + "' AND PASS_USUARIO = '" + pass + "'";
        try {
            motorSQL.connect();
            System.out.println(sql);
            ResultSet rs = motorSQL.executeQuery(sql);
            while (rs.next()) {
                usuario.setId_Usuario(rs.getInt(1));
                usuario.setNombre_Usuario(rs.getString(2));
                usuario.setUsername_Usuario(rs.getString(3));
                usuario.setPass_Usuario(rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        motorSQL.disconnect();
        return Usuario.objecttoJson(usuario);
    }
/*
    public int register(String nombre ,String username, String pass) {
        int i = 0;
        String sql = "INSERT INTO USUARIO (NOMBRE_USUARIO, USERNAME_USUARIO, PASS_USUARIO) VALUES ('" + nombre + "', '" + username + "', '" + pass + "')";
        motorSQL.connect();
        System.out.println(sql);
        i = motorSQL.
                execute(sql);
        motorSQL.disconnect();
        return i;
    }
/*
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.register("Pablo", "Pablo", "Pablo1234");
        System.out.println(usuarioDAO.login("Pablo", "Pablo1234"));

    }*/
}
