package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloBatalla.Batalla;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;




public class ControladorBatalla {

    private Batalla batalla;
    private Personaje heroe;
    private Personaje villano;

    public interface Listener {
        void onLog(String linea);
        void onFin(String ganador);
        void onCambioTurno(int turnoActual, String deQuien);
        void onEstado(int turnoActual, int saludHeroe, int saludVillano);
    }

    private Listener listener;

    public ControladorBatalla(Batalla batalla, Personaje heroe, Personaje villano) {
        this.batalla = batalla;
        this.heroe = heroe;
        this.villano = villano;
    }

    public void setListener(Listener l) { this.listener = l; }

    public Batalla getBatalla() { return batalla; }
    public Personaje getHeroe() { return heroe; }
    public Personaje getVillano() { return villano; }

    public void setBatalla(Batalla batalla) { this.batalla = batalla; }
    public void setHeroe(Personaje heroe) { this.heroe = heroe; }
    public void setVillano(Personaje villano) { this.villano = villano; }

    
    public void turnoDesdeModelo() {
        if (estaFinalizada()) return;
        if (batalla != null) {
            try {
                batalla.siguienteTurno();
                notificarEstado();
                chequearFin();
                return;
            } catch (Throwable ignored) {
                
            }
        }
        
        turnoManual();
    }

    
    private int turnoAlternador = 1;
    public void turnoManual() {
        if (estaFinalizada()) return;
        Personaje atacante = (turnoAlternador % 2 != 0) ? heroe : villano;
        Personaje objetivo  = (turnoAlternador % 2 != 0) ? villano : heroe;

        if (listener != null) listener.onCambioTurno(turnoAlternador, atacante.GetApodo());
        if (listener != null) listener.onLog("Turno " + turnoAlternador + " de " + atacante.GetApodo());

        
        atacante.atacar(atacante, objetivo);

        if (listener != null) listener.onEstado(turnoAlternador, heroe.GetSalud(), villano.GetSalud());

        turnoAlternador++;
        chequearFin();
    }

    
    public void atacarHeroe() {
        if (estaFinalizada()) return;
        heroe.atacar(heroe, villano);
        if (listener != null) listener.onLog(heroe.GetApodo() + " ejecutó un ataque.");
        notificarEstado();
        chequearFin();
    }

    
    public void atacarVillano() {
        if (estaFinalizada()) return;
        villano.atacar(villano, heroe);
        if (listener != null) listener.onLog(villano.GetApodo() + " ejecutó un ataque.");
        notificarEstado();
        chequearFin();
    }

    
    public void invocarArmaHeroe() {
        heroe.invocarArma();
        if (listener != null) listener.onLog(heroe.GetApodo() + " invoca arma.");
        notificarEstado();
    }
    public void invocarArmaVillano() {
        villano.invocarArma();
        if (listener != null) listener.onLog(villano.GetApodo() + " invoca arma.");
        notificarEstado();
    }

    
    public void cargarBendicionHeroe() {
        heroe.cargarBendicion();
        if (listener != null) listener.onLog(heroe.GetApodo() + " carga bendición.");
        notificarEstado();
    }
    public void cargarHabilidadHeroe() {
        heroe.cargarHabilidad();
        if (listener != null) listener.onLog(heroe.GetApodo() + " intenta cargar habilidad.");
        notificarEstado();
    }
    public void cargarHabilidadVillano() {
        villano.cargarHabilidad();
        if (listener != null) listener.onLog(villano.GetApodo() + " intenta cargar habilidad.");
        notificarEstado();
    }

    
    public boolean estaFinalizada() {
        return heroe.GetSalud() <= 0 || villano.GetSalud() <= 0;
    }

    
    public String getGanador() {
        if (batalla != null) {
            try {
                java.lang.reflect.Method m = batalla.getClass().getMethod("ganador");
                Object res = m.invoke(batalla);
                if (res instanceof String) return (String) res;
            } catch (Throwable ignored) {}
        }
        if (heroe.GetSalud() > 0 && villano.GetSalud() <= 0) return heroe.GetApodo();
        if (villano.GetSalud() > 0 && heroe.GetSalud() <= 0) return villano.GetApodo();
        if (heroe.GetSalud() <= 0 && villano.GetSalud() <= 0) return "Empate";
        return null;
    }

    
    private void chequearFin() {
        if (estaFinalizada() && listener != null) {
            String g = getGanador();
            listener.onFin(g != null ? g : "Empate");
        }
    }

    
    private void notificarEstado() {
        boolean mostroModelo = false;
        if (batalla != null) {
            try {
                batalla.mostrarEstado();
                mostroModelo = true;
            } catch (Throwable ignored) {}
        }
        if (!mostroModelo && listener != null) {
            listener.onEstado(Math.max(1, turnoAlternador - 1), heroe.GetSalud(), villano.GetSalud());
        }
    }
}
