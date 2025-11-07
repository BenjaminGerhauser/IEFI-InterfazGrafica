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
public class HozOxidada extends Arma{
    
    public HozOxidada(Personaje villano){
        super("Hoz Oxidada", 4);
        villano.incrementarAtaque(danioExtra);
        System.out.println(this.nombre + " brinda " + danioExtra + " de puntos de ataque.");
    }
    
    @Override
    public void usarEfectoEspecial(Personaje portador){
        System.out.println(this.nombre + " no tiene efectos especiales.");
    }
}
