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
    
    public void crearPersonaje(String apodo, int salud, int defensa, int ataque, int bendicion) {
    // Validar apodo usando el método ya existente
    this.apodo = validarApodo(apodo);

    // Asignar el resto de los atributos
    this.salud = salud;
    this.defensa = defensa;
    this.ataque = ataque;
    this.bendicion = bendicion;
    }
    
    public void ingresarApodo(String apodo){
        this.apodo = validarApodo(apodo);    
    }
    
    public String validarApodo(String apodo) {
    if (apodo == null || apodo.trim().isEmpty()) {
        throw new IllegalArgumentException("El apodo no puede estar vacío.");
    }

    apodo = apodo.trim();

    if (!apodo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
        throw new IllegalArgumentException("El apodo solo puede contener letras y espacios.");
    }

    if (apodo.length() < 3 || apodo.length() > 10) {
        throw new IllegalArgumentException("El apodo debe tener entre 3 y 10 caracteres.");
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

