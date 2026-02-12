package com.mycompany.tecnostore;

import VISTA.menu;

// Clase principal que inicia la ejecución del sistema
public class TecnoStore {
    
    public static void main(String[] args) {
        // Instanciamos la vista principal y lanzamos el menú
        menu principal = new menu();
        
        System.out.println(">>> Iniciando sistema TecnoStore...");
        principal.Menu_Principal();
    }
}