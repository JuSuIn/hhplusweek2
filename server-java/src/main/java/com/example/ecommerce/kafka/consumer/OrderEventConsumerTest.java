package com.example.ecommerce.kafka.consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.listener.ConsumerProperties;

/*
   kafka 관련 주문을 이용한 Test 입니다.
 */
public class OrderEventConsumerTest {

    @Test
    void testConsumeOrderCompletedEvent() {
        OrderEventConsumer consumer = new OrderEventConsumer();
        String dummyMessage = "{\"orderId\":123, \"status\":\"COMPLETED\"}";

        consumer.processOrderCompletedEvent(dummyMessage);
        // 로그 또는 mock 처리 결과 검증 가능
    }
}
