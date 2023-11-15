package harecon.harecon.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
    public MessageConverter messageConverter() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("java.util.HashMap", HashMap.class);
        classMapper.setIdClassMapping(idClassMapping);

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper);
        return converter;
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