package iefi.interfazgrafica.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;


public class ControladorPersonajes {

    public interface Listener {
        void onListaActualizada(List<Personaje> lista);
        void onSeleccion(Personaje seleccionado);
    }

    private final List<Personaje> personajes = new ArrayList<>();
    private Personaje seleccionado;
    private Listener listener;

    public void setListener(Listener l) { this.listener = l; }

    
    public void agregar(Personaje p) {
        if (p == null) return;
        personajes.add(p);
        notificarLista();
    }

    public boolean eliminarPorApodo(String apodo) {
        boolean removed = personajes.removeIf(p -> p != null && p.GetApodo().equalsIgnoreCase(apodo));
        if (removed) {
            if (seleccionado != null && seleccionado.GetApodo().equalsIgnoreCase(apodo)) {
                seleccionado = null;
                if (listener != null) listener.onSeleccion(null);
            }
            notificarLista();
        }
        return removed;
    }

    public Optional<Personaje> buscarPorApodo(String apodo) {
        return personajes.stream().filter(p -> p.GetApodo().equalsIgnoreCase(apodo)).findFirst();
    }

    public List<Personaje> listar() { return Collections.unmodifiableList(personajes); }

    public void seleccionar(Personaje p) {
        this.seleccionado = p;
        if (listener != null) listener.onSeleccion(p);
    }

    public Personaje getSeleccionado() { return seleccionado; }

    
    public void herirSeleccionado(int dano) {
        if (seleccionado == null) return;
        seleccionado.recibirDano(dano);
        notificarLista();
        if (listener != null) listener.onSeleccion(seleccionado);
    }

    private void notificarLista() {
        if (listener != null) listener.onListaActualizada(Collections.unmodifiableList(personajes));
    }
}
