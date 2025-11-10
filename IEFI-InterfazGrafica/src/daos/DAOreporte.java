/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.*;
import conexion.conexionBD;

/**
 *
 * @author Benja
 */
public class DAOreporte {

    public String obtenerMayorAtaque() {
        String sql = "SELECT b.mayor_ataque, b.id_batalla, "
                + "p.apodo AS jugador, p.tipo "
                + "FROM batallas b "
                + "JOIN personajes p ON b.id_jugador_mayor_ataque = p.id_personaje "
                + "ORDER BY b.mayor_ataque DESC LIMIT 1";

        try (Connection conn = conexionBD.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return "Jugador: " + rs.getString("jugador")
                        + " (" + rs.getString("tipo") + ") | "
                        + "Ataque más alto: " + rs.getInt("mayor_ataque")
                        + " | Batalla #" + rs.getInt("id_batalla");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return "No se registraron ataques.";
    }

    // 2️⃣ 🔹 Batalla más larga (turnos + ganador)
    public String obtenerBatallaMasLarga() {
        String sql = "SELECT b.id_batalla, b.turnos, p.apodo AS ganador "
                + "FROM batallas b "
                + "JOIN personajes p ON b.ganador_id = p.id_personaje "
                + "ORDER BY b.turnos DESC LIMIT 1";

        try (Connection conn = conexionBD.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return "Batalla #" + rs.getInt("id_batalla")
                        + " | Turnos: " + rs.getInt("turnos")
                        + " | Ganador: " + rs.getString("ganador");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return "No hay batallas registradas.";
    }

    // 3️⃣ 🔹 Total de armas invocadas (por personaje)
    public String obtenerArmasInvocadasPorPersonaje() {
        String sql = "SELECT nombre, apodo, armas_invocadas "
                + "FROM personajes ORDER BY armas_invocadas DESC LIMIT 5";

        StringBuilder resultado = new StringBuilder();

        try (Connection conn = conexionBD.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            resultado.append("=== Total de Armas Invocadas ===\n");
            while (rs.next()) {
                resultado.append(rs.getString("nombre"))
                        .append(" (")
                        .append(rs.getString("apodo"))
                        .append("): ")
                        .append(rs.getInt("armas_invocadas"))
                        .append(" invocaciones\n");
            }

        } catch (SQLException e) {
            resultado.append("Error: ").append(e.getMessage());
        }

        return resultado.toString();
    }

    // 4️⃣ 🔹 Ataques supremos ejecutados (por personaje)
    public String obtenerSupremosEjecutados() {
        String sql = "SELECT nombre, apodo, supremos_usados "
                + "FROM personajes ORDER BY supremos_usados DESC LIMIT 5";

        StringBuilder resultado = new StringBuilder();

        try (Connection conn = conexionBD.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            resultado.append("=== Ataques Supremos Ejecutados ===\n");
            while (rs.next()) {
                resultado.append(rs.getString("nombre"))
                        .append(" (")
                        .append(rs.getString("apodo"))
                        .append("): ")
                        .append(rs.getInt("supremos_usados"))
                        .append(" supremos\n");
            }

        } catch (SQLException e) {
            resultado.append("Error: ").append(e.getMessage());
        }

        return resultado.toString();
    }

// 5️⃣ 🔹 Porcentaje de victorias por tipo
    public String obtenerPorcentajeVictoriasPorTipo() {
        String sql = "SELECT tipo, "
                + "SUM(victorias) AS total_victorias, "
                + "ROUND(SUM(victorias) * 100.0 / (SELECT SUM(victorias) FROM personajes), 2) AS porcentaje "
                + "FROM personajes GROUP BY tipo";

        StringBuilder resultado = new StringBuilder();

        try (Connection conn = conexionBD.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            resultado.append("=== Porcentaje de Victorias por Tipo ===\n");
            while (rs.next()) {
                resultado.append(rs.getString("tipo"))
                        .append(": ")
                        .append(rs.getInt("total_victorias"))
                        .append(" victorias (")
                        .append(rs.getDouble("porcentaje"))
                        .append("%)\n");
            }

        } catch (SQLException e) {
            resultado.append("Error: ").append(e.getMessage());
        }

        return resultado.toString();
    }
}
