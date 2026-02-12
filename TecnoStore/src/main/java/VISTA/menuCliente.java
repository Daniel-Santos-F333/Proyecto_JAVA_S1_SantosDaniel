package VISTA;

import CONTROLADOR.GestionarClienteImpl;
import MODELO.Cliente;
import java.util.Scanner;

public class menuCliente {
    GestionarClienteImpl gc = new GestionarClienteImpl();
    Scanner sc = new Scanner(System.in);

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                               \n======= GESTIÓN DE CLIENTES =======
                               1. Registrar Cliente
                               2. Listar Clientes
                               3. Regresar
                               """);
            System.out.print("Seleccione una opción: ");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                op = 0;
            }

            switch (op) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> System.out.println("Regresando...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (op != 3);
    }

    private void registrar() {
        Cliente cli = new Cliente();
        System.out.println("Nombre completo:");
        cli.setNombre(sc.nextLine());
        
        System.out.println("Documento de Identificación:");
        cli.setIdentificacion(sc.nextLine());
        
        System.out.println("Correo electrónico:");
        cli.setEmail(sc.nextLine());
        
        System.out.println("Teléfono:");
        cli.setTelefono(sc.nextLine());

        gc.guardar(cli);
        System.out.println("✅ Cliente guardado correctamente.");
    }

    private void listar() {
        System.out.println("\n======= LISTADO DE CLIENTES =======");
        if (gc.listar().isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            gc.listar().forEach(System.out::println);
        }
    }
}