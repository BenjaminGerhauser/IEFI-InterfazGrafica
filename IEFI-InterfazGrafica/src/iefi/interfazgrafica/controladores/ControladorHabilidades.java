package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloHabilidades.Habilidad;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;



public class ControladorHabilidades {

    public interface Listener {
        void onLog(String linea);
    }

    private Listener listener;
    public void setListener(Listener l) { this.listener = l; }

    
    public void cargarHabilidad(Personaje lanzador) {
        if (lanzador == null) return;
        lanzador.cargarHabilidad();
        if (listener != null) listener.onLog(lanzador.GetApodo() + " intenta cargar su habilidad.");
    }

    
    public void ejecutar(Habilidad habilidad, Personaje lanzador, Personaje objetivo) {
        if (habilidad == null || lanzador == null || objetivo == null) return;
        if (habilidad.estaDisponible()) {
            habilidad.ejecutar(lanzador, objetivo);
            if (listener != null) listener.onLog(lanzador.GetApodo() + " usó " + habilidad.nombre + " sobre " + objetivo.GetApodo());
        } else {
            if (listener != null) listener.onLog("La habilidad aún no está disponible.");
        }
    }
}
