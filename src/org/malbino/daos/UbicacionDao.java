/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.malbino.models.Ubicacion;
import org.malbino.util.ConectorBD;

/**
 *
 * @author Tincho
 */
public class UbicacionDao {

    private Connection connection;

    public UbicacionDao() {
        connection = ConectorBD.getConnection();
    }

    public int nuevaUbicacion(Ubicacion ubicacion) {
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO ubicaciones (nombre, observaciones) VALUES (?, ?)");

            preparedStatement.setString(1, ubicacion.getNombre());
            preparedStatement.setString(2, ubicacion.getObservaciones());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int editarUbicacion(Ubicacion ubicacion) {
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE ubicaciones SET nombre=?, observaciones=? WHERE id_ubicacion=?");

            preparedStatement.setString(1, ubicacion.getNombre());
            preparedStatement.setString(2, ubicacion.getObservaciones());

            preparedStatement.setInt(3, ubicacion.getIdUbicacion());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Ubicacion> listaUbicaciones() {
        List<Ubicacion> ubicaciones = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ubicaciones ORDER BY id_ubicacion DESC");
            while (rs.next()) {
                Ubicacion ubicacion = new Ubicacion();
                ubicacion.setIdUbicacion(rs.getInt("id_ubicacion"));
                ubicacion.setNombre(rs.getString("nombre"));
                ubicacion.setObservaciones(rs.getString("observaciones"));

                ubicaciones.add(ubicacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ubicaciones;
    }
    
    public Ubicacion buscarUbicacion(int id_ubicacion) {
        Ubicacion ubicacion = null;

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM ubicaciones WHERE id_ubicacion=?");
            preparedStatement.setInt(1, id_ubicacion);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ubicacion = new Ubicacion();
                ubicacion.setIdUbicacion(rs.getInt("id_ubicacion"));
                ubicacion.setNombre(rs.getString("nombre"));
                ubicacion.setObservaciones(rs.getString("observaciones"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ubicacion;
    }
}
