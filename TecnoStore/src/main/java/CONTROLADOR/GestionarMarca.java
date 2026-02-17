package CONTROLADOR;

import java.sql.*;

// Controlador para gestionar las marcas en la base de datos
public class GestionarMarca {
    Conexion con = new Conexion();

    // Método para guardar una nueva marca
    public void registrarMarca(String nombre) {
        String sql = "INSERT INTO marcas (nombreMarca) VALUES (?)";
        try (Connection c = con.conectar()) {
            // Verificamos que la conexión no sea nula antes de seguir
            if (c == null) {
                System.out.println("❌ Error: Sin conexión a la base de datos.");
                return;
            }
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.executeUpdate();
                System.out.println("✅ Marca '" + nombre + "' guardada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar marca: " + e.getMessage());
        }
    }

    // Método para borrar marcas por su ID
    public void eliminarMarca(int id) {
        String sql = "DELETE FROM marcas WHERE id = ?";
        try (Connection c = con.conectar()) {
            if (c == null) return;
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, id);
                int filas = ps.executeUpdate();
                
                // Si filas es > 0 significa que sí encontró el ID y lo borró
                if (filas > 0) {
                    System.out.println("✅ Marca eliminada del sistema.");
                } else {
                    System.out.println("⚠️ No existe ninguna marca con el ID: " + id);
                }
            }
        } catch (SQLException e) {
            // Este error salta si la marca tiene celulares vinculados (Integridad Referencial)
            System.out.println("❌ Error: No se puede borrar porque hay celulares usando esta marca.");
        }
    }

    // Método para mostrar todas las marcas guardadas
    public void listarMarcas() {
        String sql = "SELECT * FROM marcas";
        try (Connection c = con.conectar()) {
            if (c == null) return;
            try (Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                System.out.println("\n--- LISTA DE MARCAS ---");
                boolean hayDatos = false;
                while (rs.next()) {
                    hayDatos = true;
                    System.out.println("ID: " + rs.getInt("id") + " | Marca: " + rs.getString("nombreMarca"));
                }
                if (!hayDatos) System.out.println("La lista está vacía.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al cargar las marcas: " + e.getMessage());
        }
    }
}