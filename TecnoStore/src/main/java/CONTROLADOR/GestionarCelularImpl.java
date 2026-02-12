package CONTROLADOR;

import MODELO.Celular;
import MODELO.CategoriaGama;
import java.sql.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Implementación de la interfaz GestionarCelular.
 * Maneja la persistencia de datos mediante JDBC hacia la base de datos TecnoStore.
 */
public class GestionarCelularImpl implements GestionarCelular {
    
    // Objeto de conexión centralizado
    private final Conexion con = new Conexion();

    /**
     * Inserta un nuevo registro en la tabla 'celulares'.
     * @param cel Objeto de tipo Celular con los datos capturados.
     */
    public void generarReporteArchivo() {
    ArrayList<Celular> bajos = stockBajo(); // Reutilizamos tu lógica de Stream/SQL
    try (PrintWriter writer = new PrintWriter(new FileWriter("reporte_ventas.txt"))) {
        writer.println("======= REPORTE DE TECNOSTORE =======");
        writer.println("Celulares con necesidad de reposición:");
        for (Celular c : bajos) {
            writer.println("- " + c.getMarca() + " " + c.getModelo() + " | Stock: " + c.getStock());
        }
        System.out.println("✅ Archivo 'reporte_ventas.txt' generado con éxito.");
    } catch (IOException e) {
        System.out.println("❌ Error al crear el archivo: " + e.getMessage());
    }
}
    @Override
    public void registrar(Celular cel) {
        String sql = "INSERT INTO celulares (marca_id, modelo, sistema_operativo, gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection c = con.conectar(); 
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            // Mapeo de atributos a parámetros de la consulta preparada
            ps.setString(1, cel.getMarca());
            ps.setString(2, cel.getModelo());
            ps.setString(3, cel.getSistemaOperativo());
            ps.setString(4, cel.getGama().name()); // Persistencia del nombre del ENUM
            ps.setDouble(5, cel.getPrecio());
            ps.setInt(6, cel.getStock());
            
            ps.executeUpdate();
            System.out.println("✅ Transacción completada: Dispositivo registrado.");
        } catch (SQLException e) {
            System.err.println("Error de persistencia en registro: " + e.getMessage());
        }
    }

    /**
     * Recupera la totalidad de los registros almacenados en el inventario.
     * @return ArrayList de objetos Celular.
     */
    @Override
    public ArrayList<Celular> listar() {
        ArrayList<Celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";
        
        try (Connection c = con.conectar();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                // Conversión de registros relacionales a objetos Java (POJO)
                lista.add(new Celular(
                    rs.getInt("id"),
                    rs.getString("marca_id"),
                    rs.getString("modelo"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getString("sistema_operativo"),
                    CategoriaGama.valueOf(rs.getString("gama").toUpperCase())
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error en la recuperación de datos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Consulta especializada para filtrar dispositivos con existencias críticas.
     * @return Lista de equipos con stock menor a 5 unidades.
     */
    @Override
    public ArrayList<Celular> stockBajo() {
        ArrayList<Celular> lista = new ArrayList<>();
        // Filtrado a nivel de base de datos para optimizar el rendimiento
        String sql = "SELECT * FROM celulares WHERE stock < 5";
        
        try (Connection c = con.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new Celular(
                    rs.getInt("id"),
                    rs.getString("marca_id"),
                    rs.getString("modelo"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getString("sistema_operativo"),
                    CategoriaGama.valueOf(rs.getString("gama").toUpperCase())
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error en reporte de stock crítico: " + e.getMessage());
        }
        return lista;
    }

    // Métodos CRUD pendientes de implementación según requerimientos futuros
    @Override public void actualizar(Celular cel) {}
    @Override public void eliminar(int id) {}
}