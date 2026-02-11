package MODELO;

public class Cliente extends Persona {
    
    public Cliente() {
        super();
    }

    public Cliente(int id, String nombre, String identificacion, String email, String telefono) {
        super(id, nombre, identificacion, email, telefono);
    }

    @Override
    public String toString() {
        return """
                *****************************
                ID CLIENTE:  %s
                NOMBRE:      %s
                DOCUMENTO:   %s
                EMAIL:       %s
                TELEFONO:    %s
                """.formatted(id, nombre, identificacion, email, telefono);
    }
}