package CONTROLADOR;

import MODELO.Celular;
import MODELO.CategoriaGama;
import java.sql.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

// Clase que conecta la lógica de los celulares con la base de datos
public class GestionarCelularImpl implements GestionarCelular {
    
    private final Conexion con = new Conexion();

    // Crea el archivo de texto para el reporte de stock
    public void generarReporteArchivo() {
        ArrayList<Celular> bajos = stockBajo(); 
        try (PrintWriter writer = new PrintWriter(new FileWriter("reporte_ventas.txt"))) {
            writer.println("======= REPORTE DE TECNOSTORE =======");
            writer.println("Celulares con poco inventario:");
            for (Celular c : bajos) {
                writer.println("- " + c.getMarca() + " " + c.getModelo() + " | Stock actual: " + c.getStock());
            }
            System.out.println("✅ Reporte guardado en 'reporte_ventas.txt'.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo: " + e.getMessage());
        }
    }

    @Override
    public void registrar(Celular cel) {
        String sql = "INSERT INTO celulares (marca_id, modelo, sistema_operativo, gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection c = con.conectar(); 
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            // Pasamos los datos del objeto a la consulta SQL
            ps.setString(1, cel.getMarca());
            ps.setString(2, cel.getModelo());
            ps.setString(3, cel.getSistemaOperativo());
            ps.setString(4, cel.getGama().name()); 
            ps.setDouble(5, cel.getPrecio());
            ps.setInt(6, cel.getStock());
            
            ps.executeUpdate();
            System.out.println("✅ Celular guardado en la base de datos.");
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Celular> listar() {
        ArrayList<Celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";
        
        try (Connection c = con.conectar();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                // Creamos los objetos Celular con los datos que trae la tabla
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
            System.err.println("❌ Error al cargar la lista: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Celular> stockBajo() {
        ArrayList<Celular> lista = new ArrayList<>();
        // Solo traemos los que tienen menos de 5 unidades
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
            System.err.println("❌ Error en reporte de stock: " + e.getMessage());
        }
        return lista;
    }

    @Override public void actualizar(Celular cel) {}
    @Override public void eliminar(int id) {}
}