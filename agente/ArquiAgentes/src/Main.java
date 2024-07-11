import com.example.agente.AgenteNotificacino;
import com.example.agente.AgenteValidacion;
import com.example.coordinador.AgenteCentral;
import com.example.model.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "juan@example.com");

        AgenteCentral agenteCentral = new AgenteCentral();
        agenteCentral.registrarAgente(new AgenteValidacion());
        agenteCentral.registrarAgente(new AgenteNotificacino());

        agenteCentral.procesarUsuario(usuario);
    }
}