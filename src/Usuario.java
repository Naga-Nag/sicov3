public class Usuario {
    private int id;
    private String nombre;
    private String password;
    private int departamento_id;

    public Usuario(int id, String nombre, String password, int departamento_id) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.departamento_id = departamento_id;
    }

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
}