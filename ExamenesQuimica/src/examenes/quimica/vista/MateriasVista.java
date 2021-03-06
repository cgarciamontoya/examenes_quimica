/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenes.quimica.vista;

import java.sql.Connection;
import examenes.quimica.db.CatalogosDAO;
import examenes.quimica.excepciones.ExamenesQuimicaException;
import examenes.quimica.modelo.CatMateria;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author desarrollo
 */
public class MateriasVista extends FormBase {

    private static final long serialVersionUID = 344990609234180129L;
    private final Connection con;
    private final CatalogosDAO catalogosDAO;
    private List<CatMateria> materias;
    private CatMateria materiaSeleccionada;

    /**
     * Creates new form MateriasVista
     * @param con
     */
    public MateriasVista(Connection con) {
        this.con = con;
        initComponents();
        lblEditando.setVisible(false);
        catalogosDAO = new CatalogosDAO(con);
        materias = catalogosDAO.buscarMateria(null);
        llenarTabla();
    }
    
    private void llenarTabla() {
        limpiarTabla(tblMaterias);
        if (materias != null && !materias.isEmpty()) {
            for (CatMateria mat : materias) {
                ((DefaultTableModel) tblMaterias.getModel()).addRow(new Object[]{mat.getId(), mat.getNombre()});
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

        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaterias = new javax.swing.JTable();
        lblEditando = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Materias");

        jLabel1.setText("Nombre:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarMateria(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar(evt);
            }
        });

        tblMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMaterias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tblMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarMateria(evt);
            }
        });
        jScrollPane1.setViewportView(tblMaterias);
        if (tblMaterias.getColumnModel().getColumnCount() > 0) {
            tblMaterias.getColumnModel().getColumn(0).setResizable(false);
            tblMaterias.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblMaterias.getColumnModel().getColumn(1).setResizable(false);
            tblMaterias.getColumnModel().getColumn(1).setPreferredWidth(465);
        }

        lblEditando.setText("Editando...");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarMateria(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEditando)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addComponent(txtNombre))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiar)
                    .addComponent(lblEditando)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarMateria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarMateria
        limpiarTabla(tblMaterias);
        materias = catalogosDAO.buscarMateria(txtNombre.getText() != null ? txtNombre.getText() : null);
        if (materias == null || materias.isEmpty()) {
            agregarMensajeAdvertencia("No se encontraron resultados");
        } else {
            llenarTabla();
        }
    }//GEN-LAST:event_buscarMateria

    private void limpiar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar
        txtNombre.setText("");
        limpiarTabla(tblMaterias);
        materiaSeleccionada = null;
        lblEditando.setVisible(false);
    }//GEN-LAST:event_limpiar

    private void seleccionarMateria(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarMateria
        if (SwingUtilities.isRightMouseButton(evt)) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Desea editar la materia?", "Advertencia", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                lblEditando.setVisible(true);
                materiaSeleccionada = new CatMateria((Integer) tblMaterias.getValueAt(tblMaterias.getSelectedRow(), 0), 
                        (String) tblMaterias.getValueAt(tblMaterias.getSelectedRow(), 1));
                txtNombre.setText(materiaSeleccionada.getNombre());
            }
        }
    }//GEN-LAST:event_seleccionarMateria

    private void guardarMateria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarMateria
        if (txtNombre.getText() == null || txtNombre.getText().isEmpty()) {
            agregarMensajeError("El nombre de la materia es requerido");
            return;
        }
        boolean nuevo = false;
        if (materiaSeleccionada == null) {
            materiaSeleccionada = new CatMateria(0, txtNombre.getText());
            nuevo = true;
        } else {
            materiaSeleccionada.setNombre(txtNombre.getText());
        }
        try {
            catalogosDAO.guardarMateria(materiaSeleccionada);
            if (nuevo) {
                limpiar(evt);
                buscarMateria(evt);
            } else {
                tblMaterias.setValueAt(materiaSeleccionada.getNombre().toUpperCase(), 
                        tblMaterias.getSelectedRow(), 1);
            }
            agregarMensajeExito("La materia fue guardada correctamente");
        } catch (ExamenesQuimicaException ex) {
            agregarMensajeError(ex.getMessage());
        }
    }//GEN-LAST:event_guardarMateria


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEditando;
    private javax.swing.JTable tblMaterias;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
