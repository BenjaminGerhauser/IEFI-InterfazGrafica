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
public class EspadaSagrada extends Arma{
    public EspadaSagrada(Personaje heroe){
        super("Espada Sagrada", 7);
        heroe.incrementarAtaque(danioExtra);
        System.out.println(this.nombre + " brinda " + danioExtra + " de puntos de ataque.");
    }
    
    @Override
    public void usarEfectoEspecial(Personaje portador){
        int curacion = 5;
        portador.curarse(curacion);
        System.out.println(this.nombre + " cura " + curacion + " puntos de vida.");
    }
}
