package VISTA;

import CONTROLADOR.GestionarClienteImpl;
import MODELO.Cliente;
import java.util.Scanner;

// Interfaz de consola para la gestión de clientes
public class menuCliente {
    
    private final GestionarClienteImpl gc = new GestionarClienteImpl();
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                               \n======= GESTIÓN DE CLIENTES =======
                               1. Registrar nuevo cliente
                               2. Listar todos los clientes
                               3. Volver al menú anterior
                               """);
            System.out.print("Elija una opción: ");
            
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                op = 0;
                continue;
            }

            switch (op) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (op != 3);
    }

    // Método para capturar los datos del cliente desde consola
    private void registrar() {
        Cliente cli = new Cliente();
        
        System.out.println("\n--- Formulario de Registro ---");
        System.out.print("Nombre completo: ");
        cli.setNombre(sc.nextLine());
        
        System.out.print("Cédula / Identificación: ");
        cli.setIdentificacion(sc.nextLine());
        
        System.out.print("Correo electrónico: ");
        cli.setEmail(sc.nextLine());
        
        System.out.print("Número de teléfono: ");
        cli.setTelefono(sc.nextLine());

        // Enviamos el objeto al controlador que maneja la transacción SQL
        gc.guardar(cli);
    }

    // Método para mostrar los clientes registrados
    private void listar() {
        System.out.println("\n======= LISTA DE CLIENTES REGISTRADOS =======");
        var listaClientes = gc.listar(); // Guardamos la lista en una variable para no re-consultar
        
        if (listaClientes.isEmpty()) {
            System.out.println("Actualmente no existen clientes en la base de datos.");
        } else {
            // Imprime cada cliente usando el método toString que definimos en el Modelo
            listaClientes.forEach(System.out::println);
        }
    }
}