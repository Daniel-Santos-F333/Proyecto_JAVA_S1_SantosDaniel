package com.mycompany.tecnostore; 

import CONTROLADOR.GestionarCelularImpl;
import CONTROLADOR.GestionarMarca;
import MODELO.Celular;
import MODELO.CategoriaGama;
import java.util.ArrayList; // <--- Faltaba esta línea

public class TecnoStore {

    public static void main(String[] args) {
        // Instanciamos los controladores
        GestionarMarca gMarca = new GestionarMarca();
        GestionarCelularImpl gCel = new GestionarCelularImpl();

        // 1. Marcas ya registradas (Samsung=1, Apple=2)
        // gMarca.registrarMarca("Samsung"); 
        // gMarca.registrarMarca("Apple");

        // 2. REGISTRO DEL CELULAR
        Celular nuevoCel = new Celular(0, "1", "S24 Ultra", 5000000.0, 10, "Android", CategoriaGama.ALTA);

        System.out.println("--- Intentando registro de Celular ---");
        gCel.registrar(nuevoCel);

        // 3. LISTADO FINAL
        System.out.println("\n--- Inventario en Base de Datos ---");
        ArrayList<Celular> lista = gCel.listar();
        
        if (lista.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Celular c : lista) {
                System.out.println(c);
            }
        }
    } // Cierre del método main
} // <--- ESTA ES LA LLAVE QUE FALTABA (Cierre de la clase)