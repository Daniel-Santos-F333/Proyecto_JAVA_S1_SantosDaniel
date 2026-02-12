package VISTA;

import java.util.Scanner;

// Clase principal de la interfaz de usuario
public class menu {

    public void Menu_Principal() {
        Scanner sc = new Scanner(System.in);
        int op = 0; 

        do {
            System.out.println("""
                               \n******************************
                                         TECNO STORE
                                   1. Gestionar Celulares
                                   2. Gestionar Clientes
                                   3. Gestionar Marcas
                                   4. Salir
                               ******************************
                               """);
            System.out.print("Seleccione una opción: ");

            // Validación para evitar que el programa se cierre si el usuario ingresa letras
            try {
                op = Integer.parseInt(sc.nextLine()); 
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingrese un número válido (1-4).");
                op = 0; 
                continue; 
            }

            switch (op) {
                case 1 -> {
                    menuCelular mc = new menuCelular();
                    mc.menu();
                }
                case 2 -> {
                    menuCliente mcli = new menuCliente();
                    mcli.menu();
                }
                case 3 -> {
                    menuMarca mm = new menuMarca();
                    mm.menu();
                }
                case 4 -> System.out.println("Cerrando sistema... ¡Hasta pronto!");
                default -> System.out.println("❌ Opción no disponible.");
            }
        } while (op != 4);
    }
}