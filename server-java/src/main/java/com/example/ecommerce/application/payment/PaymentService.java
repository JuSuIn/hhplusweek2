// 결제 도메인 관련 호출,트랜잭션 제어
package com.example.ecommerce.application.payment;

import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.order.OrderRepository;
import com.example.ecommerce.domain.order.OrderStatus;
import com.example.ecommerce.domain.payment.Payment;
import com.example.ecommerce.domain.payment.PaymentMethod;
import com.example.ecommerce.domain.payment.PaymentRepository;
import com.example.ecommerce.domain.payment.PaymentStatus;
import com.example.ecommerce.presentation.payment.PaymentRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 결제 처리 서비스
 */

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    /**
     * 결제 생성 (결제 요청)
     */
    @Transactional
    public Payment createPayment(Long orderId, Long amount, PaymentMethod menthod){
        Order order = orderRepository.findById(orderId)
                .orElseThrow( () -> new EntityNotFoundException("주문을 찾을 수 없습니다."));

        Payment payment = new Payment(order,amount,menthod);
        return paymentRepository.save(payment);
    }
    /**
     * 결제 완료 처리
     */
    @Transactional
    public void completePayment(Long paymentId){
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow( () -> new EntityNotFoundException("결제 정보를 찾을 수 없습니다."));

        payment.markAsPaid();
    }

    /**
     * 결제 실패 처리
     * */
    @Transactional
    public void failPayment(Long paymentId){
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow( () -> new EntityNotFoundException("결제 정보를 찾을 수 없습니다."));

        payment.markAdFailed();
    }

    /**
     * 결제 처리
     *
     * @param order    결제할 주문
     * @param request  결제 요청 정보 (PaymentRequest)
     * @return Payment 생성된 결제 정보
     */
    public Payment processPayment(Order order, PaymentRequest request){
        //주문상태 확인
        if( order.getStatus() != OrderStatus.CREATED){
            throw new IllegalStateException("이미 결제되었거나 취소된 주문입니다.");
        }

        //결제 엔티티 생성
        Payment payment = new Payment(order,
                request.getMethod(),
                order.getTotalPrice(),
                PaymentStatus.COMPLETED,
                LocalDateTime.now()
                );

        //결제 저장
        paymentRepository.save(payment);

        //주문 상태 변경
        order.complete();

        return payment;
    }
}
