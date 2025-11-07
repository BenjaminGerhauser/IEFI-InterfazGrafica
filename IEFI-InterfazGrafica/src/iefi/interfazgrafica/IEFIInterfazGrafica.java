/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iefi.interfazgrafica;

import vistaConfiguracion.frmVistaConfiguracion;

/**
 *
 * @author Benja
 */
public class IEFIInterfazGrafica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            frmVistaConfiguracion ventana = new frmVistaConfiguracion();
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
        });
    }
}
