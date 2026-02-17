package VISTA;

import CONTROLADOR.GestionarCelularImpl;
import CONTROLADOR.GestionarClienteImpl;
import CONTROLADOR.GestionarVentaImpl;
import MODELO.Celular;
import MODELO.Cliente;
import MODELO.DetalleVenta;
import MODELO.Venta;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class menuVenta {

    private final GestionarVentaImpl gv = new GestionarVentaImpl();
    private final GestionarClienteImpl gcl = new GestionarClienteImpl();
    private final GestionarCelularImpl gce = new GestionarCelularImpl();
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                               \n======= MÓDULO DE VENTAS =======
                               1. Realizar Nueva Venta
                               2. Listar Ventas Realizadas
                               3. Volver al Menú Principal
                               """);
            System.out.print("Seleccione una opción: ");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                continue;
            }

            switch (op) {
                case 1 -> nuevaVenta();
                case 2 -> listarVentas();
                case 3 -> System.out.println("Regresando...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (op != 3);
    }

    private void nuevaVenta() {
        Venta venta = new Venta();
        double totalVenta = 0;

        System.out.println("\n--- Seleccionar Cliente ---");
        var clientes = gcl.listar();
        clientes.forEach(System.out::println);
        System.out.print("Ingrese el ID del cliente: ");
        venta.setClienteId(Integer.parseInt(sc.nextLine()));

        boolean agregando = true;
        while (agregando) {
            System.out.println("\n--- Productos Disponibles ---");
            var celulares = gce.listar();
            celulares.forEach(System.out::println);

            System.out.print("Ingrese el ID del celular a vender: ");
            int idCel = Integer.parseInt(sc.nextLine());
            
            System.out.print("Cantidad: ");
            int cant = Integer.parseInt(sc.nextLine());

            Celular seleccionado = celulares.stream()
                    .filter(c -> c.getId() == idCel)
                    .findFirst()
                    .orElse(null);

            if (seleccionado != null && seleccionado.getStock() >= cant) {
                double subtotal = seleccionado.getPrecio() * cant;
                DetalleVenta det = new DetalleVenta(0, 0, idCel, cant, subtotal);
                venta.agregarDetalle(det);
                totalVenta += subtotal;
                System.out.println("✅ Producto añadido.");
            } else {
                System.out.println("❌ Stock insuficiente o ID no válido.");
            }

            System.out.print("¿Desea añadir otro producto? (S/N): ");
            if (sc.nextLine().equalsIgnoreCase("N")) agregando = false;
        }

        if (!venta.getDetalles().isEmpty()) {
            venta.setTotal(totalVenta);
            venta.setFecha(new Date());
            gv.registrarVenta(venta);
            gv.generarFacturaTXT(venta);
        } else {
            System.out.println("⚠️ No se agregaron productos, venta cancelada.");
        }
    }

    private void listarVentas() {
        System.out.println("\n======= HISTORIAL DE VENTAS =======");
        var lista = gv.listarVentas();
        if (lista.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta v : lista) {
                System.out.println("ID: " + v.getId() + " | Cliente ID: " + v.getClienteId() + " | Fecha: " + v.getFecha() + " | Total: $" + v.getTotal());
            }
        }
    }
}