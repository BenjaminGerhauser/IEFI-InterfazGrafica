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
        System.out.println(this.nombre + " brinda " + danioExtra + " de puntos de ataque.");
    }

    @Override
    public void usarEfectoEspecial(Personaje portador) {
        int incrementoAtaque = 5;  // aumento por el filo
        int incrementoVeneno = 4;  // aumento adicional “por veneno”

        portador.incrementarAtaque(incrementoAtaque + incrementoVeneno);

        System.out.println(this.nombre + " incrementa ataque en " + incrementoAtaque + " puntos.");
        System.out.println(this.nombre + " potencia su filo venenoso aumentando " + incrementoVeneno + " puntos extra de ataque.");
    }
}
