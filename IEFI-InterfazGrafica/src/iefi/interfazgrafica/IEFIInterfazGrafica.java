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
        try {
            // 🔹 Aplica el mismo look and feel del sistema operativo
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 🔹 Crea y muestra la interfaz en el hilo de eventos de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            frmVistaConfiguracion ventana = new frmVistaConfiguracion();

            // 🔹 Ajusta automáticamente el tamaño según los componentes
            ventana.pack();

            // 🔹 Centra la ventana en la pantalla
            ventana.setLocationRelativeTo(null);

            // 🔹 Muestra la ventana
            ventana.setVisible(true);
        });
    }
}
