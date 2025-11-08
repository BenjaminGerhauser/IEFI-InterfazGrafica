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
public class HozOxidada extends Arma {

    public HozOxidada(Personaje villano) {
        super("Hoz Oxidada", 4);
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

        // 🔹 Sin efecto especial
        mensaje.append(this.nombre)
                .append(" no tiene efectos especiales.\n");

        return mensaje.toString();
    }

}
