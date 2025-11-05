/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iefi.interfazgrafica.ModeloPersonajes;
import iefi.interfazgrafica.ModeloHabilidades.LeviatanDelVacio;
import iefi.interfazgrafica.ModeloArmas.HozMortifera;
import iefi.interfazgrafica.ModeloArmas.HozOxidada;
import iefi.interfazgrafica.ModeloArmas.HozVenenosa;


public class Villano extends Personaje {

    private String[] armasInvocadas = new String[3];
    private int habilidadesUsadas;
    
    public Villano() {
        super();
    }
    
    public Villano(int salud, int defensa, int ataque) {
        super(salud, defensa, ataque);
    }

    public String[] getArmasInvocadas(){
        return armasInvocadas;
    }
    public int getHabilidadesUsadas(){
        return habilidadesUsadas;
    }
    
    @Override
    public void atacar(Personaje villano, Personaje heroe) {

        invocarArma();
        
        if (this.habilidad != null) {
            if (this.habilidad.estaDisponible()) {
                this.habilidad.ejecutar(villano, heroe);
                return;
            } else {
                System.out.println("❌ La habilidad " + this.habilidad.nombre + " aún no está lista. Restan " + this.habilidad.turnosCarga + " turnos.");
                this.habilidad.pasarTurno();
            }
        }

        if (arma != null) {
            System.out.println("☠️ " + GetApodo() + " activa el efecto especial de " + arma.getNombre() + "!");
            arma.usarEfectoEspecial(this); 
        }

        int danoBase = this.ataque - heroe.defensa;
        if (danoBase < 0) {
            danoBase = 0;
        }

      
        if (Math.random() < 0.2) {
            danoBase *= 2;
            this.bendicion += 10;
            System.out.println("🔥 ¡ATAQUE CRÍTICO! El daño aumenta a " + danoBase + " puntos! Y gana 10% mas de energía del vacío.");
        }

        heroe.recibirDano(danoBase);
        if (heroe.GetSalud() < 0) {
            heroe.salud = 0;
        }
        System.out.println("⚔️ " + this.GetApodo() + " ataca a " + heroe.GetApodo() + " causando " + danoBase + " de daño.");

        
        cargarBendicion();
    }

    @Override
    public void invocarArma() {
        // 🌀 Habilidad suprema
        if (bendicion >= 100 && habilidad == null) {
            System.out.println("🌑 " + GetApodo() + " ha alcanzado el 100% de corrupción... ¡Puede invocar al Leviatán!");
            cargarHabilidad();
            habilidadesUsadas++;
            return; 
        }

        if (bendicion >= 20 && arma == null) {
            arma = new HozOxidada(this);
            System.out.println("🪓 " + GetApodo() + " invoca " + arma.getNombre() + "!");
            armasInvocadas[0] = "Hoz Oxidada";
        } 
        else if (bendicion >= 40 && arma != null && arma.getNombre().equals("Hoz Oxidada")) {
            arma = new HozVenenosa(this);
            System.out.println("🪓 " + GetApodo() + " invoca " + arma.getNombre() + "!");
            armasInvocadas[1] = "Hoz Venenosa";
        } 
        else if (bendicion >= 70 && arma != null && arma.getNombre().equals("Hoz Venenosa")) {
            arma = new HozMortifera(this);
            System.out.println("🪓 " + GetApodo() + " invoca " + arma.getNombre() + "!");
            armasInvocadas[2] = "Hoz Mortifera";
        }
    }

    @Override
    public void cargarBendicion() {
        int incremento = 10;
        bendicion += incremento;
        if (bendicion > 100) {
            bendicion = 100;
        }

        System.out.println("🩸 " + GetApodo() + " incrementa su energía del vacío a " + bendicion + "%.");
    }

    @Override
    public void cargarHabilidad() {
        if (bendicion >= 100) {
            habilidad = new LeviatanDelVacio();
            System.out.println("🌌 ¡" + GetApodo() + " ha invocado al " + habilidad.nombre + "!");
        }
    }
}
