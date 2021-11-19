/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.gui;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
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

/**
 *
 * @author Tincho
 */
public class Activos extends javax.swing.JInternalFrame {

    CategoriaDao categoriaDao;
    UbicacionDao ubicacionDao;
    CustodioDao custodioDao;

    ActivoDao activoDao;

    /**
     * Creates new form activos
     */
    public Activos() {
        initComponents();

        categoriaDao = new CategoriaDao();
        ubicacionDao = new UbicacionDao();
        custodioDao = new CustodioDao();
        activoDao = new ActivoDao();

        jFileChooser1.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes()));
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

    public void nuevaActivo() {
        Activo activo = new Activo("", "", "", "", "", null, null, null);
        activoDao.nuevaActivo(activo);

        actualizarLista();
    }

    public void subirFoto(Activo activo) {
        int seleccion = jFileChooser1.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            FTPClient ftpClient = new FTPClient();

            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream("ftp.properties"));

                String host = prop.getProperty("host");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");

                ftpClient.connect(host, 21);
                ftpClient.login(user, password);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                File localFile = jFileChooser1.getSelectedFile();
                String ext = FilenameUtils.getExtension(localFile.getPath());

                String remoteFile = "/web/sisac/" + activo.getIdActivo() + "." + ext;
                InputStream inputStream = new FileInputStream(localFile);

                boolean done = ftpClient.storeFile(remoteFile, inputStream);
                inputStream.close();

                if (done) {
                    activo.setFoto(activo.getIdActivo() + "." + ext);
                    activoDao.editarActivo(activo);
                }
            } catch (IOException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    public void verFoto(Activo activo) {
        try {
            JDialog dialog = new JDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setResizable(false);
            dialog.setTitle("Foto");

            Properties prop = new Properties();
            prop.load(new FileInputStream("ftp.properties"));

            String url = prop.getProperty("url");

            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new URL(url + activo.getFoto())));
            dialog.add(new JLabel(imageIcon));

            dialog.pack();
            dialog.setLocationByPlatform(true);
            dialog.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Activos.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jFileChooser1.setAcceptAllFileFilterUsed(false);

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

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/malbino/img/nuevo.png"))); // NOI18N
        jButton1.setText("Nuevo Activo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/malbino/img/upload.png"))); // NOI18N
        jButton2.setText("Subir Foto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/malbino/img/ver.png"))); // NOI18N
        jButton3.setText("Ver Foto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        nuevaActivo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            Activo activo = activoDao.buscarActivo((int) jTable1.getModel().getValueAt(selectedRow, 0));
            if (activo != null) {
                subirFoto(activo);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            Activo activo = activoDao.buscarActivo((int) jTable1.getModel().getValueAt(selectedRow, 0));
            if (activo != null && activo.getFoto() != null) {
                verFoto(activo);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
