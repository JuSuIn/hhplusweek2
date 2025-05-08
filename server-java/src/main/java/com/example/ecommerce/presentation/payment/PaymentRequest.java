package com.example.ecommerce.presentation.payment;

import com.example.ecommerce.domain.payment.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

/*
  결제 관련 DTO
 */
@Getter
public class PaymentRequest {
    private Long id;
    private Long orderId;//결제 대상 주문ID
    private PaymentMethod method; // 예: CARD, KAKAO_PAY, NAVER_PAY
    private Long amount;//결제 금액

    public PaymentRequest(Long userId,Long orderId,PaymentMethod method,Long amount){
        this.id=userId;
        this.orderId=orderId;
        this.method=method;
        this.amount=amount;
    }

}
