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
import org.malbino.models.Custodio;
import org.malbino.util.ConectorBD;

/**
 *
 * @author Tincho
 */
public class CustodioDao {

    private Connection connection;

    public CustodioDao() {
        connection = ConectorBD.getConnection();
    }

    public int nuevoCustodio(Custodio custodio) {
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO custodios (nombre, observaciones) VALUES (?, ?)");

            preparedStatement.setString(1, custodio.getNombre());
            preparedStatement.setString(2, custodio.getObservaciones());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int editarCustodio(Custodio custodio) {
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE custodios SET nombre=?, observaciones=? WHERE id_custodio=?");

            preparedStatement.setString(1, custodio.getNombre());
            preparedStatement.setString(2, custodio.getObservaciones());

            preparedStatement.setInt(3, custodio.getIdCustodio());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Custodio> listaCustodios() {
        List<Custodio> custodioes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM custodios ORDER BY id_custodio DESC");
            while (rs.next()) {
                Custodio custodio = new Custodio();
                custodio.setIdCustodio(rs.getInt("id_custodio"));
                custodio.setNombre(rs.getString("nombre"));
                custodio.setObservaciones(rs.getString("observaciones"));

                custodioes.add(custodio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return custodioes;
    }
    
    public Custodio buscarCustodio(int id_custodio) {
        Custodio custodio = null;

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM custodios WHERE id_custodio=?");
            preparedStatement.setInt(1, id_custodio);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                custodio = new Custodio();
                custodio.setIdCustodio(rs.getInt("id_custodio"));
                custodio.setNombre(rs.getString("nombre"));
                custodio.setObservaciones(rs.getString("observaciones"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return custodio;
    }
}
