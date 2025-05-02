package com.example.ecommerce.domain.payment;// 주문에 대해 얼마를, 어떤 수단으로 결제했는지를 정의합니다.
// 결제 승인/실패 등의 로직을 가질 수 있습니다.

/*
  결제 관련 도메인 정보
 */

import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.payment.PaymentStatus;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PaymentMethod method; // 예: CARD, KAKAO_PAY, NAVER_PAY
    private Long amount;//결제 금액

    private LocalDateTime paidAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;            //주문

    private Long orderId;//결제 대상 주문ID

    // 결제수단 변경
    public void validdatePaymentMethod() {
        this.method.validateAvailability();
    }

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // 결제 상태 (결제 완료, 실패 등)

    protected Payment() {}

    public Payment(Long orderId,Long amount,PaymentMethod method){
        this.orderId=orderId;
        this.amount=amount;
        this.method=method;
        this.status=PaymentStatus.READY; //처음상태(결제 대기)
    }

    //결제 완료 처리
    public void markAsPaid(){
        this.status=PaymentStatus.COMPLETED;
        this.paidAt= LocalDateTime.now();
    }

    //결젶 실패 처리
    public void markAdFailed(){
        this.status = PaymentStatus.FAILED;
    }
}