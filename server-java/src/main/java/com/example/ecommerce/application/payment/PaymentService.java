// 결제 도메인 관련 호출,트랜잭션 제어
package com.example.ecommerce.application.payment;

import com.example.ecommerce.domain.payment.Payment;
import com.example.ecommerce.domain.payment.PaymentMethod;
import com.example.ecommerce.domain.payment.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 결제 처리 서비스
 */

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /**
     * 결제 생성 (결제 요청)
     */
    @Transactional
    public Payment createPayment(Long orderId, Long amount, PaymentMethod menthod){
        Payment payment = new Payment(orderId,amount,menthod);
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

}
