
package CONTROLADOR;

import MODELO.Celular;
import java.util.ArrayList;


public interface GestionStock {
        ArrayList<Celular> stockBajo();
        void generarReporteArchivo(); 
}
