package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloBatalla.Batalla;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;
import javax.swing.JTextArea;
import vistaBatalla.frmBatalla;

public class ControladorBatalla {

    private Batalla batalla;
    private frmBatalla vista;

    private Personaje heroe;
    private Personaje villano;

    public ControladorBatalla(Personaje heroe, Personaje villano, frmBatalla vista) {
        this.batalla = new Batalla(heroe, villano);
        this.heroe = heroe;
        this.villano = villano;
        this.vista = vista;
    }

    public void siguienteTurno(JTextArea txtEventos) {
        batalla.siguienteTurno();
        vista.actualizarEstado(batalla);

        // Chequeamos si hay un ganador
        String ganador = batalla.chequearVictoria();
        if (!batalla.getHeroe().estaVivo() || !batalla.getVillano().estaVivo()) {
            txtEventos.setText(ganador);
        }

    }

}
