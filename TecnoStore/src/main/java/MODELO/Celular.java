package MODELO;

// Clase que representa la entidad Celular en el sistema
public class Celular {
    private int id;
    private String marca, modelo, sistemaOperativo;
    private double precio;
    private int stock;
    private CategoriaGama gama;

    public Celular() {}

    // Constructor para inicializar todos los atributos del celular
    public Celular(int id, String marca, String modelo, double precio, int stock, String sistemaOperativo, CategoriaGama gama) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
    }

    // Métodos de acceso (Getters y Setters)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getSistemaOperativo() { return sistemaOperativo; }
    public void setSistemaOperativo(String sistemaOperativo) { this.sistemaOperativo = sistemaOperativo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public CategoriaGama getGama() { return gama; }
    public void setGama(CategoriaGama gama) { this.gama = gama; }

    // Método para mostrar la información del celular de forma legible
    @Override
    public String toString() {
        return "ID: " + id + " | " + marca + " " + modelo + " | $" + precio + 
               " | Stock: " + stock + " | SO: " + sistemaOperativo + " | Gama: " + gama;
    }
}