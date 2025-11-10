/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iefi.interfazgrafica.controladores;

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
                    p.getSupremosUsados()));
        }

        return resultado.toString();
    }
  public List<modeloBatalla> obtenerHistorial() {
        daos.DAObatalla daos= new daos.DAObatalla();
        List<modeloBatalla> lista = daos.listarHistorial();
        return lista;
    }
}
