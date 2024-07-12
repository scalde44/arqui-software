package co.com.sofkau.mongo.helper;

import co.com.sofkau.model.generic.StoredEvent;

public class DocumentEventStored {
    private String aggregateRootId;

    private StoredEvent storedEvent;

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public void setAggregateRootId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public StoredEvent getStoredEvent() {
        return storedEvent;
    }

    public void setStoredEvent(StoredEvent storedEvent) {
        this.storedEvent = storedEvent;
    }
}