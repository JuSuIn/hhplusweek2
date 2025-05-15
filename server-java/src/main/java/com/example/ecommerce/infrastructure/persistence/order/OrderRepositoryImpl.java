package com.example.ecommerce.infrastructure.persistence.order;

import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.order.OrderRepository;
import com.example.ecommerce.domain.order.OrderStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


/**
 * 주문에 대한 데이터 접근을 담당하는 OrderRepository  implements
 *
 *  주문 저장
 *  주문 ID로 주문 조회
 *  특정 사용자 ID로 주문 목록 조회
 *  주문 상태별로 주문 목록 조회
 *  특정 사용자 ID로 최근 주문 목록 조회
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 주문 저장
     * @param order 저장할 주문 객체
     * @return 저장된 주문 객체
     */
    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
            return order;
        } else {
            return em.merge(order);
        }
    }

    /**
     * 주문 ID로 주문 조회
     * @param id 주문 ID
     * @return 주문(Optional)
     */
    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    /**
     * 특정 사용자 ID로 주문 목록 조회
     * @param userId 사용자 ID
     * @return 주문 리스트
     */
    @Override
    public List<Order> findByUserId(Long userId) {
        return em.createQuery("SELECT o FROM Order o WHERE o.userId = :userId", Order.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    /**
     * 주문 상태별로 주문 목록 조회
     * @param status 주문 상태 (CREATED, PAID, CANCELLED 등)
     * @return 주문 리스트
     */
    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return em.createQuery("SELECT o FROM Order o WHERE o.status = :status", Order.class)
                .setParameter("status", status)
                .getResultList();
    }

    /**
     * 특정 사용자 ID로 최근 주문 목록 조회
     * @param userId 사용자 ID
     * @param limit 조회할 주문 개수 제한
     * @return 최근 주문 리스트
     */
    @Override
    public List<Order> findRecentOrdersByUserId(Long userId, int limit) {
        return em.createQuery("SELECT o FROM Order o WHERE o.userId = :userId ORDER BY o.orderedAt DESC", Order.class)
                .setParameter("userId", userId)
                .setMaxResults(limit)
                .getResultList();
    }
}
