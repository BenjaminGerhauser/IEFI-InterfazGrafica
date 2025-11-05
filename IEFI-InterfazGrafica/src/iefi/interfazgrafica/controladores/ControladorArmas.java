
package iefi.interfazgrafica.controladores;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;
import iefi.interfazgrafica.ModeloArmas.Arma;
import iefi.interfazgrafica.ModeloArmas.EspadaSimple;
import iefi.interfazgrafica.ModeloArmas.EspadaSagrada;
import iefi.interfazgrafica.ModeloArmas.EspadaCelestial;
import iefi.interfazgrafica.ModeloArmas.HozOxidada;
import iefi.interfazgrafica.ModeloArmas.HozVenenosa;
import iefi.interfazgrafica.ModeloArmas.HozMortifera;

/**
 * Controlador para gestionar las armas de un personaje.
 */
public class ControladorArmas {

    /** Invoca/evoluciona el arma según la bendición (delegando en el modelo). */
    public void invocarSegunBendicion(Personaje p) {
        p.invocarArma();
    }

    /**
     * Crea un arma por nombre (útil si la UI permite selección directa).
     * Devolvemos la instancia; si necesitás asociarla al personaje, hacelo con el setter de tu modelo.
     */
    public Arma crearPorNombre(Personaje p, String nombreArma) {
        String n = nombreArma == null ? "" : nombreArma.trim().toLowerCase();
        switch (n) {
            case "espada simple":    return new EspadaSimple(p);
            case "espada sagrada":   return new EspadaSagrada(p);
            case "espada celestial": return new EspadaCelestial(p);
            case "hoz oxidada":      return new HozOxidada(p);
            case "hoz venenosa":     return new HozVenenosa(p);
            case "hoz mortifera":
            case "hoz mortífera":    return new HozMortifera(p);
            default:
                throw new IllegalArgumentException("Arma desconocida: " + nombreArma);
        }
    }

    /** Dispara el efecto especial del arma actualmente invocada (si existe). */
    public void usarEfectoEspecial(Personaje p) {
        try {
            java.lang.reflect.Field f = Personaje.class.getField("arma");
            Object arma = f.get(p);
            if (arma instanceof Arma) ((Arma) arma).usarEfectoEspecial(p);
        } catch (Exception ignore) {
            // Si tu modelo usa getter, reemplazá por p.getArma().usarEfectoEspecial(p);
        }
    }
}
