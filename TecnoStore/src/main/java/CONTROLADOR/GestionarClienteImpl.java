package CONTROLADOR;

import MODELO.Cliente; 
import java.sql.*;
import java.util.ArrayList;

// Clase para manejar los datos de los clientes en la base de datos
public class GestionarClienteImpl implements GestionarCliente {

    Conexion c = new Conexion();

    @Override
    public void guardar(Cliente cli) {
        // Usamos dos tablas: personas para los datos generales y clientes para el rol
        String sqlPersona = "INSERT INTO personas (nombre, identificacion, email, telefono) VALUES (?, ?, ?, ?)";
        String sqlCliente = "INSERT INTO clientes (persona_id) VALUES (?)";

        try (Connection con = c.conectar()) {
            if (con == null) return;
            
            // Iniciamos una transacción manual para asegurar que se guarde en ambas tablas o en ninguna
            con.setAutoCommit(false);

            try (PreparedStatement psP = con.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                psP.setString(1, cli.getNombre());
                psP.setString(2, cli.getIdentificacion());
                psP.setString(3, cli.getEmail());
                psP.setString(4, cli.getTelefono());
                psP.executeUpdate();

                // Obtenemos el ID que se acaba de crear en la tabla personas
                ResultSet rs = psP.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);

                    // Insertamos ese mismo ID en la tabla clientes para amarrarlos
                    try (PreparedStatement psC = con.prepareStatement(sqlCliente)) {
                        psC.setInt(1, idGenerado);
                        psC.executeUpdate();
                    }
                }
                
                // Si todo salió bien, guardamos los cambios definitivamente
                con.commit(); 
                System.out.println("✅ Cliente registrado en el sistema.");
            } catch (SQLException e) {
                // Si algo falla, deshacemos lo que se alcanzó a hacer para no dejar basura
                con.rollback(); 
                System.out.println("❌ Error al guardar los datos: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> lista = new ArrayList<>();
        // Unimos las tablas con INNER JOIN para mostrar la información completa
        String sql = "SELECT p.id, p.nombre, p.identificacion, p.email, p.telefono FROM personas p " +
                    "INNER JOIN clientes c ON p.id = c.persona_id";
        
        try (Connection con = c.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                // Llenamos la lista con los datos traídos de la consulta
                lista.add(new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("email"),
                    rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al cargar clientes: " + e.getMessage());
        }
        return lista;
    }

    @Override public void actualizar(Cliente c, int id) {}
    @Override public void eliminar(int id) {}
    @Override public Cliente buscar(int id) { return null; }
}