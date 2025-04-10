package com.example.ecommerce.domain.payment;// 주문에 대해 얼마를, 어떤 수단으로 결제했는지를 정의합니다.
// 결제 승인/실패 등의 로직을 가질 수 있습니다.

/*
  결제 관련 도메인 정보
 */

import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.payment.PaymentStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PaymentMethod paymentMethod; // 예: CARD, KAKAO_PAY, NAVER_PAY
    private int amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime paidAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    // 결제수단 변경
    public void validdatePaymentMethod(){
        this.paymentMethod.validateAvailability();
    }

}