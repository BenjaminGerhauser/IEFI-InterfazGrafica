package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloBatalla.Batalla;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;
import javax.swing.JButton;
import javax.swing.JTextArea;
import vistaBatalla.frmBatalla;

public class ControladorBatalla {

    private Batalla batalla;
    private frmBatalla vista;

    private Personaje heroe;
    private Personaje villano;

    public Personaje getHeroe() {
        return this.heroe;
    }

    public Personaje getVillano() {
        return this.villano;
    }

    public Batalla getBatalla() {
        return this.batalla;
    }

    public ControladorBatalla(Personaje heroe, Personaje villano, frmBatalla vista) {
        this.batalla = new Batalla(heroe, villano);
        this.heroe = heroe;
        this.villano = villano;
        this.vista = vista;
    }

    public void siguienteTurno(JTextArea txtEventos, JButton siguienteTurno, JButton siguienteBatalla) {
        batalla.siguienteTurno(txtEventos);
        vista.actualizarEstado(batalla);

        // Chequeamos si hay un ganador
        String ganador = batalla.chequearVictoria();
        if (!batalla.getHeroe().estaVivo() || !batalla.getVillano().estaVivo()) {
            txtEventos.setText(ganador);
            siguienteTurno.setEnabled(false);
            siguienteBatalla.setEnabled(true);
        }
    }
}
