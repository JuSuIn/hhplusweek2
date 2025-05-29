# 제목 이커머스 시스템

-----------------------------------------------------------------


# 🔄 Kafka Data Flow in E-commerce System



-----------------------------------------------------------------



---
## 📦 개요
> 사용자가 상품을 주문하면, 해당 주문 완료 이벤트(`OrderCompletedEvent`)가 발행되어  
> 포인트 적립, 쿠폰 사용 처리, 이메일 발송 등 후속 처리가 병렬로 수행됩니다.



---



## 🧩 데이터 흐름 구조 (요약)

| 단계 | 주체 | 설명 |
|------|------|------|
| 1 | 사용자 | 주문 요청 API 호출 |
| 2 | OrderController | 주문 요청 수신, OrderService 호출 |
| 3 | OrderService | 주문 생성 및 DB 저장 후 `OrderCompletedEvent` 발행 |
| 4 | KafkaProducer | 이벤트를 `order.completed` Topic으로 전송 |
| 5 | Kafka Broker | 메시지를 Topic에 저장, Consumer에 전달 대기 |
| 6 | PointConsumer | 메시지 수신 후 포인트 적립 |
| 7 | CouponConsumer | 메시지 수신 후 쿠폰 사용 처리 |
| 8 | EmailConsumer | 메시지 수신 후 이메일 발송 |



---




## 🧩 PlanText

```plaintext
[User]
  │
  ▼
[OrderController.createOrder()]
  │
  ▼
[OrderService.createOrder()]
  │
  ├──> Save Order in DB
  └──> KafkaProducer.send("order.completed", OrderCompletedEvent)
        │
        ▼
    ┌──────────── Kafka Broker ────────────┐
    │                                      │
    ▼                                      ▼
[PointConsumer]                     [CouponConsumer]
    │                                      │
    ▼                                      ▼
포인트 적립                           쿠폰 사용 처리

    ▼
[EmailConsumer]
    │
    ▼
이메일 발송
 ```



---




## ⚙️ Kafka Topic 구조 예시
| Topic 이름          | 설명            |
| ----------------- | ------------- |
| `order.completed` | 주문 완료 이벤트 발행용 |
| `coupon.used`     | 쿠폰 사용 완료 알림   |
| `point.earned`    | 포인트 적립 완료 알림  |
---




## 🔄 메시지 소비 방식
* PointConsumer
  * 메시지를 수신하여 해당 유저의 포인트 계정에 적립 기록 추가
* CouponConsumer
  * 쿠폰 사용 처리 및 상태 업데이트
* EmailConsumer
  * 주문 확인 메일 발송 (이메일 템플릿 시스템 연계)