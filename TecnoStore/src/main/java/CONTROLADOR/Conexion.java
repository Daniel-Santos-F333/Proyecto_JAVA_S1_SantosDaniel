package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String URL = "jdbc:mysql://localhost:3306/tecnostore_db";
    private final String USER = "root";
    private final String PASS = "1234";

    public Connection conectar() {
        Connection c = null;
        try {

            c = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa a TecnoStore!"); // testeo de la conexion mi base de datos
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return c;
    }
}