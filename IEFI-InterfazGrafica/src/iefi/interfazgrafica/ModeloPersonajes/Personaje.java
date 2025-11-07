/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iefi.interfazgrafica.ModeloPersonajes;

import java.util.Scanner;
import iefi.interfazgrafica.ModeloHabilidades.Habilidad;
import iefi.interfazgrafica.ModeloArmas.Arma;
/**
 *
 * @author agusn
 */
public abstract class Personaje {

    public String apodo;
    public int salud;
    public int defensa;
    public int ataque;
    public int bendicion = 0;
    public Arma arma;
    public Habilidad habilidad;
    
    public Personaje(){}
    
    public Personaje(int salud, int defensa, int ataque){
        this.salud = salud;
        this.defensa = defensa;
        this.ataque = ataque;
    }
    
    public void ingresarApodo(){
        this.apodo = validarApodo();
    }
    
    public String validarApodo() {
        Scanner sc = new Scanner(System.in);
        String apodo;
        while (true) {
            System.out.print("Ingrese un apodo (3 a 10 caracteres, solo letras y espacios): ");
            apodo = sc.nextLine().trim();

            // Solo letras y espacios
            if (!apodo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("❌ El apodo solo puede contener letras y espacios.");
                continue;
            }

            // Longitud 3–10
            if (apodo.length() < 3 || apodo.length() > 10) {
                System.out.println("❌ El apodo debe tener entre 3 y 10 caracteres.");
                continue;
            }

            // Si pasó todas las validaciones, salimos del bucle
            break;
        }

        return apodo;
    }
    
    public String GetApodo(){
        return this.apodo;
    }
    
    public void curarse(int curacion){
        this.salud += curacion;
    }
    
    public void recibirDano(int dano){
        this.salud -= dano;
    }
    
    public int GetSalud(){
        return this.salud;
    }
    
    public boolean estaVivo(){
        return this.salud > 0;
    }
    
    public void incrementarDefensa(int incremento){
        this.defensa += defensa;
    }
    
    public void incrementarAtaque(int incremento){
        this.ataque += incremento;
    }
    
    public void atacar(Personaje atacante, Personaje defensor){}
    
    public void invocarArma(){}
    
    public void cargarHabilidad(){}
    
    public void cargarBendicion(){}
    
}

