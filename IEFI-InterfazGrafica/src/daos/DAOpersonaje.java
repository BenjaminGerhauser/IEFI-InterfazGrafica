/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;
import modelosBD.modeloPersonaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexion.conexionBD;
/**
 *
 * @author Benja
 */
public class DAOpersonaje {
    public int guardar(modeloPersonaje personaje){
        String sql = "INSERT INTO personajes (nombre, apodo, tipo, vida_final, ataque, defensa, victorias, supremos_usados, armas_invocadas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, personaje.getNombre());
            stmt.setString(2, personaje.getApodo());
            stmt.setString(3, personaje.getTipo());
            stmt.setInt(4, personaje.getVidaFinal());
            stmt.setInt(5, personaje.getAtaque());
            stmt.setInt(6, personaje.getDefensa());
            stmt.setInt(7, personaje.getVictorias());
            stmt.setInt(8, personaje.getSupremosUsados());
            stmt.setInt(9, personaje.getArmasInvocadas());
            stmt.executeUpdate();
            
            //Obtener id del personajes guardado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                personaje.setId(idGenerado); 
                return idGenerado;
            }
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    public List<modeloPersonaje> listarRanking(){
        List<modeloPersonaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM personajes ORDER BY victorias DESC, supremos_usados DESC";
        try (Connection conn = conexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                modeloPersonaje personaje = new modeloPersonaje();
                personaje.setId(rs.getInt("id_personaje"));
                personaje.setNombre(rs.getString("nombre"));
                personaje.setApodo(rs.getString("apodo"));
                personaje.setTipo(rs.getString("tipo"));
                personaje.setVictorias(rs.getInt("victorias"));
                personaje.setVidaFinal(rs.getInt("vida_final"));
                personaje.setSupremosUsados(rs.getInt("supremos_usados"));
                personaje.setArmasInvocadas(rs.getInt("armas_invocadas"));
                lista.add(personaje);
            }
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarEstadisticas(modeloPersonaje personaje){
        String sql = "UPDATE personajes SET vida_final=?, ataque=?, defensa=?, victorias=?, supremos_usados=?, armas_invocadas=? WHERE id_personaje=?";
        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personaje.getVidaFinal());
            stmt.setInt(2, personaje.getAtaque());
            stmt.setInt(3, personaje.getDefensa());
            stmt.setInt(4, personaje.getVictorias());
            stmt.setInt(5, personaje.getSupremosUsados());
            stmt.setInt(6, personaje.getArmasInvocadas());
            stmt.setInt(7, personaje.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public modeloPersonaje buscarPorId(int idPersonaje){
    String sql = "SELECT * FROM personajes WHERE id_personaje = ?";

    try (Connection conn = conexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idPersonaje);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            modeloPersonaje personaje = new modeloPersonaje();
            personaje.setId(rs.getInt("id_personaje"));
            personaje.setNombre(rs.getString("nombre"));
            personaje.setApodo(rs.getString("apodo"));
            personaje.setTipo(rs.getString("tipo"));
            personaje.setVidaFinal(rs.getInt("vida_final"));
            personaje.setVictorias(rs.getInt("victorias"));
            personaje.setSupremosUsados(rs.getInt("supremos_usados"));
            personaje.setArmasInvocadas(rs.getInt("armas_invocadas"));
            personaje.setAtaque(rs.getInt("ataque"));
            personaje.setDefensa(rs.getInt("defensa"));
            return personaje;
        }
    } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    return null; // Si no se encuentra el personaje
}
}
