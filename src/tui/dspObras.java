package tui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import internal.Material;
import internal.Obra;
import internal.Obra_Material;
import internal.Usuario;

public class dspObras {
    public static void menu(Usuario usuario) throws SQLException {
        utils.limpiarDisplay();
        mostrarObras(usuario);
        menuInterno(usuario);
    }

    public static void mostrarObra(int id) throws SQLException {
        Obra obra = new Obra(id);

        System.out.println("ID: " + id);
        System.out.println("Nombre: " + obra.getNombre());
        System.out.println("Descripción: " + obra.getDescripcion());
        System.out.println("Departamento: " + obra.getDepartamento());

        System.out.println("Materiales: ");
        var materiales = Obra_Material.materialesDeObra(id);
        for (Material material : materiales) {
            System.out.println(material.getNomenclatura() + " - Cantidad asignada: " + Obra_Material.cantidadMaterialAsignada(id, material.getId()));
        }

    }

    /*
     * SOLUCIONADO: Bug cuando hay mas de una obra en el arraylist
     * en la consola se repiten algunos datos
     * pareciera que tiene que ver con algo de la consola mas que con el programa
     *
     * 
     * Se soluciono con el uso de un StringBuilder, system.out.println() causaba el bug de alguna manera
     */
    private static void mostrarObras(Usuario usuario) throws SQLException {
        if (usuario.esAdmin()) {
            ArrayList<Obra> obras = Obra.todasLasObras();
    
            StringBuilder sb = new StringBuilder();
            sb.append("|| Total de Obras: ").append(obras.size()).append(" ||\n");
            sb.append("-----------------------\n");
    
            for (Obra obra : obras) {
                sb.append("ID: ").append(obra.getId()).append("\n");
                sb.append("Nombre: ").append(obra.getNombre()).append("\n");
                sb.append("Descripción: ").append(obra.getDescripcion()).append("\n");
                sb.append("Departamento: ").append(obra.getDepartamento()).append("\n");
                sb.append("------------------------------------\n");
            }
    
            System.out.println(sb.toString());
        }
    }

    private static void menuCrearObra(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Crear Obra");

        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Descripción: ");
        String descrip = scanner.nextLine();

        System.out.println("Departamento: ");
        String depto = scanner.nextLine();

        Obra.crearObra(nombre, descrip, depto);
    }

    private static void menuVerObra(Scanner scanner, Usuario usuario) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Ver Obra");

        // 1. Show available obras to the user
        mostrarObras(usuario);

        System.out.println("Ingrese el ID de la obra: ");
        int idObra = scanner.nextInt();
        scanner.nextLine();

        // Input validation to ensure obra exists
        if (!Obra.existeObra(idObra)) {
            System.out.println("Error: La obra con ID " + idObra + " no existe.");
            return;
        }
        utils.limpiarDisplay();
        mostrarObra(idObra);

        System.out.println("");
        System.out.println("1. Asignar Material || 2. Quitar Material || 3. Editar Variables || 4. Volver");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                // Assign material
                utils.limpiarDisplay();
                menuAsignarMaterial(scanner, idObra);
                menu(usuario);
                break;
            case 2:
                // Remove material
                utils.limpiarDisplay();
                menuQuitarMaterial(scanner, idObra);
                menu(usuario);
                break;
            case 3:
                // Edit obra variables (nombre, descripcion, departamento)
                utils.limpiarDisplay();
                menuEditarVariablesObra(scanner, idObra);
                menu(usuario);
                break;
            case 4:
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void menuAsignarMaterial(Scanner scanner, int idObra) throws SQLException {
        // Mostrar materiales disponibles
        utils.limpiarDisplay();
        var materiales = Material.todosLosMateriales();
        System.out.println("Materiales Disponibles:");
        for (Material material : materiales) {
            System.out.println("ID: " + material.getId() + " - " + material.getNomenclatura() + " - Stock: " + material.getStock());
        }

        System.out.println("Ingrese la nomenclatura del material a asignar: ");
        int idMaterial = scanner.nextInt();

        // Veo si el material existe
        if (!Material.existeMaterial(idMaterial)) {
            System.out.println("Error: El material no existe.");
            return;
        }

        System.out.println("Ingrese la cantidad a asignar: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        // Asignar material a la obra
        Obra_Material.asignarMaterial(idObra, idMaterial, cantidad);
        System.out.println("Material asignado exitosamente a la obra.");
    }

    private static void menuQuitarMaterial(Scanner scanner, int idObra) throws SQLException {
        // Muestro los materiales asignados a la obra
        var materiales = Obra_Material.materialesDeObra(idObra);
        System.out.println("Materiales Asignados:");
        for (Material material : materiales) {
            System.out.println("ID: " + material.getId() + "- " + material.getNomenclatura());
        }

        //Elijo el material a quitar
        System.out.println("Ingrese la id del material a quitar: ");
        int idMaterial = scanner.nextInt();

        // Me fijo que realmente el material este asignado a la obra (que el numero de id aparezca)
        if (!Obra_Material.materialEstaEnObra(idObra, idMaterial)) {
            System.out.println(
                    "Error: El material no está asignado a la obra.");
            return;
        }

        // Quito la asociación del material de la obra
        Obra_Material.quitarMaterial(idObra, idMaterial);
        System.out.println("Material quitado exitosamente de la obra.");
    }

    private static void menuEditarVariablesObra(Scanner scanner, int idObra) throws SQLException {
        Obra obra = new Obra(idObra);

        System.out.println("Obra a editar:");
        System.out.println("ID: " + obra.getId());
        System.out.println("Nombre: " + obra.getNombre());
        System.out.println("Descripción: " + obra.getDescripcion());
        System.out.println("Departamento: " + obra.getDepartamento());

        System.out.println("¿Desea editar alguno de los siguientes datos? (s/n)");
        String editar = scanner.nextLine();

        if (editar.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo nombre (o deje vacío si no desea modificarlo): ");
            String nuevoNombre = scanner.nextLine();

            System.out.println("Ingrese la nueva descripción (o deje vacío si no desea modificarlo");
            System.out.println("Ingrese la nueva descripción (o deje vacío si no desea modificarlo): ");
            String nuevaDescripcion = scanner.nextLine();

            System.out.println("Ingrese el nuevo departamento (o deje vacío si no desea modificarlo): ");
            String nuevoDepartamento = scanner.nextLine();

            // Update obra variables based on user input
            obra.setNombre(nuevoNombre.isEmpty() ? obra.getNombre() : nuevoNombre);
            obra.setDescripcion(nuevaDescripcion.isEmpty() ? obra.getDescripcion() : nuevaDescripcion);
            obra.setDepartamento(nuevoDepartamento.isEmpty() ? obra.getDepartamento() : nuevoDepartamento);

            obra.actualizarObra();
            System.out.println("Variables de la obra actualizadas exitosamente.");
        } else {
            System.out.println("Edición cancelada.");
        }
    }

    private static void menuEliminarObra(Scanner scanner, Usuario usuario) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Eliminar Obra");

        mostrarObras(usuario);

        System.out.println("ID de Obra a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Obra.eliminarObra(id);
    }

    private static void menuInterno(Usuario usuario) throws SQLException {
        System.out.println();
        System.out.println("1. Crear Obra || 2. Ver Obra || 3. Eliminar Obra || 4. Volver");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                menuCrearObra(scanner);
                break;
            case 2:
                menuVerObra(scanner, usuario);
                break;
            case 3:
                menuEliminarObra(scanner, usuario);
                break;
            case 4:
                utils.limpiarDisplay();
                dspMenuPrincipal.menu(usuario);
                break;
            default:
                utils.limpiarDisplay();
                menu(usuario);
        }
    }

    public static void main(String[] args) throws SQLException {
        menu(new Usuario(1, "admin", "admin", 1, 3));
    }

}
