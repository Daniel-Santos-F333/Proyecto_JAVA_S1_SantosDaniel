package CONTROLADOR;

import MODELO.Venta;
import java.util.ArrayList;

public interface GestionarVenta {
    void registrarVenta(Venta v);
    ArrayList<Venta> listarVentas();
    void generarFacturaTXT(Venta v);
}