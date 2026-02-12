package MODELO;

// Clase base que contiene los atributos comunes para cualquier persona en el sistema
public class Persona {
    // Usamos protected para que las clases hijas (como Cliente) puedan acceder a estos datos
    protected int id;
    protected String nombre, identificacion, email, telefono;

    public Persona() {}

    // Constructor para inicializar los datos básicos
    public Persona(int id, String nombre, String identificacion, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.telefono = telefono;
    }

    // Métodos Getter y Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}