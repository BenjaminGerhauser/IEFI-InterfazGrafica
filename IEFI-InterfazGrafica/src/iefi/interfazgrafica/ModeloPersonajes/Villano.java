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

    public String[] getArmasInvocadas() {
        return armasInvocadas;
    }

    public int getHabilidadesUsadas() {
        return habilidadesUsadas;
    }

    @Override
    public String atacar(Personaje villano, Personaje heroe) {
        String mensaje = "";
        mensaje += invocarArma() + "\n";

        if (this.habilidad != null && this.habilidadPermitida) {
            if (this.habilidad.estaDisponible()) {
                mensaje += this.habilidad.ejecutar(villano, heroe) + "\n";
                return mensaje;
            } else {
                mensaje += ("❌ La habilidad " + this.habilidad.nombre + " aún no está lista. Restan " + this.habilidad.turnosCarga + " turnos.\n");
                this.habilidad.pasarTurno();
            }
        }

        if (arma != null) {
            mensaje += ("☠️ " + GetApodo() + " activa el efecto especial de " + arma.getNombre() + "!\n");
            mensaje += arma.usarEfectoEspecial(this) + "\n";
        }

        int danoBase = this.ataque - heroe.defensa;
        if (danoBase < 0) {
            danoBase = 0;
        }

        if (Math.random() < 0.2) {
            danoBase *= 2;
            this.bendicion += 10;
            mensaje += ("🔥 ¡ATAQUE CRÍTICO! El daño aumenta a " + danoBase + " puntos! Y gana 10% mas de energía del vacío.\n");
        }

        heroe.recibirDano(danoBase);
        if (heroe.GetSalud() < 0) {
            heroe.salud = 0;
        }
        mensaje += ("⚔️ " + this.GetApodo() + " ataca a " + heroe.GetApodo() + " causando " + danoBase + " de daño.\n");

        mensaje += cargarBendicion() + "\n";
        return mensaje;
    }

    @Override
    public String invocarArma() {
        String mensajeArma = "";
        
        // 🌀 Habilidad suprema
        if (bendicion >= 100 && habilidad == null) {
            mensajeArma += ("🌑 " + GetApodo() + " ha alcanzado el 100% de corrupción... ¡Puede invocar al Leviatán!\n");
            mensajeArma += cargarHabilidad() + "\n";
            habilidadesUsadas++;
            return "";
        }

        if (bendicion >= 20 && arma == null) {
            arma = new HozOxidada(this);
            mensajeArma += ("🪓 " + GetApodo() + " invoca " + arma.getNombre() + "!\n");
            armasInvocadas[0] = "Hoz Oxidada";
            this.cantArmasInvocadas ++;
        } else if (bendicion >= 40 && arma != null && arma.getNombre().equals("Hoz Oxidada")) {
            arma = new HozVenenosa(this);
            mensajeArma += ("🪓 " + GetApodo() + " invoca " + arma.getNombre() + "!\n");
            armasInvocadas[1] = "Hoz Venenosa";
            this.cantArmasInvocadas ++;
        } else if (bendicion >= 70 && arma != null && arma.getNombre().equals("Hoz Venenosa")) {
            arma = new HozMortifera(this);
            mensajeArma += ("🪓 " + GetApodo() + " invoca " + arma.getNombre() + "!\n");
            armasInvocadas[2] = "Hoz Mortifera";
            this.cantArmasInvocadas ++;
        }
        
        return mensajeArma;
    }

    @Override
    public String cargarBendicion() {
        
        String mensajeBendicion = "";
        
        int incremento = 10;
        bendicion += incremento;
        if (bendicion > 100) {
            bendicion = 100;
        }

        mensajeBendicion += ("🩸 " + GetApodo() + " incrementa su energía del vacío a " + bendicion + "%.\n");
        
        return mensajeBendicion;
    }

    @Override
    public String cargarHabilidad() {
        String mensajeHabilidad = "";
        if (bendicion >= 100) {
            habilidad = new LeviatanDelVacio();
            mensajeHabilidad += ("🌌 ¡" + GetApodo() + " ha invocado al " + habilidad.nombre + "!\n");
            this.cantHabilidadesInvocadas ++;
        }
        
        return mensajeHabilidad;
    }
}
