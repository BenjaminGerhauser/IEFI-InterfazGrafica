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
public class CastigoBendito extends Habilidad {

    public CastigoBendito() {
        super("Castigo Bendito", 0); // se puede usar inmediatamente al 100%
    }

    @Override
    public String ejecutar(Personaje lanzador, Personaje objetivo) {
        StringBuilder mensaje = new StringBuilder();

        int dano = lanzador.GetSalud() / 2; // 50% de la vida actual del héroe
        objetivo.recibirDano(dano);

        if (objetivo.GetSalud() < 0) {
            objetivo.salud = 0;
        }

        mensaje.append(lanzador.GetApodo())
                .append(" lanza ")
                .append(nombre)
                .append("!\n")
                .append("⚔️ El rayo divino inflige ")
                .append(dano)
                .append(" puntos de daño directo a ")
                .append(objetivo.GetApodo())
                .append(".\n");

        // La bendición vuelve a la mitad después de lanzarlo
        lanzador.bendicion = 50;
        lanzador.habilidad = null;

        mensaje.append("✨ La bendición de ")
                .append(lanzador.GetApodo())
                .append(" se reduce al 50% tras liberar tanto poder divino.\n");

        return mensaje.toString();

    }
}
