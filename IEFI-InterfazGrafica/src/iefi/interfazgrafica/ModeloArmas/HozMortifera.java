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
public class HozMortifera extends Arma {

    public HozMortifera(Personaje villano) {
        super("Hoz Mortífera", 8); // Daño base más alto aún
        villano.incrementarAtaque(danioExtra);
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

        // 🔹 Efecto especial (siempre)
        int incrementoAtaque = 8;   // aumento principal
        int incrementoVeneno = 6;   // aumento adicional por veneno
        int curacion = 8;           // curación al usarla

        portador.incrementarAtaque(incrementoAtaque + incrementoVeneno);
        portador.curarse(curacion);

        mensaje.append(this.nombre)
                .append(" incrementa ataque en ")
                .append(incrementoAtaque)
                .append(" puntos.\n")
                .append(this.nombre)
                .append(" añade un poder venenoso que aumenta ")
                .append(incrementoVeneno)
                .append(" puntos de ataque adicionales.\n")
                .append(this.nombre)
                .append(" cura al portador en ")
                .append(curacion)
                .append(" puntos de vida.\n");

        return mensaje.toString();
    }

}
