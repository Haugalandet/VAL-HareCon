package harecon.harecon.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class metaSubscriber {
    private final String QUEUE_NAME_META = "MetaQueue";

    @RabbitListener(queues = QUEUE_NAME_META)
    public void processMessage(String message) {
        System.out.println(message);
    }
}
