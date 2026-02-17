package CONTROLADOR;

import java.util.Scanner;
import MODELO.CategoriaGama;

public class InputValidator {
    private static final Scanner sc = new Scanner(System.in);

    // Valida que el texto no sea vacío y cumpla con un patrón (letras/números)
    public static String leerTextoValido(String mensaje, String regex) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            if (!entrada.isEmpty() && (regex == null || entrada.matches(regex))) {
                return entrada;
            }
            System.out.println("❌ Entrada inválida o vacía. Intente de nuevo.");
        }
    }

    // Valida números decimales positivos
    public static double leerDoublePositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine());
                if (valor > 0) return valor;
                System.out.println("❌ El valor debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingrese un número decimal válido.");
            }
        }
    }

    // Valida números enteros positivos
    public static int leerEnteroPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(sc.nextLine());
                if (valor >= 0) return valor;
                System.out.println("❌ El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingrese un número entero válido.");
            }
        }
    }

    // Valida la categoría del ENUM
    public static CategoriaGama leerGama(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return CategoriaGama.valueOf(sc.nextLine().toUpperCase().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Solo se permite: ALTA, MEDIA o BAJA.");
            }
        }
    }
}