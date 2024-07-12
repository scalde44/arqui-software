package co.com.sofkau.model.generic;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * The type Change event subscriber.
 */
public class ChangeEventSubscriber {
    private final List<DomainEvent> changes = new LinkedList<>();
    private final Map<String, AtomicLong> versions = new ConcurrentHashMap<>();
    private final Set<Consumer<? super DomainEvent>> observables = new HashSet<>();

    /**
     * Gets changes.
     *
     * @return the changes
     */
    public List<DomainEvent> getChanges() {
        return changes;
    }

    /**
     * Append change change apply.
     *
     * @param event the event
     * @return the change apply
     */
    public final ChangeApply appendChange(DomainEvent event) {
        changes.add(event);
        return () -> applyEvent(event);
    }

    /**
     * Subscribe.
     *
     * @param eventChange the event change
     */
    public final void subscribe(EventChange eventChange) {
        this.observables.addAll(eventChange.behaviors);
    }

    /**
     * Apply event.
     *
     * @param domainEvent the domain event
     */
    public final void applyEvent(DomainEvent domainEvent) {
        observables.forEach(consumer -> {
            try {
                consumer.accept(domainEvent);
                if (domainEvent instanceof Incremental) {
                    var map = versions.get(domainEvent.type);
                    long version = nextVersion(domainEvent, map);
                    domainEvent.setVersionType(version);
                }
            } catch (ClassCastException ignored) {
            }
        });
    }

    private long nextVersion(DomainEvent domainEvent, AtomicLong map) {
        if (map == null) {
            versions.put(domainEvent.type, new AtomicLong(domainEvent.versionType()));
            return domainEvent.versionType();
        }
        return versions.get(domainEvent.type).incrementAndGet();
    }


    /**
     * The interface Change apply.
     */
    @FunctionalInterface
    public interface ChangeApply {
        /**
         * Apply.
         */
        void apply();
    }

}