package co.com.sofkau.api.generic;

import co.com.sofkau.model.generic.DeserializeEventException;
import co.com.sofkau.model.generic.DomainEvent;

import java.util.Date;

/**
 * The type Success notification.
 */
public class SuccessNotification extends Notification {

    private SuccessNotification(String origin, String typeName, Date occurredOn, String body) {
        super(origin, typeName, occurredOn, body);
    }

    /**
     * Wrap event success notification.
     *
     * @param origin      the origin
     * @param domainEvent the domain event
     * @return the success notification
     */
    public static SuccessNotification wrapEvent(String origin, DomainEvent domainEvent) {
        return new SuccessNotification(origin, domainEvent.getClass().getCanonicalName(),
                new Date(domainEvent.when.toEpochMilli()),
                EventSerializer.instance().serialize(domainEvent)
        );
    }

    /**
     * Deserialize event domain event.
     *
     * @return the domain event
     */
    public DomainEvent deserializeEvent() {
        try {
            return EventSerializer
                    .instance()
                    .deserialize(this.getBody(), Class.forName(this.getTypeName()));
        } catch (ClassNotFoundException e) {
            throw new DeserializeEventException(e.getCause());
        }
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        return SuccessNotificationSerializer.instance().serialize(this);
    }
}