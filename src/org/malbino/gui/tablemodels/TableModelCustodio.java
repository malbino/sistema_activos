/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.gui.tablemodels;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.malbino.daos.CustodioDao;
import org.malbino.models.Custodio;

/**
 *
 * @author tincho
 */
public class TableModelCustodio implements TableModel {

    private List<Custodio> custodios;
    
    CustodioDao custodioDao;

    public TableModelCustodio(List<Custodio> custodios) {
        this.custodios = custodios;
        
        custodioDao = new CustodioDao();
    }

    @Override
    public int getRowCount() {
        return custodios.size();
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
        Custodio custodio = custodios.get(rowIndex);

        Object value = null;
        switch (columnIndex) {
            case 0: {
                value = custodio.getIdCustodio();
                break;
            }
            case 1: {
                value = custodio.getNombre();
                break;
            }
            case 2: {
                value = custodio.getObservaciones();
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Custodio custodio = custodios.get(rowIndex);

        switch (columnIndex) {
            case 0: {
                custodio.setIdCustodio((Integer) aValue);
                break;
            }
            case 1: {
                if (aValue != null) {
                    String nombre = (String) aValue;
                    custodio.setNombre(nombre);
                    
                    custodioDao.editarCustodio(custodio);
                } else {
                    custodio.setNombre(null);
                }
                break;
            }
            case 2: {
                if (aValue != null) {
                    String observaciones = (String) aValue;
                    custodio.setObservaciones(observaciones);
                    
                    custodioDao.editarCustodio(custodio);
                } else {
                    custodio.setObservaciones(null);
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
