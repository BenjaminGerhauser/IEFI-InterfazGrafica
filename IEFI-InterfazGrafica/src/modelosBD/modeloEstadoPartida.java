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
public class modeloEstadoPartida {
    private int id;
    private int idBatalla;
    private LocalDateTime fechaGuardado;
    private int heroeId;
    private int villanoId;
    private int vidaHeroe;
    private int vidaVillano;
    private int bendicionHeroe;
    private int bendicionVillano;
    private int turnoActual;
    private int ataqueHeroe;
    private int defensaHeroe;
    private int ataqueVillano;
    private int defensaVillano;
    private boolean finalizada;

    public modeloEstadoPartida() {}

    public modeloEstadoPartida(int idBatalla, int heroeId, int villanoId,
        int vidaHeroe, int vidaVillano,int ataqueHeroe, int defensaHeroe, int ataqueVillano, int defensaVillano,
        int bendicionHeroe, int bendicionVillano, int turnoActual) {
        this.idBatalla = idBatalla;
        this.heroeId = heroeId;
        this.villanoId = villanoId;
        this.vidaHeroe = vidaHeroe;
        this.vidaVillano = vidaVillano;
        this.bendicionHeroe = bendicionHeroe;
        this.bendicionVillano = bendicionVillano;
        this.turnoActual = turnoActual;
        this.ataqueHeroe = ataqueHeroe;
        this.defensaHeroe = defensaHeroe;
        this.ataqueVillano = ataqueVillano;
        this.defensaVillano = defensaVillano;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdBatalla() { return idBatalla; }
    public void setIdBatalla(int idBatalla) { this.idBatalla = idBatalla; }

    public LocalDateTime getFechaGuardado() { return fechaGuardado; }
    public void setFechaGuardado(LocalDateTime fechaGuardado) { this.fechaGuardado = fechaGuardado; }

    public int getHeroeId() { return heroeId; }
    public void setHeroeId(int heroeId) { this.heroeId = heroeId; }

    public int getVillanoId() { return villanoId; }
    public void setVillanoId(int villanoId) { this.villanoId = villanoId; }

    public int getVidaHeroe() { return vidaHeroe; }
    public void setVidaHeroe(int vidaHeroe) { this.vidaHeroe = vidaHeroe; }

    public int getVidaVillano() { return vidaVillano; }
    public void setVidaVillano(int vidaVillano) { this.vidaVillano = vidaVillano; }

    public int getBendicionHeroe() { return bendicionHeroe; }
    public void setBendicionHeroe(int bendicionHeroe) { this.bendicionHeroe = bendicionHeroe; }

    public int getBendicionVillano() { return bendicionVillano; }
    public void setBendicionVillano(int bendicionVillano) { this.bendicionVillano = bendicionVillano; }

    public int getTurnoActual() { return turnoActual; }
    public void setTurnoActual(int turnoActual) { this.turnoActual = turnoActual; }
    
    public int getAtaqueHeroe() { return ataqueHeroe; }
    public void setAtaqueHeroe(int ataqueHeroe) { this.ataqueHeroe = ataqueHeroe; }

    public int getDefensaHeroe() { return defensaHeroe; }
    public void setDefensaHeroe(int defensaHeroe) { this.defensaHeroe = defensaHeroe; }

    public int getAtaqueVillano() { return ataqueVillano; }
    public void setAtaqueVillano(int ataqueVillano) { this.ataqueVillano = ataqueVillano; }

    public int getDefensaVillano() { return defensaVillano; }
    public void setDefensaVillano(int defensaVillano) { this.defensaVillano = defensaVillano; }

    public boolean isFinalizada() { return finalizada; }
    public void setFinalizada(boolean finalizada) { this.finalizada = finalizada; }
}
