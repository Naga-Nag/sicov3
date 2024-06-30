package internal;

//El operador puede crear y eliminar obras de su propio departamento
public class Operador extends Usuario {

    private int id;
    private String nombre;
    private String password;
    private int departamento_id;
    private int rol = 2;

    public Operador(int id, String nombre, String password, int departamento_id, int rol) {
        super(id, nombre, password, departamento_id, rol);
    }
}
