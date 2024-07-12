package co.com.sofkau.rabbit.config;

import co.com.sofkau.rabbit.helper.QueuesProperties;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate, QueuesProperties properties) {
        var rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.declareExchange(new TopicExchange(properties.getExchange()));
        return rabbitAdmin;
    }
}
