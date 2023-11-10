package harecon.harecon.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TestSubscriber {

    private final String QUEUE_NAME = "subscriptionQueue";

        @RabbitListener(queues = QUEUE_NAME)
        public void processMessage(String message) {
            System.out.println(message);
        }
    }


