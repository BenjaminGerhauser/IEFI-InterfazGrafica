/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iefi.interfazgrafica.ModeloPersonajes;
import iefi.interfazgrafica.ModeloHabilidades.CastigoBendito;
import iefi.interfazgrafica.ModeloArmas.EspadaCelestial;
import iefi.interfazgrafica.ModeloArmas.EspadaSagrada;
import iefi.interfazgrafica.ModeloArmas.EspadaSimple;


public class Heroe extends Personaje {
    
    private String[] armasInvocadas = new String[3];
    private int habilidadesUsadas;
    
    public Heroe() {
        super();
    }

    public Heroe(int salud, int defensa, int ataque) {
        super(salud, defensa, ataque);
    }

    public String[] getArmasInvocadas(){
        return armasInvocadas;
    }
    public int getHabilidadesUsadas(){
        return habilidadesUsadas;
    }
    
    @Override
    public String atacar(Personaje heroe, Personaje villano) {
        String mensaje = "";
        mensaje += invocarArma() + "\n";
        
        if (this.habilidad != null && this.habilidadPermitida) {
            if(this.habilidad.estaDisponible()){
                mensaje += this.habilidad.ejecutar(heroe, villano) + "\n";
                return mensaje;
            }else{
                mensaje += ("❌ La habilidad " + this.habilidad.nombre + " aún no está lista.\n");
                this.habilidad.pasarTurno();
            }
        }

        if (arma != null) {
            mensaje +=("✨ " + GetApodo() + " utiliza el efecto especial de " + arma.getNombre() + "!\n");
            mensaje += arma.usarEfectoEspecial(this) + "\n";
        }

        int danoBase = this.ataque - villano.defensa;
        if (danoBase < 0) {
            danoBase = 0;
        }

        if (Math.random() < 0.2) {
            danoBase *= 2; 
            this.bendicion += 10;
            mensaje += ("🔥 ¡ATAQUE CRÍTICO! El daño aumenta a " + danoBase + " puntos! Y gana 10% mas de bendicion celestial.\n");
        }

        villano.recibirDano(danoBase);
        if (villano.GetSalud() < 0) {
            villano.salud = 0;
        }
        mensaje +=("🗡️ " + this.GetApodo() + " ataca a " + villano.GetApodo() + " causando " + danoBase + " de daño.\n");
        mensaje+= cargarBendicion() + "\n";
        
        return mensaje;

    }

    @Override
    public String invocarArma() {
        String mensajeArma = "";
        if (bendicion >= 100 && habilidad == null) {
            mensajeArma += ("⚡ " + GetApodo() + " ha alcanzado el 100% de bendición. ¡Puede usar su habilidad suprema!\n");
            mensajeArma += cargarHabilidad() + "\n";
            habilidadesUsadas++;
            return ""; // 👈 no seguimos con armas si ya tiene habilidad
        }

        // 🗡️ Invocar Espada Simple (solo si no hay arma)
        if (bendicion >= 20 && arma == null) {
            arma = new EspadaSimple(this);
            mensajeArma += ("🔮 " + GetApodo() + " invoca " + arma.getNombre() + "!\n");
            armasInvocadas[0] = "Espada Simple";
            this.cantArmasInvocadas ++;
        } // 🗡️ Evoluciona a Espada Sagrada (solo si la actual es Simple)
        else if (bendicion >= 40 && arma != null && arma.getNombre().equals("Espada Simple")) {
            arma = new EspadaSagrada(this);
            mensajeArma += ("🔮 " + GetApodo() + " invoca " + arma.getNombre() + "!\n");
            armasInvocadas[2] = "Espada Sagrada";
            this.cantArmasInvocadas ++;
        } // 🗡️ Evoluciona a Espada Celestial (solo si la actual es Sagrada)
        else if (bendicion >= 70 && arma != null && arma.getNombre().equals("Espada Sagrada")) {
            arma = new EspadaCelestial(this);
            mensajeArma += ("🔮 " + GetApodo() + " invoca " + arma.getNombre() + "!\n");
            armasInvocadas[2] = "Espada Celestial";
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

        mensajeBendicion += ("✨ " + GetApodo() + " aumenta su bendición a " + bendicion + "%.\n");
        
        return mensajeBendicion;
    }

    @Override
    public String cargarHabilidad() {
        
        String mensajeHabilidad = "";
        
        if (bendicion >= 100) {
            habilidad = new CastigoBendito();
            mensajeHabilidad += ("🌟 ¡" + GetApodo() + " desbloqueó su habilidad suprema: " + habilidad.nombre + "!\n");
            this.cantHabilidadesInvocadas ++;
        }
        
        return mensajeHabilidad;
    }
}