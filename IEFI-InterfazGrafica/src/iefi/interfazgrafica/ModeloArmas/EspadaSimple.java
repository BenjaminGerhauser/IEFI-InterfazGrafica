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
public class EspadaSimple extends Arma {
    
    public EspadaSimple(Personaje heroe){
        super("Espada Simple", 5);
        heroe.incrementarAtaque(danioExtra);
        System.out.println(this.nombre + " brinda " + danioExtra + " de puntos de ataque.");
    }
    
    @Override
    public void usarEfectoEspecial(Personaje portador){
        System.out.println(this.nombre + " no tiene efectos especiales.");
    }
}
