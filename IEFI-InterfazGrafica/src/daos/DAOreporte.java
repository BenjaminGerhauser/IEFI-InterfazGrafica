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
    public String obtenerMayorAtaque() throws SQLException {
        String sql = "SELECT b.mayor_ataque, b.id_batalla, " +
                     "p.apodo AS jugador, p.tipo " +
                     "FROM batallas b " +
                     "JOIN personajes p ON b.id_jugador_mayor_ataque = p.id_personaje " +
                     "ORDER BY b.mayor_ataque DESC LIMIT 1";

        try (Connection conn = conexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return "Jugador: " + rs.getString("jugador") +
                       " (" + rs.getString("tipo") + ") | " +
                       "Ataque más alto: " + rs.getInt("mayor_ataque") +
                       " | Batalla #" + rs.getInt("id_batalla");
            }
        }
        return "No se registraron ataques.";
    }

    // 2️⃣ 🔹 Batalla más larga (turnos + ganador)
    public String obtenerBatallaMasLarga() throws SQLException {
        String sql = "SELECT b.id_batalla, b.turnos, p.apodo AS ganador " +
                     "FROM batallas b " +
                     "JOIN personajes p ON b.ganador_id = p.id_personaje " +
                     "ORDER BY b.turnos DESC LIMIT 1";

        try (Connection conn = conexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return "Batalla #" + rs.getInt("id_batalla") +
                       " | Turnos: " + rs.getInt("turnos") +
                       " | Ganador: " + rs.getString("ganador");
            }
        }
        return "No hay batallas registradas.";
    }

    // 3️⃣ 🔹 Total de armas invocadas (por personaje)
    public void obtenerArmasInvocadasPorPersonaje() throws SQLException {
        String sql = "SELECT nombre, apodo, armas_invocadas " +
                     "FROM personajes ORDER BY armas_invocadas DESC";

        try (Connection conn = conexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Total de Armas Invocadas ===");
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " (" + rs.getString("apodo") + "): " +
                                   rs.getInt("armas_invocadas") + " invocaciones");
            }
        }
    }

    // 4️⃣ 🔹 Ataques supremos ejecutados (por personaje)
    public void obtenerSupremosEjecutados() throws SQLException {
        String sql = "SELECT nombre, apodo, supremos_usados " +
                     "FROM personajes ORDER BY supremos_usados DESC";

        try (Connection conn = conexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Ataques Supremos Ejecutados ===");
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " (" + rs.getString("apodo") + "): " +
                                   rs.getInt("supremos_usados") + " supremos");
            }
        }
    }

    // 5️⃣ 🔹 Porcentaje de victorias por tipo
    public void obtenerPorcentajeVictoriasPorTipo() throws SQLException {
        String sql = "SELECT tipo, " +
                     "SUM(victorias) AS total_victorias, " +
                     "ROUND(SUM(victorias) * 100.0 / (SELECT SUM(victorias) FROM personajes), 2) AS porcentaje " +
                     "FROM personajes GROUP BY tipo";

        try (Connection conn = conexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Porcentaje de Victorias por Tipo ===");
            while (rs.next()) {
                System.out.println(rs.getString("tipo") + ": " +
                                   rs.getInt("total_victorias") + " victorias (" +
                                   rs.getDouble("porcentaje") + "%)");
            }
        }
    }
}
