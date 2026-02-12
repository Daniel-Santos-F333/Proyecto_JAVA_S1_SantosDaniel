package VISTA;

import CONTROLADOR.GestionarCelularImpl;
import MODELO.Celular;
import MODELO.CategoriaGama;
import java.util.Scanner;

// Clase para la interfaz de gestión de celulares
public class menuCelular {

    private final GestionarCelularImpl gc = new GestionarCelularImpl();
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                    \n======= GESTIÓN DE CELULARES =======
                    1. Registrar nuevo equipo
                    2. Ver inventario completo
                    3. Consultar stock crítico (<5)
                    4. Exportar reporte a TXT
                    5. Volver al menú principal
                    """);
            System.out.print("Seleccione una opción: ");
            
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
                case 5 -> System.out.println("Saliendo del módulo de celulares...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (op != 5);
    }

    private void registrar() {
        Celular c = new Celular();

        System.out.println("\n--- Registro de nuevo celular ---");
        System.out.print("ID de Marca (Ej: 1 para Samsung): ");
        c.setMarca(sc.nextLine());

        System.out.print("Modelo del equipo: ");
        c.setModelo(sc.nextLine());

        // Validación de entrada para el precio
        try {
            System.out.print("Precio de venta: ");
            c.setPrecio(Double.parseDouble(sc.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Precio inválido, se registrará como 0.0");
            c.setPrecio(0.0);
        }

        // Validación de entrada para el stock
        try {
            System.out.print("Cantidad inicial en stock: ");
            c.setStock(Integer.parseInt(sc.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Stock inválido, se registrará como 0");
            c.setStock(0);
        }

        System.out.print("Sistema Operativo: ");
        c.setSistemaOperativo(sc.nextLine());

        // Validación para el tipo de ENUM Gama
        System.out.print("Gama (ALTA, MEDIA, BAJA): ");
        String entradaGama = sc.nextLine().toUpperCase().trim();

        try {
            c.setGama(CategoriaGama.valueOf(entradaGama));
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Gama no reconocida. Se asignará MEDIA por defecto.");
            c.setGama(CategoriaGama.MEDIA);
        }

        // Envío del objeto al controlador para persistencia en DB
        gc.registrar(c);
        System.out.println("✅ El equipo ha sido procesado.");
    }

    private void listar() {
        System.out.println("\n======= LISTADO DE INVENTARIO =======");
        var lista = gc.listar();
        if(lista.isEmpty()) {
            System.out.println("No hay registros disponibles.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void stockBajo() {
        System.out.println("\n======= EQUIPOS CON STOCK CRÍTICO =======");
        var listaBajos = gc.stockBajo();
        if(listaBajos.isEmpty()) {
            System.out.println("Todo el inventario está por encima del mínimo.");
        } else {
            listaBajos.forEach(System.out::println);
        }
    }
}