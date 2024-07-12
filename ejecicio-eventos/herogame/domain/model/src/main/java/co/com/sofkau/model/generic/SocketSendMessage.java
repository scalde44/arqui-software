package co.com.sofkau.model.generic;

public interface SocketSendMessage {
    void send(String correlationId, String event);
}
