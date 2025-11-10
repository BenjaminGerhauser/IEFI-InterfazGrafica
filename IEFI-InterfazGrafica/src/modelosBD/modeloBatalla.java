/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosBD;

import java.time.LocalDateTime;

/**
 *
 * @author Benja
 */
public class modeloBatalla {
    private int id;
    private LocalDateTime fecha;
    private int heroeId;
    private int villanoId;
    private Integer ganadorId;
    private int turnos;

    public modeloBatalla() {}

    public modeloBatalla(int heroeId, int villanoId, Integer ganadorId, int turnos) {
        this.heroeId = heroeId;
        this.villanoId = villanoId;
        this.ganadorId = ganadorId;
        this.turnos = turnos;
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public int getHeroeId() { return heroeId; }
    public void setHeroeId(int heroeId) { this.heroeId = heroeId; }

    public int getVillanoId() { return villanoId; }
    public void setVillanoId(int villanoId) { this.villanoId = villanoId; }

    public Integer getGanadorId() { return ganadorId; }
    public void setGanadorId(Integer ganadorId) { this.ganadorId = ganadorId; }

    public int getTurnos() { return turnos; }
    public void setTurnos(int turnos) { this.turnos = turnos; }
}
