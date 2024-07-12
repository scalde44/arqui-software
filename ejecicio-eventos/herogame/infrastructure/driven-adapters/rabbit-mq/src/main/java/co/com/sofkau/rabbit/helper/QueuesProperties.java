package co.com.sofkau.rabbit.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "queues")
public class QueuesProperties {
    private String exchange;
    private QueuesParameters game;

    @Getter
    @Setter
    public static class QueuesParameters {
        private String name;
        private RoutingParameters routing;
    }

    @Getter
    @Setter
    public static class RoutingParameters {
        private String key;
    }
}