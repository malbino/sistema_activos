/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.gui;

import com.itextpdf.text.DocumentException;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.malbino.daos.ActivoDao;
import org.malbino.daos.CategoriaDao;
import org.malbino.daos.CustodioDao;
import org.malbino.daos.UbicacionDao;
import org.malbino.gui.cellrenderers.CellRenderActivo;
import org.malbino.gui.tablemodels.TableModelActivo;
import org.malbino.models.Activo;
import org.malbino.models.Categoria;
import org.malbino.models.Custodio;
import org.malbino.models.Ubicacion;
import org.malbino.pdfs.EtiquetasPDF;

/**
 *
 * @author Tincho
 */
public class Etiquetas extends javax.swing.JInternalFrame {

    CategoriaDao categoriaDao;
    UbicacionDao ubicacionDao;
    CustodioDao custodioDao;

    ActivoDao activoDao;

    /**
     * Creates new form activos
     */
    public Etiquetas() {
        initComponents();

        categoriaDao = new CategoriaDao();
        ubicacionDao = new UbicacionDao();
        custodioDao = new CustodioDao();
        activoDao = new ActivoDao();
        
        jTable1.getTableHeader().setReorderingAllowed(false);
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public void actualizarLista() {
        List<Activo> listaActivos = activoDao.listaActivos();
        TableModelActivo tableModelActivo = new TableModelActivo(listaActivos);
        jTable1.setModel(tableModelActivo);

        Object[] estados = {"MUY MALO", "MALO", "REGULAR", "BUENO", "MUY BUENO"};
        DefaultComboBoxModel dcbmEstado = new DefaultComboBoxModel(estados);
        jTable1.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JComboBox(dcbmEstado)));
        jTable1.setDefaultRenderer(Object.class, new CellRenderActivo(4));

        List<Categoria> listaCategorias = categoriaDao.listaCategorias();
        DefaultComboBoxModel dcbmCategoria = new DefaultComboBoxModel(listaCategorias.toArray());
        jTable1.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JComboBox(dcbmCategoria)));
        jTable1.setDefaultRenderer(Object.class, new CellRenderActivo(6));

        List<Ubicacion> listaUbicaciones = ubicacionDao.listaUbicaciones();
        DefaultComboBoxModel dcbmUbicacion = new DefaultComboBoxModel(listaUbicaciones.toArray());
        jTable1.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(new JComboBox(dcbmUbicacion)));
        jTable1.setDefaultRenderer(Object.class, new CellRenderActivo(7));

        List<Custodio> listaCustodios = custodioDao.listaCustodios();
        DefaultComboBoxModel dcbmCustodio = new DefaultComboBoxModel(listaCustodios.toArray());
        jTable1.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JComboBox(dcbmCustodio)));
        jTable1.setDefaultRenderer(Object.class, new CellRenderActivo(8));
        
        
        this.resizeColumnWidth(jTable1);
    }
    
    public void exportarEtiquetas() {
        int[] selectedRows = jTable1.getSelectedRows();
        if (selectedRows.length > 0) {
            List<Activo> activos = new ArrayList<>();
            for (int row : selectedRows) {
                String codigo = (String) jTable1.getModel().getValueAt(row, 1);
                String codigoAntiguo = (String) jTable1.getModel().getValueAt(row, 2);
                String descripcion = (String) jTable1.getModel().getValueAt(row, 3);
                String estado = (String) jTable1.getModel().getValueAt(row, 4);
                
                Activo activo = new Activo(codigo, codigoAntiguo, descripcion, estado);
                activos.add(activo);
            }
            
            jFileChooser1.setSelectedFile(new File("Etiquetas.pdf"));

            int seleccion = jFileChooser1.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                if (jFileChooser1.getSelectedFile() != null) {
                    try {
                        EtiquetasPDF etiquetasPDF = new EtiquetasPDF();
                        etiquetasPDF.generarPDF(activos, jFileChooser1.getSelectedFile());
                    } catch (IOException | DocumentException ex) {
                        Logger.getLogger(Etiquetas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Activos");
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setVisible(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/malbino/img/impresora.png"))); // NOI18N
        jButton1.setText("Imprimir Etiquetas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportarEtiquetas();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
