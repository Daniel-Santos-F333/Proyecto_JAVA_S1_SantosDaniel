package CONTROLADOR;

import java.sql.*;
import java.util.ArrayList;

public class GestionarMarca {
    Conexion con = new Conexion();

    public void registrarMarca(String nombre) {
        String sql = "INSERT INTO marcas (nombreMarca) VALUES (?)";
        try (Connection c = con.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
            System.out.println("✅ Marca registrada.");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void eliminarMarca(int id) {
        String sql = "DELETE FROM marcas WHERE idMarca = ?";
        try (Connection c = con.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✅ Marca eliminada.");
            else System.out.println("⚠️ No se encontró la marca con ID: " + id);
        } catch (SQLException e) {
            System.out.println("❌ Error: No puedes eliminar marcas con celulares asociados.");
        }
    }

    public void listarMarcas() {
        String sql = "SELECT * FROM marcas";
        try (Connection c = con.conectar(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- LISTADO DE MARCAS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idMarca") + " | Nombre: " + rs.getString("nombreMarca"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar.");
        }
    }
}