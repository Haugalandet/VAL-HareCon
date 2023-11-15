package harecon.harecon.controllers;

import harecon.harecon.rabbit.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        publisher.publishMessage("{\n" +
                "\"mouse_x\": 100,\n" +
                "\"mouse_y\": 100}");
        return ResponseEntity.ok("Message published successfully.");
    }
}
