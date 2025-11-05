// Ajustá el package según tu proyecto, por ejemplo:
// package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloBatalla.Batalla;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;

/**
 * Controlador de la Batalla: encapsula el ciclo de turnos sin I/O por consola
 * para integrarlo fácilmente con una interfaz gráfica.
 */
public class ControladorBatalla {
    private final Batalla batalla;

    /** Crea la batalla a partir de dos personajes (héroe y villano). */
    public ControladorBatalla(Personaje heroe, Personaje villano) {
        this.batalla = new Batalla(heroe, villano);
    }

    /** Usa una instancia existente del modelo de batalla. */
    public ControladorBatalla(Batalla batalla) {
        this.batalla = batalla;
    }

    /** Ejecuta una ronda: ataca el héroe y, si el villano sigue vivo, ataca el villano. */
    public void jugarRonda() {
        Personaje h = getHeroe();
        Personaje v = getVillano();
        h.atacar(h, v);
        if (v.estaVivo()) v.atacar(v, h);
    }

    /**
     * @return "Héroe", "Villano", "Empate" o null si la batalla continúa.
     * (Se apoya en la lógica del modelo.)
     */
    public String chequearResultado() {
        Personaje h = getHeroe();
        Personaje v = getVillano();
        if (!h.estaVivo() || !v.estaVivo()) {
            return batalla.chequearVictoria();
        }
        return null;
    }

    /** Helpers para acceder a los combatientes (requiere getters en Batalla). */
    public Personaje getHeroe()   { return batalla.getHeroe();   } // agregá getters en el modelo si faltan
    public Personaje getVillano() { return batalla.getVillano(); }

    public Batalla getModelo() { return batalla; }
}
