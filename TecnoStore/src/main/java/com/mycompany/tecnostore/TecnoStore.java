package com.mycompany.tecnostore;

import CONTROLADOR.GestionarClienteImpl;
import MODELO.Cliente;

public class TecnoStore {
    public static void main(String[] args) {
        // 1. Instanciamos el controlador
        GestionarClienteImpl control = new GestionarClienteImpl();

        // 2. Creamos un cliente de prueba
        // ID se pone 0 porque la DB lo genera solo
        Cliente prueba = new Cliente(0, "Daniel Prueba", "123456", "daniel@test.com", "300123");

        // 3. Intentamos guardar
        System.out.println("Intentando guardar cliente...");
        control.guardar(prueba);

        // 4. Verificamos listando
        System.out.println("Lista de clientes en la DB:");
        for (Cliente c : control.listar()) {
            System.out.println(c);
        }
    }
}