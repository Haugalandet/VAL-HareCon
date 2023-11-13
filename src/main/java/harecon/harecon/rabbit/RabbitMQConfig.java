package harecon.harecon.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitMQConfig {


    private final String EXCHANGE_NAME = "subscriptionExchange";
    private final String QUEUE_NAME_RESULTS = "ResultsQueue";
    private final String QUEUE_NAME_META = "MetaQueue";
    private final String ROUTING_KEY_RESULTS = "results";
    private final String ROUTING_KEY_META = "resultsformeta";
    @Bean
    public Queue resultsQueue() {
        return new Queue(QUEUE_NAME_RESULTS);
    }
    @Bean
    public Queue metaQueue() {
        return new Queue(QUEUE_NAME_META);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public TopicExchange subscriptionExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding resultBinding(Queue resultsQueue, TopicExchange subscriptionExchange) {
        return BindingBuilder.bind(resultsQueue).to(subscriptionExchange).with(ROUTING_KEY_RESULTS);
    }

    @Bean
    public Binding metABinding(Queue metaQueue, TopicExchange subscriptionExchange) {
        return BindingBuilder.bind(metaQueue).to(subscriptionExchange).with(ROUTING_KEY_META);
    }
}