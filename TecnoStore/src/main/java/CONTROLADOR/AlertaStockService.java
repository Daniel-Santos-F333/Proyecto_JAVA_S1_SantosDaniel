
package CONTROLADOR;

import MODELO.CategoriaGama;
import MODELO.Celular;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AlertaStockService {
    
    private final Conexion con = new Conexion();
    
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
    
    public void generarReporteArchivo() {
        ArrayList<Celular> bajos = stockBajo();
        // Nombre de archivo corregido como pediste
        try (PrintWriter writer = new PrintWriter(new FileWriter("reporte_stock_critico.txt"))) {
            writer.println("======= REPORTE DE STOCK CRÍTICO =======");
            for (Celular c : bajos) {
                // El objeto Celular ya trae el nombre de la marca gracias al listar() corregido
                writer.println("- " + c.getMarca() + " " + c.getModelo() + " | Stock: " + c.getStock());
            }
            System.out.println("✅ Reporte guardado como 'reporte_stock_critico.txt'.");
        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}

