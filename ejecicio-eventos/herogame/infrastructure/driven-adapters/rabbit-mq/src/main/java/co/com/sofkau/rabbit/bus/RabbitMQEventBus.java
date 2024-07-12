package co.com.sofkau.rabbit.bus;

import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventBus;
import co.com.sofkau.model.generic.EventRepository;
import co.com.sofkau.rabbit.generic.SuccessNotification;
import co.com.sofkau.rabbit.generic.SuccessNotificationSerializer;
import co.com.sofkau.rabbit.helper.QueuesProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RabbitMQEventBus implements EventBus {
    private final RabbitTemplate rabbitTemplate;
    private final EventRepository repository;
    private final QueuesProperties queuesProperties;

    @Override
    public Mono<Void> publish(DomainEvent event) {
        var notification = SuccessNotification.wrapEvent(queuesProperties.getExchange(), event);
        var notificationSerialization = SuccessNotificationSerializer.instance().serialize(notification);
        rabbitTemplate.convertAndSend(this.queuesProperties.getExchange(), event.type, notificationSerialization.getBytes());
        return repository.save(event, event.type);
    }

    @Override
    public Mono<Void> publishError(Throwable errorEvent) {
        return Mono.empty();
    }
}
