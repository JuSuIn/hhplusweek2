package com.example.ecommerce.domain.payment;

// 결제 관련 JPA 인터페이스입니다.
// 결제 정보를 저장하거나 조회할 때 사용합니다.

import com.example.ecommerce.domain.payment.Payment;

import java.util.List;
import java.util.Optional;

/**
 * Payment 도메인 리포지토리 인터페이스
 * 결제 데이터에 접근하는 책임을 가짐
 *
 * 결제 저장
 * 결제 ID로 결제 조회
 * 주문 ID로 결제 조회
 * 사용자 ID로 결제 목록 조회
 */

public interface PaymentRepository {

    /**
     * 결제 저장
     * @param payment 저장할 결제 객체
     * @return 저장된 결제 객체
     */
    Payment save(Payment payment); //결제 저장

    /**
     * 결제 ID로 결제 조회
     * @param id 결제 ID
     * @return 결제 정보 (Optional)
     */
    Optional<Payment> findById(Long id);//결제 ID로 결제 조회

    /**
     * 주문 ID로 결제 조회
     * (한 주문에 대해 하나의 결제 정보만 있다고 가정)
     * @param orderId 주문 ID
     * @return 결제 정보 (Optional)
     */
    Optional<Payment> findByOrderId(Long orderId);//주문 ID로 결제 조회

    /**
     * 사용자 ID로 결제 목록 조회
     * (선택사항: 결제 내역 조회용)
     * @param userId 사용자 ID
     * @return 결제 리스트
     */
    List<Payment> findByUserId(Long userId);//사용자 ID로 결제 목록 조회
}
