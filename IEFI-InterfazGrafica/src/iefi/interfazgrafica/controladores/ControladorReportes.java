/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iefi.interfazgrafica.controladores;

import daos.DAOpersonaje;
import java.util.List;
import modelosBD.modeloBatalla;
import modelosBD.modeloPersonaje;

/**
 *
 * @author Juani
 */
public class ControladorReportes {

    public String MayorAtaque;
    public String ArmasInvocadasPorPersonaje;
    public String PorcentajeVictoriasPorTipo;
    public String SupremosEjecutados;
    public String BatallaMasLarga;

    public ControladorReportes() {
        daos.DAOreporte daosReporte = new daos.DAOreporte();
        MayorAtaque = daosReporte.obtenerMayorAtaque();
        ArmasInvocadasPorPersonaje = daosReporte.obtenerArmasInvocadasPorPersonaje();
        PorcentajeVictoriasPorTipo = daosReporte.obtenerPorcentajeVictoriasPorTipo();
        SupremosEjecutados = daosReporte.obtenerSupremosEjecutados();
        BatallaMasLarga = daosReporte.obtenerBatallaMasLarga();
    }

    public String obtenerRankingFormateado() {
        daos.DAOpersonaje daos= new daos.DAOpersonaje();
        List<modeloPersonaje> ranking = daos.listarRanking();
        StringBuilder resultado = new StringBuilder();

        resultado.append("=== 🏆 Ranking de Personajes ===\n");
        resultado.append(String.format("%-15s %-15s %-10s %-10s %-10s %-10s\n",
                "Nombre", "Apodo", "Tipo", "Vida", "Victorias", "Supremos"));

        resultado.append("------------------------------------------------------------\n");

        for (modeloPersonaje p : ranking) {
            resultado.append(String.format("%-15s %-15s %-10s %-10d %-10d %-10d\n",
                    p.getNombre(),
                    p.getApodo(),
                    p.getTipo(),
                    p.getVidaFinal(),
                    p.getVictorias(),
                    p.getSupremosUsados(),"\n"));
        }

        return resultado.toString();
    }
    public String obtenerHistorial() {
    daos.DAObatalla dao = new daos.DAObatalla();
    List<modeloBatalla> lista = dao.listarHistorial();
    daos.DAOpersonaje daoPersonajes =  new DAOpersonaje();
    if (lista == null || lista.isEmpty()) {
        return "No hay batallas registradas.";
    }

    StringBuilder sb = new StringBuilder();
    sb.append("=== ÚLTIMAS 5 BATALLAS ===\n");

    for (modeloBatalla b : lista) {
        // Si los nombres no están cargados, mostrar por ID
        modelosBD.modeloPersonaje modeloPersonaje = new modelosBD.modeloPersonaje();
        modeloPersonaje apodoVillano = daoPersonajes.buscarPorId(b.getVillanoId());
        modeloPersonaje apodoHeroe = daoPersonajes.buscarPorId(b.getHeroeId());
        

        
        sb.append("Batalla #").append(b.getId())
          .append(" - Héroe: ").append(apodoHeroe.getApodo())
          .append(" | Villano: ").append(apodoVillano.getApodo());

        // Ganador puede ser null
        if (b.getGanadorId() != 0) {
            modeloPersonaje apodoGanador = daoPersonajes.buscarPorId(b.getGanadorId());
            sb.append(" | Ganador: ").append(apodoGanador.getApodo());
        } else {
            sb.append(" | Ganador: -");
        }

        sb.append(" | Turnos: ").append(b.getTurnos()).append("\n");
    }

    return sb.toString();
}
}
