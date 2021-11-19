/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.gui.tablemodels;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.malbino.daos.ActivoDao;
import org.malbino.models.Activo;
import org.malbino.models.Categoria;
import org.malbino.models.Custodio;
import org.malbino.models.Ubicacion;

/**
 *
 * @author tincho
 */
public class TableModelActivo implements TableModel {

    private List<Activo> activos;

    ActivoDao activoDao;

    public TableModelActivo(List<Activo> activos) {
        this.activos = activos;

        activoDao = new ActivoDao();
    }

    @Override
    public int getRowCount() {
        return activos.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String columnName = null;

        switch (columnIndex) {
            case 0: {
                columnName = "Nº";
                break;
            }
            case 1: {
                columnName = "Codigo";
                break;
            }
            case 2: {
                columnName = "Codigo Antiguo";
                break;
            }
            case 3: {
                columnName = "Descripción";
                break;
            }
            case 4: {
                columnName = "Estado";
                break;
            }
            case 5: {
                columnName = "Observaciones";
                break;
            }
            case 6: {
                columnName = "Categoria";
                break;
            }
            case 7: {
                columnName = "Ubicacion";
                break;
            }
            case 8: {
                columnName = "Custodio";
                break;
            }
        }

        return columnName;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> columnClass = null;

        switch (columnIndex) {
            case 0: {
                columnClass = Integer.class;
                break;
            }
            case 1: {
                columnClass = String.class;
                break;
            }
            case 2: {
                columnClass = String.class;
                break;
            }
            case 3: {
                columnClass = String.class;
                break;
            }
            case 4: {
                columnClass = String.class;
                break;
            }
            case 5: {
                columnClass = String.class;
                break;
            }
            case 6: {
                columnClass = String.class;
                break;
            }
            case 7: {
                columnClass = Categoria.class;
                break;
            }
            case 8: {
                columnClass = Ubicacion.class;
                break;
            }
            case 9: {
                columnClass = Custodio.class;
                break;
            }
        }

        return columnClass;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        boolean cellEditable = false;
        switch (columnIndex) {
            case 0: {
                cellEditable = false;
                break;
            }
            case 1: {
                cellEditable = true;
                break;
            }
            case 2: {
                cellEditable = true;
                break;
            }
            case 3: {
                cellEditable = true;
                break;
            }
            case 4: {
                cellEditable = true;
                break;
            }
            case 5: {
                cellEditable = true;
                break;
            }
            case 6: {
                cellEditable = true;
                break;
            }
            case 7: {
                cellEditable = true;
                break;
            }
            case 8: {
                cellEditable = true;
                break;
            }
        }
        return cellEditable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Activo activo = activos.get(rowIndex);

        Object value = null;
        switch (columnIndex) {
            case 0: {
                value = activo.getIdActivo();
                break;
            }
            case 1: {
                value = activo.getCodigo();
                break;
            }
            case 2: {
                value = activo.getCodigoAntiguo();
                break;
            }
            case 3: {
                value = activo.getDescripcion();
                break;
            }
            case 4: {
                value = activo.getEstado();
                break;
            }
            case 5: {
                value = activo.getObservaciones();
                break;
            }
            case 6: {
                value = activo.getCategoria();
                break;
            }
            case 7: {
                value = activo.getUbicacion();
                break;
            }
            case 8: {
                value = activo.getCustodio();
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Activo activo = activos.get(rowIndex);

        switch (columnIndex) {
            case 0: {
                activo.setIdActivo((Integer) aValue);
                break;
            }
            case 1: {
                if (aValue != null) {
                    String codigo = (String) aValue;
                    activo.setCodigo(codigo);

                    activoDao.editarActivo(activo);
                } else {
                    activo.setCodigo(null);
                }
                break;
            }
            case 2: {
                if (aValue != null) {
                    String codigoAntiguo = (String) aValue;
                    activo.setCodigoAntiguo(codigoAntiguo);

                    activoDao.editarActivo(activo);
                } else {
                    activo.setCodigoAntiguo(null);
                }
                break;
            }
            case 3: {
                if (aValue != null) {
                    String descripcion = (String) aValue;
                    activo.setDescripcion(descripcion);

                    activoDao.editarActivo(activo);
                } else {
                    activo.setDescripcion(null);
                }
                break;
            }
            case 4: {
                if (aValue != null) {
                    String estado = (String) aValue;
                    activo.setEstado(estado);

                    activoDao.editarActivo(activo);
                } else {
                    activo.setCodigoAntiguo(null);
                }
                break;
            }
            case 5: {
                if (aValue != null) {
                    String observaciones = (String) aValue;
                    activo.setObservaciones(observaciones);

                    activoDao.editarActivo(activo);
                } else {
                    activo.setObservaciones(null);
                }
                break;
            }
            case 6: {
                if (aValue != null) {
                    Categoria categoria = (Categoria) aValue;
                    activo.setCategoria(categoria);

                    activoDao.editarActivo(activo);
                }
                break;
            }
            case 7: {
                if (aValue != null) {
                    Ubicacion ubicacion = (Ubicacion) aValue;
                    activo.setUbicacion(ubicacion);

                    activoDao.editarActivo(activo);
                }
                break;
            }
            case 8: {
                if (aValue != null) {
                    Custodio custodio = (Custodio) aValue;
                    activo.setCustodio(custodio);

                    activoDao.editarActivo(activo);
                }
                break;
            }
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }

}
