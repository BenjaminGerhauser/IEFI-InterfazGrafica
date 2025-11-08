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
public class HozVenenosa extends Arma{
    public HozVenenosa(Personaje villano) {
        super("Hoz Venenosa", 6); // Daño base mayor que la espada
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
        int incrementoAtaque = 5;
        int incrementoVeneno = 4;
        portador.incrementarAtaque(incrementoAtaque + incrementoVeneno);

        mensaje.append(this.nombre)
               .append(" incrementa ataque en ")
               .append(incrementoAtaque)
               .append(" puntos.\n")
               .append(this.nombre)
               .append(" potencia su filo venenoso aumentando ")
               .append(incrementoVeneno)
               .append(" puntos extra de ataque.\n");

        return mensaje.toString();
    }
}
