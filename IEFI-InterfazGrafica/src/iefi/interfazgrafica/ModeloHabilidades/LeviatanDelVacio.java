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
public class LeviatanDelVacio extends Habilidad {

    public LeviatanDelVacio() {
        super("Leviatán del Vacío", 3); // necesita 3 turnos de carga
    }

    @Override
    public String ejecutar(Personaje lanzador, Personaje objetivo) {

        StringBuilder mensaje = new StringBuilder();

        // 🔹 Daño = 100% de la salud actual del héroe
        int dano = objetivo.GetSalud();
        objetivo.recibirDano(dano);

        if (objetivo.GetSalud() < 0) {
            objetivo.salud = 0;
        }

        // 🔹 Mensajes del ataque
        mensaje.append(lanzador.GetApodo())
                .append(" invoca a ")
                .append(nombre)
                .append("!\n")
                .append("🌊 El Leviatán desata su furia e inflige ")
                .append(dano)
                .append(" puntos de daño devastador a ")
                .append(objetivo.GetApodo())
                .append(".\n");

        // 🔹 Reinicia tiempo de recarga
        this.turnosCarga = 3;

        // 🔹 Devuelve mensaje formateado
        mensajeHabilidad = mensaje.toString();
        return mensajeHabilidad;
    }
}
