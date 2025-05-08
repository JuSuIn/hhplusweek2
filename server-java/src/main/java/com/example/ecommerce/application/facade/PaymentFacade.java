package com.example.ecommerce.application.facade;//잔액  + 외부 결제 시스템과의 통합 처리

import com.example.ecommerce.application.order.OrderService;
import com.example.ecommerce.application.payment.PaymentService;
import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.payment.Payment;
import com.example.ecommerce.presentation.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *  결제 관련 파사드
 */
@Service
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;
    private final OrderService orderService;

    //주문 상태 확인 -> 결제 처리 -> 주문 완료 처리
    public Payment processPayment(Long orderId, PaymentRequest request){
        //주문상태확인
        Order order = orderService.getOrder(orderId);

        //결제 처리
        Payment payment = paymentService.processPayment(order,request);

        //주문 완료 처리
        orderService.completeOrder(orderId);

        return payment;
    }
}