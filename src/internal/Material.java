package internal;

public class Material {
    private int id;
    private String nomenclatura;
    private String nombre;
    private String descripcion;
    private int obra_id;
    private double precio;

    public Material(int id, String nomenclatura, String nombre, String descripcion, int obra_id, double precio) {
        this.id = id;
        this.nomenclatura = nomenclatura;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obra_id = obra_id;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomenclatura() {
        return nomenclatura;
    }
    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getObra_id() {
        return obra_id;
    }
    public void setObra_id(int obra_id) {
        this.obra_id = obra_id;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}