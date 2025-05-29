package com.example.ecommerce.kafka.consumer;
/*
   kafka 관련 KafkaConsumer 예시
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OrderEventConsumer.class);


    public void processOrderCompletedEvent(String message){
        logger.info("📥 Kafka 주문완료 메시지 수신: {} " + message);
    }

    @KafkaListener(topics = "order.completed", groupId = "order-group")
    public void consumeOrderCompletedEvent(String message){
        processOrderCompletedEvent(message);

        //이부분에서 나중에 메세지 파시한 다음 로직 처리 해야됨
    }
}
