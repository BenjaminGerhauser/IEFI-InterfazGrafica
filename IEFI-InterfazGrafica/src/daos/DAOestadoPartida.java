/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import modelosBD.modeloEstadoPartida;
import java.sql.*;
import conexion.conexionBD;
/**
 *
 * @author Benja
 */
public class DAOestadoPartida {
    public int guardar(modeloEstadoPartida estado){
        String sql = "INSERT INTO estado_partida (" +
                 "id_batalla, heroe_id, villano_id, " +
                 "vida_heroe, vida_villano, " +
                 "bendicion_heroe, bendicion_villano, " +
                 "ataque_heroe, defensa_heroe, ataque_villano, defensa_villano, " +
                 "turno_actual, finalizada" +
                 ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, estado.getIdBatalla());
            stmt.setInt(2, estado.getHeroeId());
            stmt.setInt(3, estado.getVillanoId());
            stmt.setInt(4, estado.getVidaHeroe());
            stmt.setInt(5, estado.getVidaVillano());
            stmt.setInt(6, estado.getBendicionHeroe());
            stmt.setInt(7, estado.getBendicionVillano());
            stmt.setInt(8, estado.getAtaqueHeroe());
            stmt.setInt(9, estado.getDefensaHeroe());
            stmt.setInt(10, estado.getAtaqueVillano());
            stmt.setInt(11, estado.getDefensaVillano());
            stmt.setInt(12, estado.getTurnoActual());
            stmt.setBoolean(13, estado.isFinalizada());


            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            int idGenerado = rs.getInt(1);
            estado.setId(idGenerado);
            return idGenerado;
        }
        }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    public boolean actualizar(modeloEstadoPartida estado) {
    String sql = "UPDATE estado_partida SET " +
                 "vida_heroe = ?, vida_villano = ?, " +
                 "bendicion_heroe = ?, bendicion_villano = ?, " +
                 "ataque_heroe = ?, defensa_heroe = ?, " +
                 "ataque_villano = ?, defensa_villano = ?, " +
                 "turno_actual = ?, finalizada = ?, " +
                 "fecha_guardado = NOW() " +
                 "WHERE id_batalla = ?";

    try (Connection conn = conexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, estado.getVidaHeroe());
        stmt.setInt(2, estado.getVidaVillano());
        stmt.setInt(3, estado.getBendicionHeroe());
        stmt.setInt(4, estado.getBendicionVillano());
        stmt.setInt(5, estado.getAtaqueHeroe());
        stmt.setInt(6, estado.getDefensaHeroe());
        stmt.setInt(7, estado.getAtaqueVillano());
        stmt.setInt(8, estado.getDefensaVillano());
        stmt.setInt(9, estado.getTurnoActual());
        stmt.setBoolean(10, estado.isFinalizada());
        stmt.setInt(11, estado.getIdBatalla());

        stmt.executeUpdate();
        return true;
    }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
    }
    return false;
}
    public modeloEstadoPartida cargarUltima(){
        String sql = "SELECT * FROM estado_partida ORDER BY fecha_guardado DESC LIMIT 1";

        try (Connection conn = conexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                modeloEstadoPartida estado = new modeloEstadoPartida();
                estado.setId(rs.getInt("id_estado"));
                estado.setIdBatalla(rs.getInt("id_batalla"));
                estado.setHeroeId(rs.getInt("heroe_id"));
                estado.setVillanoId(rs.getInt("villano_id"));
                estado.setVidaHeroe(rs.getInt("vida_heroe"));
                estado.setVidaVillano(rs.getInt("vida_villano"));
                estado.setBendicionHeroe(rs.getInt("bendicion_heroe"));
                estado.setBendicionVillano(rs.getInt("bendicion_villano"));
                estado.setTurnoActual(rs.getInt("turno_actual"));
                estado.setFechaGuardado(rs.getTimestamp("fecha_guardado").toLocalDateTime());
                return estado;
            }
        }
        catch (SQLException e) {
            System.err.println("Error al insertar profesor: " + e.getMessage());
        }
        return null;
    }
    
     public modeloEstadoPartida cargarPorBatalla(int idBatalla){
        String sql = "SELECT * FROM estado_partida WHERE id_batalla = ? ORDER BY fecha_guardado DESC LIMIT 1";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idBatalla);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                modeloEstadoPartida estado = new modeloEstadoPartida();
                estado.setId(rs.getInt("id_estado"));
                estado.setIdBatalla(rs.getInt("id_batalla"));
                estado.setHeroeId(rs.getInt("heroe_id"));
                estado.setVillanoId(rs.getInt("villano_id"));
                estado.setVidaHeroe(rs.getInt("vida_heroe"));
                estado.setVidaVillano(rs.getInt("vida_villano"));
                estado.setBendicionHeroe(rs.getInt("bendicion_heroe"));
                estado.setBendicionVillano(rs.getInt("bendicion_villano"));
                estado.setTurnoActual(rs.getInt("turno_actual"));
                estado.setFechaGuardado(rs.getTimestamp("fecha_guardado").toLocalDateTime());
                return estado;
            }
        }catch (SQLException e) {
            System.err.println("Error al insertar profesor: " + e.getMessage());
        }
        return null;
    }
}
