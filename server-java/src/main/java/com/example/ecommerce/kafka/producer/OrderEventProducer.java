package com.example.ecommerce.kafka.producer;


/*
   kafka 관련 KafkaProducer 예시
 */

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderCompletedEvent(Long orderId){
        String topic="order.completed";
        String message = "\"orderId\" : " + orderId;

        kafkaTemplate.send(topic,message);
        System.out.println("Order Completed Event sent to topic Kafka: " + message);
    }
}
