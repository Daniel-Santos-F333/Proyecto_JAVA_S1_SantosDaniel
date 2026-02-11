package CONTROLADOR;

import MODELO.Cliente;
import java.sql.*;
import java.util.ArrayList;

public class GestionarClienteImpl implements GestionarCliente {

    Conexion c = new Conexion();

    @Override
    public void guardar(Cliente cli) {
        String sqlPersona = "INSERT INTO personas (nombre, identificacion, email, telefono) VALUES (?, ?, ?, ?)";
        String sqlCliente = "INSERT INTO clientes (persona_id) VALUES (?)";

        try (Connection con = c.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psP = con.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                psP.setString(1, cli.getNombre());
                psP.setString(2, cli.getIdentificacion());
                psP.setString(3, cli.getEmail());
                psP.setString(4, cli.getTelefono());
                psP.executeUpdate();

                ResultSet rs = psP.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);

                    try (PreparedStatement psC = con.prepareStatement(sqlCliente)) {
                        psC.setInt(1, idGenerado);
                        psC.executeUpdate();
                    }
                }
                
                con.commit(); 
                System.out.println("Cliente registrado con éxito en ambas tablas.");
            } catch (SQLException e) {
                con.rollback(); 
                System.out.println("Error al guardar: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> lista = new ArrayList<>();
        // Se hace un JOIN para traer los datos de ambas tablas
        String sql = "SELECT p.id, p.nombre, p.identificacion, p.email, p.telefono FROM personas p " +
                    "INNER JOIN clientes c ON p.id = c.persona_id";
        
        try (Connection con = c.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("email"),
                    rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override public void actualizar(Cliente c, int id) {}
    @Override public void eliminar(int id) {}
    @Override public Cliente buscar(int id) { return null; }
}