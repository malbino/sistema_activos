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
import org.malbino.models.Activo;
import org.malbino.models.Categoria;
import org.malbino.models.Custodio;
import org.malbino.models.Ubicacion;
import org.malbino.util.ConectorBD;

/**
 *
 * @author Tincho
 */
public class ActivoDao {

    private Connection connection;

    CategoriaDao categoriaDao;
    UbicacionDao ubicacionDao;
    CustodioDao custodioDao;

    public ActivoDao() {
        connection = ConectorBD.getConnection();

        categoriaDao = new CategoriaDao();
        ubicacionDao = new UbicacionDao();
        custodioDao = new CustodioDao();
    }

    public int nuevaActivo(Activo activo) {
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO activos (codigo, codigo_antiguo, descripcion, estado, observaciones, id_categoria, id_ubicacion, id_custodio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, activo.getCodigo());
            preparedStatement.setString(2, activo.getCodigoAntiguo());
            preparedStatement.setString(3, activo.getDescripcion());
            preparedStatement.setString(4, activo.getEstado());
            preparedStatement.setString(5, activo.getObservaciones());

            preparedStatement.setInt(6, 1);
            preparedStatement.setInt(7, 1);
            preparedStatement.setInt(8, 1);

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int editarActivo(Activo activo) {
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE activos SET codigo=?, codigo_antiguo=?, descripcion=?, estado=?, observaciones=?, foto=?, id_categoria=?, id_ubicacion=?, id_custodio=? WHERE id_activo=?");

            preparedStatement.setString(1, activo.getCodigo());
            preparedStatement.setString(2, activo.getCodigoAntiguo());
            preparedStatement.setString(3, activo.getDescripcion());
            preparedStatement.setString(4, activo.getEstado());
            preparedStatement.setString(5, activo.getObservaciones());
            preparedStatement.setString(6, activo.getFoto());

            preparedStatement.setInt(7, activo.getCategoria().getIdCategoria());
            preparedStatement.setInt(8, activo.getUbicacion().getIdUbicacion());
            preparedStatement.setInt(9, activo.getCustodio().getIdCustodio());

            preparedStatement.setInt(10, activo.getIdActivo());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Activo> listaActivos() {
        List<Activo> activos = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM activos ORDER BY id_activo DESC");
            while (rs.next()) {
                Activo activo = new Activo();
                activo.setIdActivo(rs.getInt("id_activo"));
                activo.setCodigo(rs.getString("codigo"));
                activo.setCodigoAntiguo(rs.getString("codigo_antiguo"));
                activo.setDescripcion(rs.getString("descripcion"));
                activo.setEstado(rs.getString("estado"));
                activo.setObservaciones(rs.getString("observaciones"));
                activo.setFoto(rs.getString("foto"));

                Categoria categoria = categoriaDao.buscarCategoria(rs.getInt("id_categoria"));
                if (categoria != null) {
                    activo.setCategoria(categoria);
                }

                Ubicacion ubicacion = ubicacionDao.buscarUbicacion(rs.getInt("id_ubicacion"));
                if (ubicacion != null) {
                    activo.setUbicacion(ubicacion);
                }

                Custodio custodio = custodioDao.buscarCustodio(rs.getInt("id_custodio"));
                if (custodio != null) {
                    activo.setCustodio(custodio);
                }

                activos.add(activo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activos;
    }

    public List<Activo> buscar(String keyword) {
        List<Activo> activos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM activos JOIN categorias ON activos.id_categoria = categorias.id_categoria "
                            + "JOIN ubicaciones ON activos.id_ubicacion = ubicaciones.id_ubicacion "
                            + "JOIN custodios ON activos.id_custodio = custodios.id_custodio "
                            + "WHERE activos.codigo LIKE ? OR "
                            + "activos.codigo_antiguo LIKE ? OR "
                            + "activos.descripcion LIKE ? OR "
                            + "activos.estado LIKE ? OR "
                            + "activos.observaciones LIKE ? OR "
                            + "categorias.nombre LIKE ? OR "
                            + "ubicaciones.nombre LIKE ? OR "
                            + "custodios.nombre LIKE ? "
                            + "ORDER BY id_activo");

            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            preparedStatement.setString(4, "%" + keyword + "%");
            preparedStatement.setString(5, "%" + keyword + "%");
            preparedStatement.setString(6, "%" + keyword + "%");
            preparedStatement.setString(7, "%" + keyword + "%");
            preparedStatement.setString(8, "%" + keyword + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Activo activo = new Activo();
                activo.setIdActivo(rs.getInt("id_activo"));
                activo.setCodigo(rs.getString("codigo"));
                activo.setCodigoAntiguo(rs.getString("codigo_antiguo"));
                activo.setDescripcion(rs.getString("descripcion"));
                activo.setEstado(rs.getString("estado"));
                activo.setObservaciones(rs.getString("observaciones"));
                activo.setFoto(rs.getString("foto"));

                Categoria categoria = categoriaDao.buscarCategoria(rs.getInt("id_categoria"));
                if (categoria != null) {
                    activo.setCategoria(categoria);
                }

                Ubicacion ubicacion = ubicacionDao.buscarUbicacion(rs.getInt("id_ubicacion"));
                if (ubicacion != null) {
                    activo.setUbicacion(ubicacion);
                }

                Custodio custodio = custodioDao.buscarCustodio(rs.getInt("id_custodio"));
                if (custodio != null) {
                    activo.setCustodio(custodio);
                }

                activos.add(activo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activos;
    }

    public Activo buscarActivo(int id_activo) {
        Activo activo = null;

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM activos WHERE id_activo=?");
            preparedStatement.setInt(1, id_activo);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                activo = new Activo();
                activo.setIdActivo(rs.getInt("id_activo"));
                activo.setCodigo(rs.getString("codigo"));
                activo.setCodigoAntiguo(rs.getString("codigo_antiguo"));
                activo.setDescripcion(rs.getString("descripcion"));
                activo.setEstado(rs.getString("estado"));
                activo.setObservaciones(rs.getString("observaciones"));
                activo.setFoto(rs.getString("foto"));

                Categoria categoria = categoriaDao.buscarCategoria(rs.getInt("id_categoria"));
                if (categoria != null) {
                    activo.setCategoria(categoria);
                }

                Ubicacion ubicacion = ubicacionDao.buscarUbicacion(rs.getInt("id_ubicacion"));
                if (ubicacion != null) {
                    activo.setUbicacion(ubicacion);
                }

                Custodio custodio = custodioDao.buscarCustodio(rs.getInt("id_custodio"));
                if (custodio != null) {
                    activo.setCustodio(custodio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activo;
    }
}
