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
public class EspadaSagrada extends Arma {

    public EspadaSagrada(Personaje heroe) {
        super("Espada Sagrada", 7);
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

        // 🔹 Efecto especial (siempre)
        int curacion = 5;
        portador.curarse(curacion);

        mensaje.append(this.nombre)
                .append(" cura ")
                .append(curacion)
                .append(" puntos de vida.\n");

        return mensaje.toString();
    }

}
