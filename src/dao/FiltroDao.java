/*
 * To change this license header, choose License Headers in Project Properties.
TAREA
 *CHECK BOOK Y SE GUARDEN EN LA BASE DE DATOS, CUANDO SE DE BUSCAR APAREZCAN LOS OPCIONES Y CUANDO SE LE DE BUSCAR
SE GUARDEN LOS EN LA BASE DE DATOS
3 MEJOR CALIDAD, ISO, USA, INGRESE UN FILTRO O SELECCIONA CUALQUIERA DE LOS DOS Y SE GUARDEN EN LA BASE DE DATOS
7:00 DEL MARTES 19
pen the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filtro;

/**
 *
 * @author María Lourdes
 */
public class FiltroDao implements metodos<Filtro> {

    private static final String SQL_INSERT = "INSERT INTO script_personas (id,afp,nombres,apellidos,edad,profesion,estado) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE INTO script_personas SET profesion=?,edad=?, estado=? WHERE afp=?";
    private static final String SQL_DELETE = "DELETE FROM script_personas WHERE afp=?";
    private static final String SQL_READ = "SELECT * FROM script_personas WHERE afp=?";
    private static final String SQL_READALL = "SELECT * FROM script_personas";
    private static final Conexion con = Conexion.conectar();
    public ArrayList<Filtro> readAll;

    @Override
    public boolean create(Filtro g) {
        //nos servira para preparar la consulta de insert
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getAfp());
            ps.setString(2, g.getNombre());
            ps.setString(3, g.getApellido());
            ps.setString(4, g.getProfesion());
            ps.setInt(5, g.getEdad());
            ps.setBoolean(4, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getAfp());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getProfesion());
            ps.setInt(2, c.getEdad());
            ps.setBoolean(3, c.getEstado());
             if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Filtro read(Object key) {
        Filtro f = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getCnx().prepareCall(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                f = new Filtro(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }

        return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while (rs.next()) {
                all.add(new Filtro(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)));

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }
}
