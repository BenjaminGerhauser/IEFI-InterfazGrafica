// Ajustá el package según tu proyecto, por ejemplo:
// package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloPersonajes.Personaje;
import iefi.interfazgrafica.ModeloPersonajes.Heroe;
import iefi.interfazgrafica.ModeloPersonajes.Villano;

/**
 * Operaciones comunes sobre personajes (creación y acciones típicas).
 */
public class ControladorPersonajes {

    /** Crea un héroe con estadísticas base. */
    public Heroe crearHeroe(String apodo, int salud, int defensa, int ataque) {
        Heroe h = new Heroe();
        trySet(h, "apodo", apodo);
        trySet(h, "salud",  salud);
        trySet(h, "defensa", defensa);
        trySet(h, "ataque", ataque);
        return h;
    }

    /** Crea un villano con estadísticas base. */
    public Villano crearVillano(String apodo, int salud, int defensa, int ataque) {
        Villano v = new Villano();
        trySet(v, "apodo", apodo);
        trySet(v, "salud",  salud);
        trySet(v, "defensa", defensa);
        trySet(v, "ataque", ataque);
        return v;
    }

    /** Ordena a un personaje atacar a otro, delegando la lógica al modelo. */
    public void atacar(Personaje atacante, Personaje objetivo) {
        atacante.atacar(atacante, objetivo);
    }

    /** Incrementa bendición/energía según el modelo. */
    public void cargarBendicion(Personaje personaje) {
        personaje.cargarBendicion();
    }

    /** Invoca/evoluciona el arma del personaje según su bendición. */
    public void invocarArma(Personaje personaje) {
        personaje.invocarArma();
    }

    /** Intenta cargar la habilidad (ulti) si corresponde. */
    public void cargarHabilidad(Personaje personaje) {
        personaje.cargarHabilidad();
    }

    /** Estado breve útil para UI. */
    public String getEstado(Personaje p) {
        try {
            String apodo = String.valueOf(Personaje.class.getMethod("GetApodo").invoke(p));
            int salud     = (int) Personaje.class.getMethod("GetSalud").invoke(p);
            return apodo + " [HP=" + salud + "]";
        } catch (Exception e) {
            try {
                String apodo = String.valueOf(Personaje.class.getMethod("getApodo").invoke(p));
                int salud     = (int) Personaje.class.getMethod("getSalud").invoke(p);
                return apodo + " [HP=" + salud + "]";
            } catch (Exception ex) {
                return "[Estado no disponible]";
            }
        }
    }

    // --- Utilidad: setea por setter si existe; si no, intenta campo público ---
    private void trySet(Object obj, String prop, Object value) {
        String setter = "set" + Character.toUpperCase(prop.charAt(0)) + prop.substring(1);
        try {
            if (value instanceof Integer) {
                obj.getClass().getMethod(setter, int.class).invoke(obj, ((Integer) value).intValue());
            } else {
                obj.getClass().getMethod(setter, value.getClass()).invoke(obj, value);
            }
        } catch (Exception ignore) {
            try {
                java.lang.reflect.Field f = obj.getClass().getField(prop);
                f.setAccessible(true);
                f.set(obj, value);
            } catch (Exception ignored) {}
        }
    }
}
