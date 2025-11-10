package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloPersonajes.Personaje;



public class ControladorArmas {

    public interface Listener {
        void onLog(String linea);
    }

    private Listener listener;
    public void setListener(Listener l) { this.listener = l; }

    
    public void invocarArma(Personaje p) {
        if (p == null) return;
        p.invocarArma();
        if (listener != null) listener.onLog(p.GetApodo() + " invoca/equipa su arma.");
    }
}
