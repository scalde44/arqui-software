import com.example.model.Usuario;
import com.example.model.peer.PeerUsuario;

public class Main {
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario(1L, "Juan Pérez", "juan@example.com", "192.168.1.2");
        Usuario usuario2 = new Usuario(2L, "Ana López", "ana@example.com", "192.168.1.3");

        PeerUsuario peer1 = new PeerUsuario(usuario1);
        PeerUsuario peer2 = new PeerUsuario(usuario2);

        peer1.agregarPeer(usuario2);
        peer2.agregarPeer(usuario1);

        // Enviar y recibir mensajes simulados
        peer1.enviarMensaje("Hola, Ana!", usuario2);
        peer2.recibirMensaje("Hola, Ana!", usuario1);

        peer2.enviarMensaje("Hola, Juan!", usuario1);
        peer1.recibirMensaje("Hola, Juan!", usuario2);
    }
}