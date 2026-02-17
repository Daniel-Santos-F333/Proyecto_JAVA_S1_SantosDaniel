package VISTA;

import CONTROLADOR.GestionarCelularImpl;
import CONTROLADOR.GestionarMarca;
import CONTROLADOR.InputValidator;
import MODELO.Celular;
import MODELO.CategoriaGama;
import java.util.Scanner;

// Clase para la interfaz de gestión de celulares
public class menuCelular {
    
    private final GestionarMarca gm = new GestionarMarca();
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
        System.out.println("\n--- Registro de nuevo celular ---");
        Celular c = new Celular();
        gm.listarMarcas(); // Esto mostrará el ID y el Nombre de las marcas de la DB
    
    System.out.print("Seleccione el ID de la marca: ");
    c.setMarca(sc.nextLine());
        // Usamos el validador centralizado
        c.setMarca(InputValidator.leerTextoValido("ID de Marca: ", "\\d+"));
        c.setModelo(InputValidator.leerTextoValido("Modelo: ", "^[a-zA-Z0-9 ]+$"));
        c.setPrecio(InputValidator.leerDoublePositivo("Precio: "));
        c.setStock(InputValidator.leerEnteroPositivo("Stock Inicial: "));
        c.setSistemaOperativo(InputValidator.leerTextoValido("Sistema Operativo: ", null));
        c.setGama(InputValidator.leerGama("Gama (ALTA, MEDIA, BAJA): "));

        gc.registrar(c);
        System.out.println("✅ Equipo registrado exitosamente.");
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
    
    private void eliminar() {
    int id = InputValidator.leerEnteroPositivo("Ingrese el ID del celular a eliminar: ");
    
    // El validador de confirmación que pediste
    System.out.print("⚠️ ¿Está seguro que desea eliminar el ID " + id + "? (S/N): ");
    String confirmacion = sc.nextLine().toUpperCase();
    
    if (confirmacion.equals("S")) {
        gc.eliminar(id);
    } else {
        System.out.println("❌ Operación cancelada.");
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