package com.kafkaproducer.controller;

import com.kafkaproducer.dto.Customer;
import com.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            for (int i = 0; i < 100000; i++) {
                kafkaMessagePublisher.sendMessageToTopic(message + ":" + i);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("publishCustomer")
    public ResponseEntity<?> publishMessage(@RequestBody Customer customer) {
        try {
//            for (int i = 0; i < 100000; i++) {
                customer.setId(Long.valueOf(1));
                kafkaMessagePublisher.sendCustomerToTopic(customer);
//            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
