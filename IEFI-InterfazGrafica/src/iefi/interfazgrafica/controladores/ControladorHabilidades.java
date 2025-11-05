// Ajustá el package según tu proyecto, por ejemplo:
// package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloPersonajes.Personaje;
import iefi.interfazgrafica.ModeloHabilidades.Habilidad;

/**
 * Controlador para orquestar el uso y la recarga de habilidades.
 */
public class ControladorHabilidades {

    /** Ejecuta la habilidad si está disponible. Devuelve true si se ejecutó. */
    public boolean ejecutarSiDisponible(Personaje lanzador, Personaje objetivo) {
        Habilidad h = obtenerHabilidad(lanzador);
        if (h != null && h.estaDisponible()) {
            h.ejecutar(lanzador, objetivo);
            return true;
        }
        return false;
    }

    /** Avanza un turno de recarga para la habilidad (si existe). */
    public void pasarTurno(Personaje p) {
        Habilidad h = obtenerHabilidad(p);
        if (h != null) h.pasarTurno();
    }

    /** Intenta cargar/desbloquear la habilidad definitiva según la lógica del modelo. */
    public void cargarUltima(Personaje p) {
        p.cargarHabilidad();
    }

    // --- Utilidad para tolerar diferencias de API del modelo ---
    private Habilidad obtenerHabilidad(Personaje p) {
        try {
            // Si tu modelo expone getHabilidad():
            return (Habilidad) Personaje.class.getMethod("getHabilidad").invoke(p);
        } catch (Exception ignore) {
            try {
                // Si es un campo público llamado "habilidad":
                java.lang.reflect.Field f = Personaje.class.getField("habilidad");
                return (Habilidad) f.get(p);
            } catch (Exception e) {
                return null;
            }
        }
    }
}
