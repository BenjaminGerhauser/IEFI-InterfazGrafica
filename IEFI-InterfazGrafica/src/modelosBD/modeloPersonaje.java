/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosBD;

/**
 *
 * @author Benja
 */
public class modeloPersonaje {
    private int id;
    private String nombre;
    private String apodo;
    private String tipo; // "Héroe" o "Villano"
    private int vidaFinal;
    private int victorias;
    private int supremosUsados;
    private int armasInvocadas;
    private int ataque;
    private int defensa;
    public modeloPersonaje() {}

    public modeloPersonaje(String nombre, String apodo, String tipo, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.tipo = tipo;
        this.vidaFinal = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApodo() { return apodo; }
    public void setApodo(String apodo) { this.apodo = apodo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getVidaFinal() { return vidaFinal; }
    public void setVidaFinal(int vidaFinal) { this.vidaFinal = vidaFinal; }

    public int getVictorias() { return victorias; }
    public void setVictorias(int victorias) { this.victorias = victorias; }

    public int getSupremosUsados() { return supremosUsados; }
    public void setSupremosUsados(int supremosUsados) { this.supremosUsados = supremosUsados; }

    public int getArmasInvocadas() { return armasInvocadas; }
    public void setArmasInvocadas(int armasInvocadas) { this.armasInvocadas = armasInvocadas; }
    
    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
}
