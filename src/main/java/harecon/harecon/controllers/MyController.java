package harecon.harecon.controllers;

import harecon.harecon.rabbit.ResultPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lala")
public class MyController {

    private final ResultPublisher resultPublisher;

    @Autowired
    public MyController(ResultPublisher resultPublisher) {
        this.resultPublisher = resultPublisher;
    }

    @PostMapping("/publish-message")
    public ResponseEntity<String> publishMessage() {
        resultPublisher.publishMessage("Hello, RabbitMQ!");
        return ResponseEntity.ok("Message published successfully.");
    }
}
