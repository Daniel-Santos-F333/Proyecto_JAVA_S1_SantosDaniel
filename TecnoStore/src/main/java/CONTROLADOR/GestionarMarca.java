package CONTROLADOR;

import java.sql.*;

public class GestionarMarca {
    Conexion con = new Conexion();

    public void registrarMarca(String nombre) {
    // Cambiamos 'nombre' por 'nombreMarca' según tu tabla en MySQL
    String sql = "INSERT INTO marcas (nombreMarca) VALUES (?)";
    try (Connection c = con.conectar(); 
         PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setString(1, nombre);
        ps.executeUpdate();
        System.out.println("✅ Marca '" + nombre + "' registrada exitosamente.");
    } catch (SQLException e) {
        System.out.println("❌ Error en tabla marcas: " + e.getMessage());
        }
    }
}