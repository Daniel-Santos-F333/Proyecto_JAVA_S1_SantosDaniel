package VISTA;

import CONTROLADOR.GestionarCelularImpl;
import MODELO.Celular;
import MODELO.CategoriaGama;
import java.util.Scanner;

public class menuCelular {

    GestionarCelularImpl gc = new GestionarCelularImpl();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("""
                    \n======= GESTIÓN DE CELULARES =======
                    1. Registrar
                    2. Listar Inventario
                    3. Reporte Stock Bajo (<5)
                    4. GENERAR REPORTE TXT
                    5. Regresar
                    """);
            System.out.print("Seleccione una opción: ");
            
            // Blindaje para la opción del menú
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
                case 3 -> stockBajo();
                case 4 -> gc.generarReporteArchivo();
                case 5 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (op != 5);
    }

    private void registrar() {
        Scanner sc = new Scanner(System.in);
        Celular c = new Celular();

        System.out.println("Ingrese Marca ID (1:Samsung, 2:Apple):");
        c.setMarca(sc.nextLine());

        System.out.println("Ingrese Modelo:");
        c.setModelo(sc.nextLine());

        // Blindaje para Precio
        try {
            System.out.println("Ingrese Precio:");
            c.setPrecio(Double.parseDouble(sc.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("❌ Precio inválido. Se asignará 0.0");
            c.setPrecio(0.0);
        }

        // Blindaje para Stock
        try {
            System.out.println("Ingrese Stock:");
            c.setStock(Integer.parseInt(sc.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("❌ Stock inválido. Se asignará 0");
            c.setStock(0);
        }

        System.out.println("Ingrese Sistema Operativo:");
        c.setSistemaOperativo(sc.nextLine());

        System.out.println("Ingrese Gama (ALTA, MEDIA, BAJA):");
        String entradaGama = sc.nextLine().toUpperCase().trim();

        try {
            c.setGama(CategoriaGama.valueOf(entradaGama));
        } catch (IllegalArgumentException e) {
            System.out.println("❌ ERROR: '" + entradaGama + "' no es una gama válida. Se asignará MEDIA por defecto.");
            c.setGama(CategoriaGama.MEDIA);
        }

        gc.registrar(c); // ¡No olvides llamar al controlador para guardar!
        System.out.println("✅ Registro procesado.");
    } // <--- AQUÍ FALTABA ESTA LLAVE

    private void listar() {
        System.out.println("\n======= INVENTARIO TOTAL =======");
        gc.listar().forEach(System.out::println);
    }

    private void stockBajo() {
        System.out.println("\n======= ALERTAS DE REPOSICIÓN =======");
        gc.stockBajo().forEach(System.out::println);
    }
}