package VISTA;

import java.util.Scanner;

public class menu {

    public void Menu_Principal() {
        Scanner sc = new Scanner(System.in);
        int op = 0; // Inicializamos en 0

        do {
            System.out.println("""
                           \n******************************
                                     TECNO STORE
                           1.   Gestionar Celulares
                           2.   Gestionar Clientes
                           3.   Gestionar Marcas
                           4.   Salir
                           ******************************
                           """);
            System.out.print("Seleccione una opción: ");

            // --- BLINDAJE CONTRA LETRAS ---
            try {
                op = Integer.parseInt(sc.nextLine()); // Usamos nextLine para no dejar basura en el buffer
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Debes ingresar un número válido.");
                op = 0; // Reiniciamos la opción para que repita el ciclo
                continue; // Salta al inicio del do-while
            }

            switch (op) {
                case 1 -> {
                    menuCelular mc = new menuCelular();
                    mc.menu();
                }
                case 2 -> {
                    menuCliente mc = new menuCliente();
                    mc.menu();
                }
                case 3 -> {
                    menuMarca mm = new menuMarca();
                    mm.menu();
                }
                case 4 -> System.out.println("¡Gracias por usar TecnoStore!");
                default -> System.out.println("❌ Opción no válida, intenta de nuevo.");
            }
        } while (op != 4);
    }
}