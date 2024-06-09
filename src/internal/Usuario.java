package internal;


//En rigor el usuario solo debe consultar obras (TODO: Implementar funciones jerarquicas Usuario, Operador, Administrador)
public class Usuario {
    private int id;
    private String nombre;
    private String password;
    private int departamento_id;
    private int rol = 1; // 1: Usuario, 2: Operador, 3: Administrador

    public Usuario(int id, String nombre, String password, int departamento_id, int rol) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.departamento_id = departamento_id;
        this.rol = rol;
    }

    public Usuario(String nombre, String password, int departamento_id, int rol) {
        this.id = -1;
        this.nombre = nombre;
        this.password = password;
        this.departamento_id = departamento_id;
        this.rol = rol;
    }

    //getters y setters, justo esta clase no tiene mucho misterio

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public int getRol() {
        return rol;
    }
}