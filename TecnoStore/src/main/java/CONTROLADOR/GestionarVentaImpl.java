package CONTROLADOR;

import MODELO.Venta;
import MODELO.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class GestionarVentaImpl implements GestionarVenta {

    private final Conexion con = new Conexion();

    @Override
    public void registrarVenta(Venta v) {
        String sqlVenta = "INSERT INTO ventas (cliente_id, total) VALUES (?, ?)";
        String sqlDetalle = "INSERT INTO detalle_ventas (venta_id, celular_id, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        String sqlStock = "UPDATE celulares SET stock = stock - ? WHERE id = ?";

        Connection c = con.conectar();
        try {
            c.setAutoCommit(false);

            try (PreparedStatement psV = c.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psV.setInt(1, v.getClienteId());
                psV.setDouble(2, v.getTotal());
                psV.executeUpdate();

                ResultSet rs = psV.getGeneratedKeys();
                if (rs.next()) {
                    int idVenta = rs.getInt(1);
                    v.setId(idVenta);

                    try (PreparedStatement psD = c.prepareStatement(sqlDetalle);
                         PreparedStatement psS = c.prepareStatement(sqlStock)) {
                        
                        for (DetalleVenta det : v.getDetalles()) {
                            psD.setInt(1, idVenta);
                            psD.setInt(2, det.getCelularId());
                            psD.setInt(3, det.getCantidad());
                            psD.setDouble(4, det.getSubtotal());
                            psD.executeUpdate();

                            psS.setInt(1, det.getCantidad());
                            psS.setInt(2, det.getCelularId());
                            psS.executeUpdate();
                        }
                    }
                }
                c.commit();
                System.out.println("✅ Venta registrada y stock actualizado.");
            } catch (SQLException e) {
                c.rollback();
                System.out.println("❌ Error en la transacción: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Venta> listarVentas() {
        ArrayList<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas ORDER BY fecha DESC";
        try (Connection c = con.conectar();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Venta(
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getTimestamp("fecha"),
                    rs.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar ventas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void generarFacturaTXT(Venta v) {
        String nombreArchivo = "factura_" + v.getId() + ".txt";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("========== TECNOSTORE ==========");
            writer.println("Factura N°: " + v.getId());
            writer.println("Fecha: " + sdf.format(v.getFecha()));
            writer.println("Cliente ID: " + v.getClienteId());
            writer.println("--------------------------------");
            writer.println("Detalles de la compra:");
            for (DetalleVenta d : v.getDetalles()) {
                writer.println("ID Prod: " + d.getCelularId() + " | Cant: " + d.getCantidad() + " | Subt: $" + d.getSubtotal());
            }
            writer.println("--------------------------------");
            writer.println("TOTAL A PAGAR: $" + v.getTotal());
            writer.println("================================");
            System.out.println("✅ Factura generada: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("❌ Error al crear archivo: " + e.getMessage());
        }
    }
}