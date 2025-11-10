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
    public boolean habilidadPermitida = true;
    public int cantArmasInvocadas = 0;    
    public int cantHabilidadesInvocadas = 0;

    
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
    
    public int GetDefensa(){
        return this.defensa;
    }
    
    public int GetAtaque(){
        return this.ataque;
    }
    
    public int GetBendicion(){
        return this.bendicion;
    }
    
    public void SetDefensa(int defensa){
        this.defensa = defensa;
    }
    
    public void SetAtaque(int ataque){
        this.ataque = ataque;
    }
    
    public void SetBendicion(int bendicion){
        this.bendicion = bendicion;
    }
    
    public void SetSalud(int salud){
        this.salud = salud;
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
        this.defensa += incremento;
    }
    
    public void incrementarAtaque(int incremento){
        this.ataque += incremento;
    }
    
    public String atacar(Personaje atacante, Personaje defensor){return "";}
    
    public String invocarArma(){return "";}
    
    public String cargarHabilidad(){return "";}
    
    public String cargarBendicion(){return "";}
    
}

