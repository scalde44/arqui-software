package co.com.sofkau.websocket.controller;

import co.com.sofkau.model.generic.SocketSendMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ServerEndpoint("/events/{correlationId}")
public class WebSocketSendController implements SocketSendMessage {
    private static final Logger logger = Logger.getLogger(WebSocketSendController.class.getName());
    private static Map<String, Map<String, Session>> sessions;

    public WebSocketSendController() {
        if (Objects.isNull(sessions)) {
            sessions = new ConcurrentHashMap<>();
        }
    }

    @OnOpen
    public void onOpen(Session session, @RequestParam MultiValueMap reqMap, @PathVariable("correlationId") String correlationId) {
        logger.info("Connect by " + correlationId);
        var map = sessions.getOrDefault(correlationId, new HashMap<>());
        map.put(session.channel().id().asShortText(), session);
        sessions.put(correlationId, map);
    }

    @OnClose
    public void onClose(Session session, @PathVariable("correlationId") String correlationId) {
        sessions.get(correlationId).remove(session.channel().id().asShortText());
        logger.info("Desconnect by " + correlationId);

    }

    @OnError
    public void onError(Session session, @PathVariable("correlationId") String correlationId, Throwable throwable) {
        sessions.get(correlationId).remove(session.channel().id().asShortText());
        logger.log(Level.SEVERE, throwable.getMessage());

    }

    @Override
    public void send(String correlationId, String event) {
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            sessions.get(correlationId).values()
                    .forEach(session -> {
                        session.sendText(event);
                    });
        }
    }
}
