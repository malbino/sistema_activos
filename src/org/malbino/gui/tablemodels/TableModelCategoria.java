/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.gui.tablemodels;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.malbino.daos.CategoriaDao;
import org.malbino.models.Categoria;

/**
 *
 * @author tincho
 */
public class TableModelCategoria implements TableModel {

    private List<Categoria> categorias;
    
    CategoriaDao categoriaDao;

    public TableModelCategoria(List<Categoria> categorias) {
        this.categorias = categorias;
        
        categoriaDao = new CategoriaDao();
    }

    @Override
    public int getRowCount() {
        return categorias.size();
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
        Categoria categoria = categorias.get(rowIndex);

        Object value = null;
        switch (columnIndex) {
            case 0: {
                value = categoria.getIdCategoria();
                break;
            }
            case 1: {
                value = categoria.getNombre();
                break;
            }
            case 2: {
                value = categoria.getObservaciones();
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Categoria categoria = categorias.get(rowIndex);

        switch (columnIndex) {
            case 0: {
                categoria.setIdCategoria((Integer) aValue);
                break;
            }
            case 1: {
                if (aValue != null) {
                    String nombre = (String) aValue;
                    categoria.setNombre(nombre);
                    
                    categoriaDao.editarCategoria(categoria);
                } else {
                    categoria.setNombre(null);
                }
                break;
            }
            case 2: {
                if (aValue != null) {
                    String observaciones = (String) aValue;
                    categoria.setObservaciones(observaciones);
                    
                    categoriaDao.editarCategoria(categoria);
                } else {
                    categoria.setObservaciones(null);
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
