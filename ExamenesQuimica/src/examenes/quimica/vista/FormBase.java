/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenes.quimica.vista;

import examenes.quimica.modelo.CatMateria;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sandra
 */
public class FormBase extends JInternalFrame {
    
    public void agregarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje,
                    "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void agregarMensajeError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void agregarMensajeAdvertencia(String advertencia) {
        JOptionPane.showMessageDialog(this, advertencia, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    public String mensajeValidacion(List<String> errores) {
        String error = "No se puede guardar el registro por los siguientes errores de validación: \n\r";
        for (String er : errores) {
            error += "\t* " + er + "\n\r";
        }
        return error;
    }
    
    public void limpiarTabla(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(model.getRowCount() - 1);
        }
    }
    
    public boolean confirmarPregunta(java.awt.event.MouseEvent evt, String mensaje) {
        if (SwingUtilities.isRightMouseButton(evt)) {
            int dialogResult =  JOptionPane.showConfirmDialog(null, mensaje, "Advertencia", JOptionPane.YES_NO_OPTION);
             return dialogResult == JOptionPane.YES_OPTION;
        }
        return false;
    }
}
