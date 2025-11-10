/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;
import modelosBD.modeloBatalla;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexion.conexionBD;
import java.time.ZoneId;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 *
 * @author Benja
 */
public class DAObatalla {
    public int guardar(modeloBatalla batalla){
        String sql = "INSERT INTO batallas (heroe_id, villano_id, ganador_id, turnos) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, batalla.getHeroeId());
            stmt.setInt(2, batalla.getVillanoId()); 
            if (batalla.getGanadorId() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, batalla.getGanadorId());
            }
            stmt.setInt(4, batalla.getTurnos());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            int idGenerado = rs.getInt(1);
            batalla.setId(idGenerado);
            return idGenerado;
        }
        }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }
    
    public boolean actualizarResultado(modeloBatalla batalla){
    String sql = "UPDATE batallas SET ganador_id = ?, turnos = ? WHERE id_batalla = ?";

    try (Connection conn = conexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, batalla.getGanadorId());
        stmt.setInt(2, batalla.getTurnos());
        stmt.setInt(3, batalla.getId());
        stmt.executeUpdate();
        
        int filas = stmt.executeUpdate();

        // Devuelvo el id de la batalla actualizada
        if (filas > 0) {
            return true;
        }
    }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
    }
    return false;
}

    public List<modeloBatalla> listarHistorial(){
        List<modeloBatalla> lista = new ArrayList<>();
        String sql = "SELECT * FROM batallas ORDER BY fecha DESC LIMIT 5";
        try (Connection conn = conexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                modeloBatalla batalla = new modeloBatalla();
                batalla.setId(rs.getInt("id_batalla"));
                batalla.setHeroeId(rs.getInt("heroe_id"));
                batalla.setVillanoId(rs.getInt("villano_id"));
                batalla.setGanadorId(rs.getInt("ganador_id"));
                batalla.setTurnos(rs.getInt("turnos"));
                lista.add(batalla);
            }
        }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public int obtenerMayorAtaqueActual(int idBatalla){
    String sql = "SELECT mayor_ataque FROM batallas WHERE id_batalla = ?";

    try (Connection conn = conexionBD.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idBatalla);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("mayor_ataque");
        }
    }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    return 0;
}

    public boolean actualizarMayorAtaque(int idBatalla, int nuevoAtaque, int idJugador){
    String sql = "UPDATE batallas SET mayor_ataque = ?, id_jugador_mayor_ataque = ? WHERE id_batalla = ?";

    try (Connection conn =conexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, nuevoAtaque);
        stmt.setInt(2, idJugador);
        stmt.setInt(3, idBatalla);

        return stmt.executeUpdate() > 0;
    }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
    }
    return false;
}
    
}
