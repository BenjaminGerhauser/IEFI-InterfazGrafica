/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iefi.interfazgrafica.ModeloBatalla;

import java.util.Scanner;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;

/**
 *
 * @author LucasV
 */
public class Batalla {

    private int turno = 1;
    private Personaje heroe;
    private Personaje villano;
    private int turnosEmpleados = 0;

    public Batalla(Personaje heroe, Personaje villano) {
        this.heroe = heroe;
        this.villano = villano;
    }

    public int getTurno(){
        return this.turno;
    }
    
    public Personaje getHeroe(){
        return this.heroe;
    }
    
    public Personaje getVillano(){
        return this.villano;
    }
    
    public Object[] iniciarBatalla() {
        System.out.println("⚔️  ¡Comienza la batalla entre " + heroe.GetApodo() + " y " + villano.GetApodo() + "!");
        System.out.println("--------------------------------------------------");

        while (heroe.estaVivo() && villano.estaVivo()) {
            siguienteTurno();
        }

        String ganador = chequearVictoria();
        Object[] info = new Object[]{heroe, villano, ganador, this.turnosEmpleados};
        return info;
    }

    public void siguienteTurno() {
        System.out.println("🔁 Turno " + turno + " ---------------------------");

        if (turno % 2 != 0) {
            // Turno del héroe
            System.out.println("🦸‍♂️ Turno de " + heroe.GetApodo());
            heroe.atacar(heroe, villano);
        } else {
            // Turno del villano
            System.out.println("😈 Turno de " + villano.GetApodo());
            villano.atacar(villano, heroe);
        }

        mostrarEstado();

        // incrementamos los contadores
        turno++;
        turnosEmpleados++;
    }

    public String chequearVictoria() {
        String mensaje = "🏁 La batalla ha terminado.\n";
        
        if (heroe.estaVivo() && !villano.estaVivo()) {
            mensaje += "🎉 ¡" + heroe.GetApodo() + " ha derrotado a " + villano.GetApodo() + " en " + turnosEmpleados + " turnos!";
        } else if (villano.estaVivo() && !heroe.estaVivo()) {
            mensaje += "☠️  ¡" + villano.GetApodo() + " ha vencido a " + heroe.GetApodo() + " en " + turnosEmpleados + " turnos!";
        } else {
            mensaje += "🤝 Ambos han caído al mismo tiempo. ¡Empate!";
        }
        
        return mensaje;
    }

    public String mostrarEstado() {
        return "Estado actual: \n" + heroe.GetApodo() + " → Salud: " + heroe.GetSalud() + "\n" + villano.GetApodo() + " → Salud: " + villano.GetSalud();
    }
}
