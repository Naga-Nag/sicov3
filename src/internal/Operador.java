package internal;

//El operador puede pedir material para las obras de su departamento (entre otros), crear obras para su propio departamento y editarlas, eliminarlas etc

public class Operador extends Usuario {
    private static int rol = 2;
    public Operador(int id, String nombre, String password, int departamento_id) {
        super(id, nombre, password, departamento_id, rol);
    }
    
}
