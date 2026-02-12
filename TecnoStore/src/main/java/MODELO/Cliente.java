package MODELO;

// Esta clase representa a un cliente y hereda los atributos de Persona
public class Cliente extends Persona {
    
    // Constructor vacío que llama al constructor de la clase padre
    public Cliente() {
        super();
    }

    // Constructor con parámetros para inicializar los datos del cliente
    public Cliente(int id, String nombre, String identificacion, String email, String telefono) {
        super(id, nombre, identificacion, email, telefono);
    }

    // Formateo de los datos para mostrar en consola
    @Override
    public String toString() {
        return "CLIENTE -> ID: " + id + 
               " | Nombre: " + nombre + 
               " | Doc: " + identificacion + 
               " | Email: " + email + 
               " | Tel: " + telefono;
    }
}