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
import org.malbino.models.Categoria;
import org.malbino.util.ConectorBD;

/**
 *
 * @author Tincho
 */
public class CategoriaDao {

    private Connection connection;

    public CategoriaDao() {
        connection = ConectorBD.getConnection();
    }

    public int nuevaCategoria(Categoria categoria) {
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO categorias (nombre, observaciones) VALUES (?, ?)");

            preparedStatement.setString(1, categoria.getNombre());
            preparedStatement.setString(2, categoria.getObservaciones());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int editarCategoria(Categoria categoria) {
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE categorias SET nombre=?, observaciones=? WHERE id_categoria=?");

            preparedStatement.setString(1, categoria.getNombre());
            preparedStatement.setString(2, categoria.getObservaciones());

            preparedStatement.setInt(3, categoria.getIdCategoria());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Categoria> listaCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM categorias ORDER BY id_categoria DESC");
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setObservaciones(rs.getString("observaciones"));

                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }
    
    public Categoria buscarCategoria(int id_categoria) {
        Categoria categoria = null;

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM categorias WHERE id_categoria=?");
            preparedStatement.setInt(1, id_categoria);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setObservaciones(rs.getString("observaciones"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }
}
