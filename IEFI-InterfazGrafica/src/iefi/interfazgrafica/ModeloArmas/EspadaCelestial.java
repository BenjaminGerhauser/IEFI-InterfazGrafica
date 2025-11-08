/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iefi.interfazgrafica.ModeloArmas;

import iefi.interfazgrafica.ModeloPersonajes.Personaje;

/**
 *
 * @author Alumno
 */
public class EspadaCelestial extends Arma {

    public EspadaCelestial(Personaje heroe) {
        super("Espada Celestial", 10);
        heroe.incrementarAtaque(danioExtra);
        mensajeEfecto = (this.nombre + " brinda " + danioExtra + " de puntos de ataque.\n");
    }

    @Override
    public String usarEfectoEspecial(Personaje portador) {
        StringBuilder mensaje = new StringBuilder();

        // 🔹 Mostrar mensaje inicial solo la primera vez
        if (!efectoInicialAplicado) {
            mensaje.append(mensajeEfecto);
            efectoInicialAplicado = true;
        }

        // 🔹 Efecto especial (siempre se ejecuta)
        int curacion = 8;
        int incrementoDefensa = 3;

        portador.curarse(curacion);
        portador.incrementarDefensa(incrementoDefensa);

        mensaje.append(this.nombre)
                .append(" cura ")
                .append(curacion)
                .append(" puntos de vida.\n")
                .append(this.nombre)
                .append(" incrementa defensa en ")
                .append(incrementoDefensa)
                .append(" puntos.\n");

        return mensaje.toString();
    }
}
