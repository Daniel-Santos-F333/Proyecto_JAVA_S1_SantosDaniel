package CONTROLADOR;

import MODELO.Celular;
import java.util.ArrayList;

public interface GestionarCelular {
    void registrar(Celular cel);
    void actualizar(Celular cel);
    void eliminar(int id);
    ArrayList<Celular> listar();
    ArrayList<Celular> stockBajo();
    void generarReporteArchivo(); 
}