import com.example.filtro.FiltroLongitudEmail;
import com.example.filtro.FiltroNombreMayusculas;
import com.example.model.Usuario;
import com.example.tuberias.TuberiaUsuarios;

public class Main {
    public static void main(String[] args) {
        TuberiaUsuarios tuberiaUsuarios = new TuberiaUsuarios();

        // Agregar filtros a la tubería
        tuberiaUsuarios.agregarFiltro(new FiltroNombreMayusculas());
        tuberiaUsuarios.agregarFiltro(new FiltroLongitudEmail());

        // Crear un usuario
        Usuario usuario = new Usuario(1L, "Juan Pérez", "juan@example.com");

        // Ejecutar la tubería con el usuario
        tuberiaUsuarios.ejecutarTuberia(usuario);

        // Mostrar el usuario después de aplicar los filtros
        System.out.println("Usuario modificado:");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Email: " + usuario.getEmail());
    }
}