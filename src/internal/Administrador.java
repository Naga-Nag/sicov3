package internal;

import java.sql.SQLException;

public class Administrador extends Usuario {
    private boolean admin = true;
    // Atributos específicos del administrador (si los hubiera)
    // con el atributo especifico de admin en esta clase, la puedo quitar de usuario quiza?
  
    public Administrador(int id, String nombre, String password, int departamento_id) {
      super(id, nombre, password, departamento_id, true); // Asigna admin = true por defecto
    }
  
    public Administrador(String nombre, String password, int departamento_id) {
      super(nombre, password, departamento_id, true); // Asigna admin = true por defecto
    }
  
    // Métodos específicos del administrador (si los hubiera)
  
    public void crearUsuario(Usuario usuario) throws SQLException {
      // Lógica para crear un usuario
    }
  
    public void eliminarUsuario(Usuario usuario) throws SQLException {
      // Lógica para eliminar un usuario
    }
  
  }
