package MODELO;

import java.util.ArrayList;
import java.util.Date;

public class Venta {
    private int id;
    private int clienteId;
    private Date fecha;
    private double total;
    private ArrayList<DetalleVenta> detalles;

    public Venta() {
        this.detalles = new ArrayList<>();
    }

    public Venta(int id, int clienteId, Date fecha, double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
        this.detalles = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public ArrayList<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(ArrayList<DetalleVenta> detalles) { this.detalles = detalles; }
    
    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
    }
}