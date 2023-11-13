package harecon.harecon.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;


@Service
public class ResultSubscriber {
    private final RestTemplate restTemplate;
    private final String QUEUE_NAME_RESULTS = "ResultsQueue";

    @Autowired
    public ResultSubscriber(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RabbitListener(queues = QUEUE_NAME_RESULTS)
    public void sendToDweet(String message, String thingName) {
        String dweetUrl = "https://dweet.io/dweet/for/" + thingName; //change to result's id
        ResponseEntity<String> response = restTemplate.postForEntity(dweetUrl, message, String.class); //POST
        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Dweet sent successfully: " + response.getBody());
        } else {
            System.err.println("Error sending dweet: " + response.getStatusCode());
        }

        //POST to meta-end as well
    }







}


