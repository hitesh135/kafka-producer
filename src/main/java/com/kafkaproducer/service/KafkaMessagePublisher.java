package com.kafkaproducer.service;

import com.google.gson.Gson;
import com.kafkaproducer.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private Gson gson;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("OPL-KAFKA-6", message);
        future.whenComplete((result, throwable) -> {
            if (throwable == null) {
                System.out.println(result);
            } else {
                System.out.println(throwable);
            }
        });
    }
    public void sendCustomerToTopic(Customer customer) throws Exception {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("OPL-CUSTOMER", gson.toJson(customer));
        future.whenComplete((result, throwable) -> {
            if (throwable == null) {
                System.out.println("Customer sent :: " + result.toString()
                + " with Offer = " + result.getRecordMetadata().offset());
            } else {
                System.out.println(throwable);
            }
        });
    }
}
