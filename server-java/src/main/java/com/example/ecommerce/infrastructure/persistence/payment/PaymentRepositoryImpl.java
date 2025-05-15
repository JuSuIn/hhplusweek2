package com.example.ecommerce.infrastructure.persistence.payment;

import com.example.ecommerce.domain.payment.Payment;
import com.example.ecommerce.domain.payment.PaymentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *  결재관련
 *  PaymentRepository implements
 * 결제 데이터에 접근하는 책임을 가짐
 *
 * 결제 저장
 * 결제 ID로 결제 조회
 * 주문 ID로 결제 조회
 * 사용자 ID로 결제 목록 조회
 */

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Payment save(Payment payment) {
        if (payment.getId() == null) {
            em.persist(payment);
            return payment;
        } else {
            return em.merge(payment);
        }
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return Optional.ofNullable(em.find(Payment.class, id));
    }

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        return em.createQuery("SELECT p FROM Payment p WHERE p.order.id = :orderId", Payment.class)
                .setParameter("orderId", orderId)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Payment> findByUserId(Long userId) {
        return em.createQuery("SELECT p FROM Payment p WHERE p.order.userId = :userId", Payment.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
