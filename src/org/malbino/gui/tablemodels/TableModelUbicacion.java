/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.gui.tablemodels;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.malbino.daos.UbicacionDao;
import org.malbino.models.Ubicacion;

/**
 *
 * @author tincho
 */
public class TableModelUbicacion implements TableModel {

    private List<Ubicacion> ubicacions;
    
    UbicacionDao ubicacionDao;

    public TableModelUbicacion(List<Ubicacion> ubicacions) {
        this.ubicacions = ubicacions;
        
        ubicacionDao = new UbicacionDao();
    }

    @Override
    public int getRowCount() {
        return ubicacions.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
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
                columnName = "Nombre";
                break;
            }
            case 2: {
                columnName = "Observaciones";
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
        }
        return cellEditable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ubicacion ubicacion = ubicacions.get(rowIndex);

        Object value = null;
        switch (columnIndex) {
            case 0: {
                value = ubicacion.getIdUbicacion();
                break;
            }
            case 1: {
                value = ubicacion.getNombre();
                break;
            }
            case 2: {
                value = ubicacion.getObservaciones();
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Ubicacion ubicacion = ubicacions.get(rowIndex);

        switch (columnIndex) {
            case 0: {
                ubicacion.setIdUbicacion((Integer) aValue);
                break;
            }
            case 1: {
                if (aValue != null) {
                    String nombre = (String) aValue;
                    ubicacion.setNombre(nombre);
                    
                    ubicacionDao.editarUbicacion(ubicacion);
                } else {
                    ubicacion.setNombre(null);
                }
                break;
            }
            case 2: {
                if (aValue != null) {
                    String observaciones = (String) aValue;
                    ubicacion.setObservaciones(observaciones);
                    
                    ubicacionDao.editarUbicacion(ubicacion);
                } else {
                    ubicacion.setObservaciones(null);
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
