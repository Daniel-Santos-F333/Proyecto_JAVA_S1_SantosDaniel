package VISTA;

import CONTROLADOR.GestionarMarca;
import java.util.Scanner;

public class menuMarca {
    GestionarMarca gm = new GestionarMarca();
    Scanner sc = new Scanner(System.in);

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
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                op = 0;
            }

            switch (op) {
                case 1 -> {
                    System.out.println("Nombre de la marca:");
                    gm.registrarMarca(sc.nextLine());
                }
                case 2 -> gm.listarMarcas();
                case 3 -> {
                    System.out.println("ID de la marca a eliminar:");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        gm.eliminarMarca(id);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ ID inválido.");
                    }
                }
            }
        } while (op != 4);
    }
}