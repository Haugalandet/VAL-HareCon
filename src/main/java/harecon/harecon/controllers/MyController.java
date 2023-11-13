package harecon.harecon.controllers;

import harecon.harecon.rabbit.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lala")
public class MyController {

    private final Publisher publisher;

    @Autowired
    public MyController(Publisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/publish-message")
    public ResponseEntity<String> publishMessage() {
        publisher.publishMessage("nicetry");
        return ResponseEntity.ok("Message published successfully.");
    }
}
