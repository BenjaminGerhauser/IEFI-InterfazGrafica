/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iefi.interfazgrafica.ModeloHabilidades;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;
/**
 *
 * @author Juani
 */
public abstract class Habilidad {
    public String nombre;
    public int turnosCarga;
    protected String mensajeHabilidad;

    public Habilidad(String nombre, int turnosCarga) {
        this.nombre = nombre;
        this.turnosCarga = turnosCarga;
    }

    public boolean estaDisponible() {
        return turnosCarga <= 0;
    }

    public abstract String ejecutar(Personaje lanzador, Personaje objetivo);

    public void pasarTurno() {
        if (turnosCarga > 0) {
            turnosCarga--;
        }
    }
}



