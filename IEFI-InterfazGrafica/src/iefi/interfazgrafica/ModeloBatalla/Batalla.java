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


    public Object[] iniciarBatalla() {
        System.out.println("⚔️  ¡Comienza la batalla entre " + heroe.GetApodo() + " y " + villano.GetApodo() + "!");
        System.out.println("--------------------------------------------------");

        while (heroe.estaVivo() && villano.estaVivo()) {
            siguienteTurno();
        }

        String ganador = chequearVictoria();
        Object[] info = new Object[]{heroe,villano,ganador,this.turnosEmpleados};
        return info;
    }


    public void siguienteTurno() {
        Scanner sc = new Scanner(System.in); 

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


        System.out.println("🔎 Presione ENTER para continuar al siguiente turno...");
        sc.nextLine();

        turno++;
        turnosEmpleados++;
    }

    public String chequearVictoria() {
        System.out.println("🏁 La batalla ha terminado.");
        if (heroe.estaVivo() && !villano.estaVivo()) {
            System.out.println("🎉 ¡" + heroe.GetApodo() + " ha derrotado a " + villano.GetApodo() + " en " + turnosEmpleados + " turnos!");
            return heroe.GetApodo();
        } else if (villano.estaVivo() && !heroe.estaVivo()) {
            System.out.println("☠️  ¡" + villano.GetApodo() + " ha vencido a " + heroe.GetApodo() + " en " + turnosEmpleados + " turnos!");
            return villano.GetApodo();
        } else {
            System.out.println("🤝 Ambos han caído al mismo tiempo. ¡Empate!");
            return "Empate";
        }
    }

    public void mostrarEstado() {
        System.out.println("Estado actual:");
        System.out.println(heroe.GetApodo() + " → Salud: " + heroe.GetSalud());
        System.out.println(villano.GetApodo() + " → Salud: " + villano.GetSalud());
        System.out.println("--------------------------------------------------");
    }
      public Personaje getHeroe() {
        return heroe;
    }

    public Personaje getVillano() {
        return villano;
    }
}
