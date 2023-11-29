package harecon.harecon.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


@Service
public class ResultSubscriber {



    private final RestTemplate restTemplate;
    private final String QUEUE_NAME_RESULTS = "ResultsQueue";

    @Autowired
    public ResultSubscriber(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RabbitListener(queues = QUEUE_NAME_RESULTS)
    public void sendToDweet(String resultJSON) {
        String dweetUrl = "https://dweet.io/dweet/for/results"; //in a perfect world: change to result's id
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(resultJSON, headers);
    // Send the request
        ResponseEntity<String> response = restTemplate.postForEntity(dweetUrl, entity, String.class);//POST to dweet
        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Dweet sent successfully: " + response.getBody());
        } else {
            System.err.println("Error sending dweet: " + response.getStatusCode());
        }

        //POST to meta-end as well
        //no; metaend is in itself a subscriber
    }







}


