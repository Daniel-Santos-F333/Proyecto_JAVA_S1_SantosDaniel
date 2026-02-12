package VISTA;

import CONTROLADOR.GestionarMarca;
import java.util.Scanner;

// Interfaz para gestionar las marcas de los dispositivos
public class menuMarca {
    private final GestionarMarca gm = new GestionarMarca();
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                               \n======= GESTIÓN DE MARCAS =======
                               1. Registrar Nueva Marca
                               2. Listar Marcas
                               3. Eliminar Marca
                               4. Regresar
                               """);
            System.out.print("Seleccione una opción: ");
            
            // Blindaje para evitar que el programa falle si se ingresan letras
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                op = 0;
                continue;
            }

            switch (op) {
                case 1 -> registrar();
                case 2 -> gm.listarMarcas();
                case 3 -> eliminar();
                case 4 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (op != 4);
    }

    // Lógica para capturar el nombre de la nueva marca
    private void registrar() {
        System.out.print("Ingrese el nombre de la nueva marca: ");
        String nombre = sc.nextLine();
        
        if (!nombre.trim().isEmpty()) {
            gm.registrarMarca(nombre);
        } else {
            System.out.println("❌ Error: El nombre de la marca no puede estar vacío.");
        }
    }

    // Lógica para eliminar marca con confirmación de seguridad
    private void eliminar() {
        System.out.print("Ingrese el ID de la marca que desea eliminar: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            
            System.out.println("⚠️ ADVERTENCIA: ¿Está seguro de eliminar el ID " + id + "?");
            System.out.print("Escriba 'S' para confirmar: ");
            String confirmar = sc.nextLine().toUpperCase();
            
            if (confirmar.equals("S")) {
                gm.eliminarMarca(id);
            } else {
                System.out.println("❌ Operación cancelada por el usuario.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: El ID debe ser un número entero.");
        }
    }
}