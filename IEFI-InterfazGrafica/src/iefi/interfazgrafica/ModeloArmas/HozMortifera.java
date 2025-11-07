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
        System.out.println(this.nombre + " brinda " + danioExtra + " de puntos de ataque.");
    }
    @Override
    public void usarEfectoEspecial(Personaje portador) {
        int incrementoAtaque = 8;   // aumento principal
        int incrementoVeneno = 6;   // aumento adicional por veneno
        int curacion = 8;          // curación al usarla

        portador.incrementarAtaque(incrementoAtaque + incrementoVeneno);
        portador.curarse(curacion);

        System.out.println(this.nombre + " incrementa ataque en " + incrementoAtaque + " puntos.");
        System.out.println(this.nombre + " añade un poder venenoso que aumenta " + incrementoVeneno + " puntos de ataque adicionales.");
        System.out.println(this.nombre + " cura al portador en " + curacion + " puntos de vida.");
    }
}
