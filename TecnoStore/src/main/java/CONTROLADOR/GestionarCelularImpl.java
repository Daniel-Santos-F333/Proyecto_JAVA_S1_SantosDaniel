package CONTROLADOR;

import MODELO.Celular;
import MODELO.CategoriaGama;
import java.sql.*;
import java.util.ArrayList;

public class GestionarCelularImpl implements GestionarCelular {
    Conexion con = new Conexion();

    @Override
    public void registrar(Celular cel) {
        String sql = "INSERT INTO celulares (marca_id, modelo, sistema_operativo, gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection c = con.conectar(); 
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setString(1, cel.getMarca());
            ps.setString(2, cel.getModelo());
            ps.setString(3, cel.getSistemaOperativo());
            ps.setString(4, cel.getGama().name());
            ps.setDouble(5, cel.getPrecio());
            ps.setInt(6, cel.getStock());
            
            ps.executeUpdate();
            System.out.println("✅ Celular registrado en inventario.");
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar celular: " + e.getMessage());
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
                // Sincronizado con el constructor: id, marca, modelo, precio, stock, SO, gama
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
            System.out.println("❌ Error al listar inventario: " + e.getMessage());
        }
        return lista;
    }

    @Override public void actualizar(Celular cel) {}
    @Override public void eliminar(int id) {}
    @Override public ArrayList<Celular> stockBajo() { return new ArrayList<>(); }
}