package MODELO;

public class DetalleVenta {
    private int id;
    private int ventaId;
    private int celularId;
    private int cantidad;
    private double subtotal;

    public DetalleVenta() {
    }

    public DetalleVenta(int id, int ventaId, int celularId, int cantidad, double subtotal) {
        this.id = id;
        this.ventaId = ventaId;
        this.celularId = celularId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVentaId() { return ventaId; }
    public void setVentaId(int ventaId) { this.ventaId = ventaId; }

    public int getCelularId() { return celularId; }
    public void setCelularId(int celularId) { this.celularId = celularId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}