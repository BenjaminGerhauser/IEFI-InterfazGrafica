package iefi.interfazgrafica.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;


public class ControladorPersonajes {
   public final Personaje personaje;

    public ControladorPersonajes(Personaje personaje) {
        this.personaje = personaje;
    }
    
    public void crearPersonaje(String apodo, int salud, int defensa, int ataque, int bendicion) {
        personaje.crearPersonaje(apodo, salud, defensa, ataque, bendicion);
    }
    
}
