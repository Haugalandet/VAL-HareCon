package harecon.harecon.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultPublisher {
    private final String QUEUE_NAME = "subscriptionQueue";
    private final String EXCHANGE_NAME = "subscriptionExchange";

    private final String ROUTING_KEY = "lol";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ResultPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
    }
}
