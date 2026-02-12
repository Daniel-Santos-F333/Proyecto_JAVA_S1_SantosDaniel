package MODELO;

public class Celular {
    private int id;
    private String marca, modelo, sistemaOperativo;
    private double precio;
    private int stock;
    private CategoriaGama gama;

    // Constructor vacío
    public Celular() {}

    // CONSTRUCTOR PRINCIPAL (Revisa bien este orden)
    public Celular(int id, String marca, String modelo, double precio, int stock, String sistemaOperativo, CategoriaGama gama) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
    }

    // Getters y Setters...
    public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getSistemaOperativo() { return sistemaOperativo; }
    public CategoriaGama getGama() { return gama; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + marca + " " + modelo + " | $" + precio + " | Stock: " + stock + " | SO: " + sistemaOperativo + " | Gama: " + gama;
    }
}